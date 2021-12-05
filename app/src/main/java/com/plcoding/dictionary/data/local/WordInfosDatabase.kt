package com.plcoding.dictionary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.plcoding.dictionary.data.Converter
import com.plcoding.dictionary.data.local.dao.WordInfoDao
import com.plcoding.dictionary.data.local.entity.WordInfoEntity

@Database(entities = [WordInfoEntity::class], version = 1)
@TypeConverters(Converter::class)
abstract class WordInfosDatabase : RoomDatabase(){

    abstract val dao : WordInfoDao
}