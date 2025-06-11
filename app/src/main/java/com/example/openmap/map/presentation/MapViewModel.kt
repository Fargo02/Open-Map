package com.example.openmap.map.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openmap.map.domain.usecase.GetStationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.openmap.core.util.NetworkError
import com.example.openmap.map.domain.usecase.GetSavedStationsUseCase
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getStationsUseCase: GetStationsUseCase,
    private val getSavedStationsUseCase : GetSavedStationsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MapScreenState())
    val uiState: StateFlow<MapScreenState> = _uiState.asStateFlow()

    init {
        getStations()
    }

    private fun getStations() {
        viewModelScope.launch {
            getStationsUseCase()
                .onSuccess { result -> _uiState.update { it.copy(stations = result) } }
                .onFailure { error ->
                    val savedStation = getSavedStationsUseCase()
                    _uiState.update { it.copy(stations = savedStation) }
                    when (error) {
                        is NetworkError.ServerError -> {}
                        is NetworkError.NoData -> {}
                        is NetworkError.BadRequest -> {}
                        is NetworkError.NoInternet -> {}
                    }
                }
        }
    }
}