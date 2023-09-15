package com.example.groupstagesim.data.repositories

import com.example.groupstagesim.data.MatchDao
import com.example.groupstagesim.data.models.Match
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class MatchRepository @Inject constructor(private val matchDao: MatchDao) {

    val getAllMatches: Flow<List<Match>> = matchDao.getAllMatches()

    fun getMatchesFromRound(roundNumber: Int): Flow<List<Match>> {
        return matchDao.getMatchesFromRound(roundNumber)
    }

    suspend fun addMatch(match: Match) {
        matchDao.addMatch(match)
    }

    suspend fun deleteAllMatches() {
        matchDao.deleteAllMatches()
    }
}