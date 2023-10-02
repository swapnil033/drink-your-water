package com.swapnil.drinkyourwater.di

import android.app.Application
import androidx.room.Room
import com.swapnil.drinkyourwater.feature_dailyProgress.data.dataSource.DailyGoalDao
import com.swapnil.drinkyourwater.feature_dailyProgress.data.dataSource.ProgressHistoryDao
import com.swapnil.drinkyourwater.feature_dailyProgress.data.dataSource.WaterDatabase
import com.swapnil.drinkyourwater.feature_dailyProgress.data.repository.ProgressHistoryRepositoryImpl
import com.swapnil.drinkyourwater.feature_dailyProgress.data.repository.DailyProgressRepositoryImpl
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.repository.ProgressHistoryRepository
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.repository.DailyProgressRepository
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.use_case.AddDailyGoalUseCase
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.use_case.AddNewProgressUseCase
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.use_case.GetDailyGoalUseCase
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.use_case.GetTodayProgressUseCase
import com.swapnil.drinkyourwater.feature_dailyProgress.domain.use_case.LocalUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        application: Application
    ): WaterDatabase{
        return Room
            .databaseBuilder(
                application,
                WaterDatabase::class.java,
                WaterDatabase.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDailyGoalDao(
        database: WaterDatabase
    ): DailyGoalDao{
        return database.dailyGoalDao
    }

    @Provides
    @Singleton
    fun provideDailyProgressHistoryDao(
        database: WaterDatabase
    ): ProgressHistoryDao {
        return database.progressHistoryDao
    }

    @Provides
    @Singleton
    fun provideDailyProgressHistoryRepository(
        progressHistoryDao: ProgressHistoryDao
    ): ProgressHistoryRepository {
        return ProgressHistoryRepositoryImpl(
            dao = progressHistoryDao
        )
    }

    @Provides
    @Singleton
    fun provideDailyProgressRepository(
        dailyGoalDao: DailyGoalDao
    ): DailyProgressRepository {
        return DailyProgressRepositoryImpl(
            dao = dailyGoalDao
        )
    }

    @Provides
    @Singleton
    fun provideLocalUseCases(
        dailyProgressRepository: DailyProgressRepository,
        progressHistoryRepository: ProgressHistoryRepository,

        ): LocalUseCases{
        return LocalUseCases(
            addDailyGoalUseCase = AddDailyGoalUseCase(dailyProgressRepository),
            getDailyGoalUseCase = GetDailyGoalUseCase(dailyProgressRepository),
            getTodayProgressUseCase = GetTodayProgressUseCase(progressHistoryRepository),

            addNewProgressUseCase = AddNewProgressUseCase(progressHistoryRepository, dailyProgressRepository)
        )
    }


}