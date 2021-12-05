package com.plcoding.dictionary.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.plcoding.dictionary.data.Converter
import com.plcoding.dictionary.data.WordInfoRepositoryImpl
import com.plcoding.dictionary.data.local.WordInfosDatabase
import com.plcoding.dictionary.data.remote.DIctionaryApi
import com.plcoding.dictionary.data.util.GsonParser
import com.plcoding.dictionary.domain.repository.WordInfoRepository
import com.plcoding.dictionary.domain.use_case.GetWordInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    @Provides
    @Singleton
    fun providesGetWordInfoUseCase(repository: WordInfoRepository):GetWordInfo{

        return GetWordInfo(repository)

    }

    @Provides
    @Singleton
    fun providesWordInfoRepository(db: WordInfosDatabase, api:DIctionaryApi):WordInfoRepository{
        return  WordInfoRepositoryImpl(dao = db.dao, api = api)
    }

    @Provides
    @Singleton
    fun providesWordInfoDatabase(app:Application):WordInfosDatabase{
        return Room.databaseBuilder(
            app,
            WordInfosDatabase::class.java,
            "word_db")
            .addTypeConverter(Converter(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun providesDictionaruApi():DIctionaryApi{
        return  Retrofit.Builder()
            .baseUrl(DIctionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DIctionaryApi::class.java)
    }
}