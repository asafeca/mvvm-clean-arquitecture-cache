package com.plcoding.dictionary.data

import com.plcoding.dictionary.core.util.Resource
import com.plcoding.dictionary.data.local.dao.WordInfoDao
import com.plcoding.dictionary.data.remote.DIctionaryApi
import com.plcoding.dictionary.domain.model.WordInfo
import com.plcoding.dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val dao:WordInfoDao,
    private val api:DIctionaryApi): WordInfoRepository {
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow{
        emit(Resource.Loading())
        val result =   dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(data=result))

        try{

            val remoteData = api.getWordInfo(word).map { it.toWordInfo() }
            dao.deleteWordInfos(remoteData.map{it.word})
            emit(Resource.Success(remoteData))


        }
        catch (e: IOException){

            emit(Resource.Error("An error occured. Details: ${e.localizedMessage}"))

        }
        catch (e:HttpException){

            emit(Resource.Error("An error occured on server. Details: ${e.localizedMessage}"))

        }

        val localResponse =   dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(localResponse))

    }
}