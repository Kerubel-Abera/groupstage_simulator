package com.example.groupstagesim.data.repositories

import androidx.room.Transaction
import com.example.groupstagesim.data.TeamDao
import com.example.groupstagesim.data.models.Team
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class TeamRepository @Inject constructor(private val teamDao: TeamDao) {

    val getAllTeams: Flow<List<Team>> = teamDao.getAllTeams()

    fun getTeam(teamId: Int): Flow<Team> {
        return teamDao.getTeam(teamId)
    }

    suspend fun addTeam(team: Team) {
        teamDao.addTeam(team)
    }

    suspend fun insertTeams(teams: List<Team>) {
        teamDao.insertTeams(teams)
    }

    suspend fun updateTeam(team: Team) {
        teamDao.updateTeam(team)
    }

    suspend fun updateAllTeams(teams: List<Team>) {
        teamDao.updateAllTeams(teams)
    }

    suspend fun deleteAllTeams() {
        teamDao.deleteAllTeams()
    }

}