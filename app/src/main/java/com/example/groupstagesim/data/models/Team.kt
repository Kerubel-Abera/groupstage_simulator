package com.example.groupstagesim.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.groupstagesim.util.Constants.DATABASE_TEAM_TABLE

@Entity(tableName = DATABASE_TEAM_TABLE)
data class Team(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    @ColumnInfo(name = "team_name")
    val teamName: String = "Reset",
    val rating: Int = 1,
    var played: Int = 0,
    var win: Int = 0,
    var draw: Int = 0,
    var loss: Int = 0,
    @ColumnInfo(name = "goals_for")
    var goalsFor: Int = 0,
    @ColumnInfo(name = "goals_against")
    var goalsAgainst: Int = 0,
    var difference: Int = 0,
    var points: Int = 0
)

