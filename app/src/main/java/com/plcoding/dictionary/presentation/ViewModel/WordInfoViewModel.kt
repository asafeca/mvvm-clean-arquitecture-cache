package com.plcoding.dictionary.presentation.ViewModel

import android.provider.UserDictionary
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.dictionary.core.util.Resource
import com.plcoding.dictionary.domain.model.WordInfoState
import com.plcoding.dictionary.domain.use_case.GetWordInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(private val getWordInfoUseCase: GetWordInfo):ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _state  = mutableStateOf(WordInfoState())
     val state:State<WordInfoState> = _state


    private val _eventFlow = MutableSharedFlow<UIEvent>()
     val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null;

    fun onSearch(word:String){
        _searchQuery.value = word

        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            delay(500L)

            getWordInfoUseCase(word)
                .onEach{result ->

                    when(result){
                        is Resource.Success ->{

                            _state.value = state.value.copy(
                                wordInfoItems = result.data?: emptyList(),
                                isLoading=false
                            )

                        }

                        is Resource.Error ->{
                            _state.value = state.value.copy(
                                wordInfoItems = result.data?: emptyList(),
                                isLoading=false
                            )

                            _eventFlow.emit(UIEvent.ShowSnackbar(result.message?:"Unknown error!"))

                        }

                        is Resource.Loading ->{
                            _state.value = state.value.copy(
                                wordInfoItems = emptyList(),
                                isLoading=true
                            )

                        }

                    }

                }.launchIn(this)
        }

    }

    sealed class UIEvent(){
        data class ShowSnackbar(val message:String):UIEvent()
    }
}