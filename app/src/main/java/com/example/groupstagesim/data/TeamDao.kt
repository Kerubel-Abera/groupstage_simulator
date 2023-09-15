package com.example.groupstagesim.data

import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.groupstagesim.data.models.Team
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {

    @Query("SELECT * FROM teams ORDER BY id ASC")
    fun getAllTeams(): Flow<List<Team>>

    @Query("SELECT * FROM TEAMS WHERE id=:teamId")
    fun getTeam(teamId: Int): Flow<Team>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTeam(team: Team)

    @Transaction
    suspend fun insertTeams(teams: List<Team>) {
        for(team in teams) {
            addTeam(team)
        }
    }

    @Update
    suspend fun updateTeam(team: Team)

    @Transaction
    suspend fun updateAllTeams(teams: List<Team>) {
        for(team in teams){
            updateTeam(team)
        }
    }

    @Query("DELETE FROM teams")
    suspend fun deleteAllTeams()

}