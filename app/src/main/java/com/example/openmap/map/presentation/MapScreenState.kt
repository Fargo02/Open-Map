package com.example.openmap.map.presentation

import com.example.openmap.core.domain.model.Station

data class MapScreenState(
    val stations: List<Station> = emptyList(),
    val dialogMessage: DialogData = DialogData(),
    val showDialog: Boolean = false
)

data class DialogData(
    val message: String = "",
    val action: String = "",
    val onAction: (() -> Unit)? = null
)