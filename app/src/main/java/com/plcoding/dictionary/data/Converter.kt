package com.plcoding.dictionary.data

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.plcoding.dictionary.data.util.JsonParser
import com.plcoding.dictionary.domain.model.Meaning

@ProvidedTypeConverter
class Converter(private  val jsonParser: JsonParser) {
    @TypeConverter
    fun fromMeaningJson(json:String):List<Meaning>{
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json,
           object : TypeToken<ArrayList<Meaning>>(){}?.type
        )?: emptyList()
    }

    @TypeConverter
    fun toMeaningJson(meanings:List<Meaning>):String{
        return jsonParser.toJson(meanings,
        object : TypeToken<ArrayList<Meaning>>(){}?.type
        )?:"[]"

    }
}