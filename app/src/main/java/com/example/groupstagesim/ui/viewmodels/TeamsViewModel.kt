package com.example.groupstagesim.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groupstagesim.data.models.Team
import com.example.groupstagesim.data.repositories.TeamRepository
import com.example.groupstagesim.util.Constants.AMOUNT_OF_TEAMS
import com.example.groupstagesim.util.Constants.ERROR_MESSAGE_DUPLICATE_NAMES
import com.example.groupstagesim.util.Constants.ERROR_MESSAGE_EMPTY_NAMES
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val teamRepository: TeamRepository,
) : ViewModel() {

    private fun addTeam(team: Team) {
        viewModelScope.launch {
            teamRepository.addTeam(team)
        }
    }

    private fun insertTeams(teams: List<Team>){
        viewModelScope.launch {
            teamRepository.insertTeams(teams)
        }
    }

    /**
     * Resets all teams' statistics to initial values.
     */
    fun resetTeams() {
        val teams: MutableList<Team> = mutableListOf()
        val resetNames= listOf(
            "Team A",
            "Team B",
            "Team C",
            "Team D"
        )
        for (i in 1..AMOUNT_OF_TEAMS) {
            val team = Team(
                id = i,
                teamName = resetNames[i-1],
                rating = 1,
                played = 0,
                win = 0,
                draw = 0,
                loss = 0,
                goalsFor = 0,
                goalsAgainst = 0,
                difference = 0,
                points = 0
            )
            teams.add(team)
        }

        viewModelScope.launch {
            teamRepository.updateAllTeams(teams)
        }
    }

    /**
     * Adds teams to the database with their names and ratings.
     *
     * @param teamsWithRatings A list of pairs containing team names and ratings.
     */
    fun enterTeams(teamsWithRatings: List<Pair<String, Int>>) {
        var id = 0
        val teams: MutableList<Team> = mutableListOf()
        for ((teamName, teamRating) in teamsWithRatings) {
            val team = Team(
                id = id + 1,
                teamName = teamName,
                rating = teamRating,
                played = 0,
                win = 0,
                draw = 0,
                loss = 0,
                goalsFor = 0,
                goalsAgainst = 0,
                difference = 0,
                points = 0
            )
            Log.i("CreateTeamsScreen", "ADDED TEAM ${team.teamName} on id ${team.id}")
            teams.add(team)
            //addTeam(team = team)
            id++
        }
        insertTeams(teams)
    }

    /**
     * Validates user inputs for team names.
     *
     * @param inputs A list of user-provided team names.
     * @return An error message if there are validation issues; otherwise, null.
     */
    fun checkInputs(inputs: List<String>): String? {

        if (inputs.any { it.isEmpty() }) {
            return ERROR_MESSAGE_EMPTY_NAMES
        }

        if (inputs.size != inputs.distinct().size) {
            return ERROR_MESSAGE_DUPLICATE_NAMES
        }

        return null
    }

}