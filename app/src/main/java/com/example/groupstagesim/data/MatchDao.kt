package com.example.groupstagesim.data

import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.groupstagesim.data.models.Match
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchDao {

    @Query("SELECT * FROM matches ORDER BY id ASC")
    fun getAllMatches(): Flow<List<Match>>

    @Query("SELECT * FROM matches WHERE round_number=:roundNumber")
    fun getMatchesFromRound(roundNumber: Int): Flow<List<Match>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMatch(match: Match)

    @Query("DELETE FROM matches")
    suspend fun deleteAllMatches()

}