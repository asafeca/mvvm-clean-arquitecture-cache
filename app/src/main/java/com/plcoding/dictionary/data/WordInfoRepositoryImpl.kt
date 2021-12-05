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
    private val api:DIctionaryApi,
    private val dao: WordInfoDao):WordInfoRepository {
    override fun getWordInfo(word:String): Flow<Resource<List<WordInfo>>> = flow {

        emit(Resource.Loading())
        val wordInfos = dao.getWordInfos(word).map{it.toWordInfo()}
        emit(Resource.Loading(data = wordInfos))


        try{
            val remoteWordInfo = api.getWordInfo(word)
            dao.deleteWordInfos(remoteWordInfo.map{it.word})
            dao.insertWordInfos(remoteWordInfo.map { it.toWordInfoEntiy() })

            emit(Resource.Success(remoteWordInfo.map { it.toWordInfo() }))

        }
        catch(e:HttpException){

            emit(Resource.Error(message = "OOPS, Something went wrong! ${e.localizedMessage}", data = wordInfos))

        }
        catch(e:IOException){

            emit(Resource.Error(message = "Couldn't reach server", data = wordInfos))


        }

        val newWordInfos = dao.getWordInfos(word).map{it.toWordInfo()}

        emit(Resource.Success(data = newWordInfos))

    }
}