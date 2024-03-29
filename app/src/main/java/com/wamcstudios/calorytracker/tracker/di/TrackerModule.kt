package com.wamcstudios.calorytracker.tracker.di

import android.app.Application
import androidx.room.Room
import com.wamcstudios.calorytracker.core.common.Constant
import com.wamcstudios.calorytracker.core.domain.preferences.Preferences
import com.wamcstudios.calorytracker.tracker.data.local.TrackerDatabase
import com.wamcstudios.calorytracker.tracker.data.remote.OpenFoodApi
import com.wamcstudios.calorytracker.tracker.data.repository.TrackerRepositoryImpl
import com.wamcstudios.calorytracker.tracker.domain.repository.TrackerRepository
import com.wamcstudios.calorytracker.tracker.domain.use_case.CalculateMealNutrients
import com.wamcstudios.calorytracker.tracker.domain.use_case.DeleteTrackedFood
import com.wamcstudios.calorytracker.tracker.domain.use_case.GetFoodsForDate
import com.wamcstudios.calorytracker.tracker.domain.use_case.SearchFood
import com.wamcstudios.calorytracker.tracker.domain.use_case.TrackFood
import com.wamcstudios.calorytracker.tracker.domain.use_case.TrackerUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrackerModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }).build()
    }

    @Provides
    @Singleton
    fun provideOpenFoodApi(client: OkHttpClient): OpenFoodApi {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create()).client(client).build().create()
    }

    @Provides
    @Singleton
    fun provideTrackerDatabase(app: Application): TrackerDatabase {
        return Room.databaseBuilder(app, TrackerDatabase::class.java, "tracker_db").build()
    }

    @Provides
    @Singleton
    fun provideTrackerRepository(db: TrackerDatabase, api: OpenFoodApi): TrackerRepository {
        return TrackerRepositoryImpl(dao = db.dao, api = api)
    }

    @Provides
    @Singleton
    fun provideTrackerUseCases(
        trackerRepository: TrackerRepository,
        preferences: Preferences,
    ): TrackerUseCases {
        return TrackerUseCases(
            trackFood = TrackFood(trackerRepository),
            searchFood = SearchFood(trackerRepository),
            getFoodsForDate = GetFoodsForDate(trackerRepository),
            deleteTrackedFood = DeleteTrackedFood(trackerRepository),
            calculateMealNutrients = CalculateMealNutrients(preferences)
        )
    }
}