package com.example.groupstagesim.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groupstagesim.data.models.Match
import com.example.groupstagesim.data.models.Team
import com.example.groupstagesim.data.repositories.MatchRepository
import com.example.groupstagesim.data.repositories.TeamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val teamRepository: TeamRepository,
    private val matchRepository: MatchRepository
) : ViewModel() {

    private val _allTeams = MutableStateFlow<List<Team>>(emptyList())
    val allTeams: StateFlow<List<Team>> = _allTeams

    private val _allMatches = MutableStateFlow<List<Match>>(emptyList())
    val allMatches: StateFlow<List<Match>> = _allMatches

    private val _allTeamsSorted = MutableStateFlow<List<Team>>(emptyList())
    val allTeamsSorted: StateFlow<List<Team>> = _allTeamsSorted

    init {
        getAllTeams()
        getAllMatches()
    }

    private fun getAllTeams() {
        viewModelScope.launch {
            teamRepository.getAllTeams.collect {
                _allTeams.value = it
            }
        }
    }

    private fun getAllMatches() {
        viewModelScope.launch {
            matchRepository.getAllMatches.collect {
                _allMatches.value = it
            }
        }
    }

    /**
     * Sorts the list of teams based on points, goal difference, goals for, goals against,
     * and head-to-head results.
     */
    fun sortTeamList() {
        val sortingTeamList = _allTeams.value.toMutableList()

        sortingTeamList.sortWith(
            compareByDescending<Team> { it.points }
                .thenByDescending { it.difference }
                .thenByDescending { it.goalsFor }
                .thenBy { it.goalsAgainst }
                .thenComparing { team1, team2 ->
                    calculateHeadToHeadScore(team1, team2)
                }
        )

        _allTeamsSorted.value = sortingTeamList.toList()

    }

    /**
     * Calculates the head-to-head score between two teams based on match results.
     *
     * @param team1 The first team.
     * @param team2 The second team.
     * @return The head-to-head score where positive indicates an advantage for team1,
     * negative for team2, and zero for a tie.
     */
    private fun calculateHeadToHeadScore(team1: Team, team2: Team): Int {
        val team1Id = team1.id
        val team2Id = team2.id
        var headToHeadScore = 0

        for (match in _allMatches.value) {
            if (
                (match.homeTeamId == team1Id && match.awayTeamId == team2Id) ||
                (match.homeTeamId == team2Id && match.awayTeamId == team1Id)
            ) {
                when (match.winningTeamId) {
                    team1Id -> {
                        headToHeadScore--
                    }

                    team2Id -> {
                        headToHeadScore++
                    }

                    else -> {
                        // No winner in the match.
                    }
                }

            }
        }
        return headToHeadScore
    }

}