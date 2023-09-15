package com.example.groupstagesim.util

object Constants {

    const val DATABASE_NAME = "groupstage_database"

    const val DATABASE_TEAM_TABLE = "teams"
    const val DATABASE_MATCH_TABLE = "matches"

    const val START_SCREEN = "start"
    const val CREATE_TEAMS_SCREEN = "teams"
    const val SIMULATE_MATCH_SCREEN = "match"
    const val RESULTS_SCREEN = "results"

    const val MAX_TEAM_NAME_CHARACTERS = 15

    const val AMOUNT_OF_ROUNDS = 3
    const val MATCHES_PER_ROUND = 2
    const val SHOT_ATTEMPTS = 5
    const val AMOUNT_OF_TEAMS = 4

    const val POINTS_ON_WIN = 3
    const val POINTS_ON_DRAW = 1

    const val ERROR_MESSAGE_EMPTY_NAMES = "Please fill in all the names"
    const val ERROR_MESSAGE_DUPLICATE_NAMES = "Please make all the names unique"

}