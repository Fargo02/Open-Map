package com.example.openmap.map.presentation

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.openmap.R
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@Composable
fun MapScreen(
    modifier: Modifier = Modifier,
    state: MapScreenState,
    changeVisibilityDialog: (Boolean) -> Unit
) {
    val context = LocalContext.current
    Box(modifier = modifier.fillMaxSize()) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                MapView(context).apply {
                    setTileSource(TileSourceFactory.OpenTopo)
                    setMultiTouchControls(true)
                    controller.setZoom(5.0)
                }
            },
            update = { map ->
                map.overlays.clear()
                val circleDrawable = GradientDrawable().apply {
                    shape = GradientDrawable.OVAL
                    setColor(Color.YELLOW)
                    setSize(50, 50)
                }

                state.stations.forEach { markerData ->
                    val marker = Marker(map).apply {
                        icon = circleDrawable
                        position = GeoPoint(markerData.pos.lat, markerData.pos.lng)
                        title = markerData.mountpoint
                        snippet = buildString {
                            append(context.getString(R.string.clients_count, markerData.clients))
                            append(context.getString(R.string.rtcm_label))
                            markerData.rtcm.forEach { (key, value) ->
                                append("$key: $value")
                            }
                        }

                        setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                    }
                    map.overlays.add(marker)
                }

                if (state.stations.isNotEmpty()) {
                    map.controller.setCenter(GeoPoint(56.0153, 92.8932))
                }
            }
        )
    }

    CustomAlertDialog(
        showDialog = state.showDialog,
        dialogMessage = state.dialogMessage,
        changeVisibilityDialog = { changeVisibilityDialog(it) }
    )
}

@Composable
private fun CustomAlertDialog(
    showDialog: Boolean,
    dialogMessage: DialogData,
    changeVisibilityDialog: (Boolean) -> Unit
) {
    if (showDialog) {
        AlertDialog(
            modifier = Modifier,
            onDismissRequest = {
                changeVisibilityDialog(false)
                dialogMessage.onAction?.let { it() }
            },
            title = {
                Text(
                    modifier = Modifier.padding(end = 30.dp),
                    text = dialogMessage.message,
                    color = MaterialTheme.colorScheme.primary
                )
            },
            confirmButton = {
                Button(onClick = {
                    dialogMessage.onAction?.let { it() }
                    changeVisibilityDialog(false)
                }) {
                    Text(text = dialogMessage.action)
                }
            }
        )
    }
}