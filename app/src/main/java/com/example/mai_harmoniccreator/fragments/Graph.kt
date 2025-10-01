package com.example.mai_harmoniccreator.fragments

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.LoadingIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.RoundedPolygon
import co.yml.charts.axis.AxisConfig
import co.yml.charts.axis.AxisData
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import co.yml.charts.ui.wavechart.WaveChart
import co.yml.charts.ui.wavechart.model.Wave
import co.yml.charts.ui.wavechart.model.WaveChartData
import co.yml.charts.ui.wavechart.model.WaveFillColor
import co.yml.charts.ui.wavechart.model.WavePlotData
import com.example.mai_harmoniccreator.data.GraphData
import com.example.mai_harmoniccreator.data.HarmonicSignalParameters

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun Graph(data: GraphData) {
    val pointsData = data.pointsData

    val xAxisData = AxisData.Builder()
        .steps(pointsData.size - 1) // количество шагов = число точек - 1
        .axisStepSize(30.dp) // расстояние между точками
        .bottomPadding(40.dp)
        .labelData { index ->
            "%.4f".format(pointsData[index].x) // выводим реальные X из точек
        }
        .build()

    val yAxisData = AxisData.Builder()
        .steps(5)
        .axisOffset(20.dp)
        .labelData { index ->
            ((index * data.yAmplitude) / 5f).toString()
        }
        .build()

    val waveChartData = WaveChartData(
        wavePlotData = WavePlotData(
            lines = listOf(
                Wave(
                    dataPoints = pointsData,
                    waveStyle = LineStyle(color = Color.Black),
                    selectionHighlightPoint = SelectionHighlightPoint(),
                    shadowUnderLine = ShadowUnderLine(),
                    selectionHighlightPopUp = SelectionHighlightPopUp(),
                    waveFillColor = WaveFillColor(
                        topColor = Color.Green,
                        bottomColor = Color.Red
                    ),
                )
            )
        ),
        isZoomAllowed = false,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
    )

    WaveChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        waveChartData = waveChartData
    )
}