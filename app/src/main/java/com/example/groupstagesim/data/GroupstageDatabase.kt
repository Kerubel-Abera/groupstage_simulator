package com.example.groupstagesim.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.groupstagesim.data.models.Match
import com.example.groupstagesim.data.models.Team


@Database(
    entities = [Team::class, Match::class],
    version = 1,
    exportSchema = false
)
abstract class GroupstageDatabase : RoomDatabase() {
    abstract fun teamDao(): TeamDao
    abstract fun matchDao(): MatchDao
}