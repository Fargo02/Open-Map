package com.example.openmap.map.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openmap.core.util.NetworkError
import com.example.openmap.map.domain.usecase.GetSavedStationsUseCase
import com.example.openmap.map.domain.usecase.GetStationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getStationsUseCase: GetStationsUseCase,
    private val getSavedStationsUseCase: GetSavedStationsUseCase
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

                    if (savedStation.isNotEmpty()) {
                        _uiState.update { it.copy(stations = savedStation) }
                        return@onFailure
                    }
                    when (error) {
                        is NetworkError.ServerError,
                        is NetworkError.NoData,
                        is NetworkError.BadRequest -> _uiState.update {
                            it.copy(
                                dialogMessage = DialogData(message = error.message ?: ERROR),
                                showDialog = true
                            )
                        }

                        is NetworkError.NoInternet -> _uiState.update {
                            it.copy(
                                dialogMessage = DialogData(
                                    message = error.message,
                                    action = UPDATE,
                                    onAction = { getStations() }),
                                showDialog = true
                            )
                        }
                    }
                }
        }
    }

    fun changeVisibilityDialog(flag: Boolean) {
        _uiState.update { it.copy(showDialog = flag) }
    }

    companion object {
        private const val ERROR = "Ошибка"
        private const val UPDATE = "Обновить"
    }
}