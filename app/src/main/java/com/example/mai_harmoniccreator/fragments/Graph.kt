package com.example.mai_harmoniccreator.fragments

import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.mai_harmoniccreator.data.GraphData
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlin.collections.map

@Composable
fun Graph(data: GraphData, modifier: Modifier = Modifier) {
    // AndroidView позволяет встроить View из старой системы в Compose
    AndroidView(
        modifier = modifier,
        // factory - это лямбда, которая создает View. Вызывается один раз.
        factory = { context ->
            LineChart(context).apply {
                // Базовая настройка внешнего вида графика
                description.isEnabled = false // Отключаем описание
                legend.isEnabled = false // Отключаем легенду
                isDragEnabled = true
                setScaleEnabled(true)
                setPinchZoom(true)

                // Настройка оси X (горизонтальной)
                xAxis.apply {
                    position = XAxis.XAxisPosition.BOTTOM
                    setDrawGridLines(true) // рисовать вертикальные линии сетки
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
        // update - вызывается при каждом изменении `data` (рекомпозиции)
        update = { chart ->
            // 1. Преобразуем наши данные (List<Point>) в формат библиотеки (List<Entry>)
            val entries = data.pointsData.map { point ->
                Entry(point.x, point.y)
            }

            // 2. Создаем LineDataSet - это набор данных для одной линии на графике
            val dataSet = LineDataSet(entries, "Harmonic Signal").apply {
                color = Color.YELLOW
                setDrawValues(false) // Не отображать значения над точками
                setDrawCircles(false) // Не рисовать круги в точках
                lineWidth = 2f // Толщина линии
            }

            // 3. Создаем LineData, которая содержит все наши LineDataSet (у нас он один)
            val lineData = LineData(dataSet)

            // 4. Устанавливаем данные в график и обновляем его
            chart.data = lineData
            chart.invalidate() // Перерисовать график
        }
    )
}