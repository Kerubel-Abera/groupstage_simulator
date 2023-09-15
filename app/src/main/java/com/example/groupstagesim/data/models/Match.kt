package com.example.groupstagesim.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.groupstagesim.util.Constants.DATABASE_MATCH_TABLE

@Entity(
    tableName = DATABASE_MATCH_TABLE,
    foreignKeys = [
        ForeignKey(
            entity = Team::class,
            childColumns = ["home_team_id"],
            parentColumns = ["id"]
        ),
        ForeignKey(
            entity = Team::class,
            childColumns = ["away_team_id"],
            parentColumns = ["id"]
        )
    ]
)
data class Match(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    @ColumnInfo(name = "home_team_id")
    val homeTeamId: Int,
    @ColumnInfo(name = "away_team_id")
    val awayTeamId: Int,
    @ColumnInfo(name = "home_team_score")
    val homeTeamScore: Int = 0,
    @ColumnInfo(name = "away_team_score")
    val awayTeamScore: Int = 0,
    @ColumnInfo(name = "round_number")
    val roundNumber: Int,
    @ColumnInfo(name = "winning_team_id")
    val winningTeamId: Int = 0
)
