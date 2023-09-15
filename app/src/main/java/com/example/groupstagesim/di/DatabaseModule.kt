package com.example.groupstagesim.di

import android.content.Context
import androidx.room.Room
import com.example.groupstagesim.data.GroupstageDatabase
import com.example.groupstagesim.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        GroupstageDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideTeamDao(database: GroupstageDatabase) = database.teamDao()

    @Provides
    @Singleton
    fun provideMatchDao(database: GroupstageDatabase) = database.matchDao()

}