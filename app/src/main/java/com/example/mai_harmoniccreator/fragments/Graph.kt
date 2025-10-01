package com.example.mai_harmoniccreator.fragments

import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.mai_harmoniccreator.data.GraphData
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

@Composable
fun Graph(data: GraphData, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            LineChart(context).apply {
                description.isEnabled = false // Отключаем описание
                legend.isEnabled = false // Отключаем легенду
                isDragEnabled = true // Включаем возможность перетаскивания
                setScaleEnabled(true)
                setPinchZoom(true)

                // Настройка оси X
                xAxis.apply {
                    position = XAxis.XAxisPosition.BOTTOM
                    setDrawGridLines(true)
                    textColor = Color.WHITE
                }

                // Настройка оси Y (вертикальной)
                axisRight.isEnabled = false // Отключаем правую ось Y
                axisLeft.apply {
                    setDrawGridLines(true) // Рисовать горизонтальные линии сетки
                    textColor = Color.WHITE
                    // Устанавливаем пределы по оси Y для красивого отображения
                    axisMaximum = data.yAmplitude.toFloat() * 1.2f
                    axisMinimum = -data.yAmplitude.toFloat() * 1.2f
                }
            }
        },
        update = { chart ->
            val entries = data.pointsData.map { point ->
                Entry(point.x, point.y)
            }

            val dataSet = LineDataSet(entries, "Harmonic Signal").apply {
                color = Color.YELLOW
                setDrawValues(false) // Не отображать значения над точками
                setDrawCircles(false) // Не рисовать круги в точках
                lineWidth = 2f
            }

            val lineData = LineData(dataSet)

            chart.data = lineData
            chart.invalidate() // Перерисовать график
        }
    )
}