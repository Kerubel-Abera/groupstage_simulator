package com.example.groupstagesim.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groupstagesim.data.models.Match
import com.example.groupstagesim.data.models.Team
import com.example.groupstagesim.data.repositories.MatchRepository
import com.example.groupstagesim.data.repositories.TeamRepository
import com.example.groupstagesim.util.Constants.AMOUNT_OF_ROUNDS
import com.example.groupstagesim.util.Constants.MATCHES_PER_ROUND
import com.example.groupstagesim.util.Constants.POINTS_ON_DRAW
import com.example.groupstagesim.util.Constants.POINTS_ON_WIN
import com.example.groupstagesim.util.Constants.SHOT_ATTEMPTS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class SimulateViewModel @Inject constructor(
    private val teamRepository: TeamRepository,
    private val matchRepository: MatchRepository
) : ViewModel() {

    private val _allTeams = MutableStateFlow<List<Team>>(emptyList())
    val allTeams: StateFlow<List<Team>> = _allTeams

    // Messages displayed during match simulation.
    private val loadingMessages = listOf(
        "Everyone is doing their best to win!",
        "Goals have been scored!",
        "The spectators are having fun.",
        "A spectator tried to run on the field...",
        "It's a good weather for the match today!",
        "The star players are going on the bench..."
    )

    init {
        getAllTeams()
    }

    private fun getAllTeams() {
        viewModelScope.launch {
            teamRepository.getAllTeams.collect {
                _allTeams.value = it
            }
        }
    }

    /**
     * Generates a random loading message for match simulation.
     *
     * @return A random loading message.
     */
    fun generateMatchMessage(): String {
        return loadingMessages.random()
    }


    /**
     * Simulates matches for the specified teams and rounds.
     *
     * @return A list of simulated matches.
     */
    fun simulateMatches(teams: List<Team>): List<Match> {

        val allMatches: MutableList<Match> = mutableListOf()

        if (teams.size == 4) {
            //val teams = _allTeams.value
            val shuffledTeams = teams.shuffled().toMutableList()
            var matchNumber = 0

            for (round in 1..AMOUNT_OF_ROUNDS) {
                // Simulate matches for each round.
                for (match in 1..MATCHES_PER_ROUND) {
                    val homeTeam = shuffledTeams[match - 1]
                    val awayTeam = shuffledTeams[match + 1]
                    var homeTeamScore = 0
                    var awayTeamScore = 0

                    for (i in 1..SHOT_ATTEMPTS) {
                        // Simulate goal scoring attempts.
                        if (simulateGoalScoring(homeTeam.rating, awayTeam.rating)) homeTeamScore++
                        if (simulateGoalScoring(awayTeam.rating, homeTeam.rating)) awayTeamScore++
                    }
                    val winningTeamId = when {
                        homeTeamScore > awayTeamScore -> homeTeam.id
                        homeTeamScore < awayTeamScore -> awayTeam.id
                        else -> -1
                    }

                    val simulatedMatch = Match(
                        id = matchNumber,
                        homeTeamId = homeTeam.id,
                        awayTeamId = awayTeam.id,
                        homeTeamScore = homeTeamScore,
                        awayTeamScore = awayTeamScore,
                        roundNumber = round,
                        winningTeamId = winningTeamId
                    )

                    allMatches.add(simulatedMatch)

                    // Update match data and team statistics.
                    addMatch(simulatedMatch)
                    addMatchDataToTeam(homeTeam, homeTeamScore, awayTeamScore)
                    addMatchDataToTeam(awayTeam, awayTeamScore, homeTeamScore)
                    matchNumber++
                }
                val removedTeam = shuffledTeams.removeAt(2)
                shuffledTeams.add(0, removedTeam)
            }
        } else {
            throw IllegalStateException("Expected 4 teams but found ${_allTeams.value.size} teams.")
        }
        return allMatches
    }


    /**
     * Simulates goal scoring attempts for a match.
     *
     * @param ownRating The rating of the home team.
     * @param oppositionRating The rating of the away team.
     * @return `true` if a goal is scored, `false` otherwise.
     */
    private fun simulateGoalScoring(ownRating: Int, oppositionRating: Int): Boolean {
        val ratingDifference = ownRating - oppositionRating
        val probabilityOfScoring = calculateProbability(ratingDifference)
        val randomValue = Random.nextFloat()

        return randomValue <= probabilityOfScoring
    }


    /**
     * Calculates the probability of scoring based on the rating difference.
     *
     * @param ratingDifference The rating difference between two teams.
     * @return The calculated probability of scoring.
     */
    private fun calculateProbability(ratingDifference: Int): Float {
        val baseProbability = 0.5f
        return (baseProbability + (ratingDifference * 0.05)).coerceIn(0.0, 1.0).toFloat()
    }


    /**
     * Adds a match to the match repository.
     *
     * @param match The match to be added.
     */
    private fun addMatch(match: Match) {
        viewModelScope.launch {
            matchRepository.addMatch(match)
        }
    }

    /**
     * Updates the team's data in the repository.
     *
     * @param team The team with updated statistics to be stored in the repository.
     */
    private fun updateTeam(team: Team) {
        viewModelScope.launch {
            teamRepository.updateTeam(team)
        }
    }

    /**
     * Updates the team's statistics based on the match result and stores the updated team data
     * in the repository.
     *
     * @param team The team to update with match statistics.
     * @param teamScore The score of the team in the match.
     * @param opposingScore The score of the opposing team in the match.
     */
    private fun addMatchDataToTeam(team: Team, teamScore: Int, opposingScore: Int) {
        team.played++
        team.goalsFor = team.goalsFor + teamScore
        team.goalsAgainst = team.goalsAgainst + opposingScore
        if (teamScore > opposingScore) {
            team.win++
            team.points = team.points + POINTS_ON_WIN
        } else if (teamScore == opposingScore) {
            team.draw++
            team.points = team.points + POINTS_ON_DRAW
        } else {
            team.loss++
        }
        team.difference = team.goalsFor - team.goalsAgainst

        // Update the team data in the repository.
        updateTeam(team)
    }

}