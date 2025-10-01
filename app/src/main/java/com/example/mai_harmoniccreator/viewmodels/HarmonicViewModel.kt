package com.example.mai_harmoniccreator.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.mai_harmoniccreator.data.GraphData
import com.example.mai_harmoniccreator.data.HarmonicSignalParameters
import com.example.mai_harmoniccreator.data.Point
import com.example.mai_harmoniccreator.data.physicalUnits.deg
import com.example.mai_harmoniccreator.data.physicalUnits.div
import com.example.mai_harmoniccreator.data.physicalUnits.hz
import com.example.mai_harmoniccreator.data.physicalUnits.rad
import com.example.mai_harmoniccreator.data.physicalUnits.v
import com.example.mai_harmoniccreator.navigation.DrawDestination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.math.sin
import kotlin.time.times
import com.example.mai_harmoniccreator.utils.Complex
import com.example.mai_harmoniccreator.utils.FFT

class HarmonicViewModel(
    val navController: NavHostController
) : ViewModel() {
    private val _events = MutableSharedFlow<UiEvent>()
    val events = _events.asSharedFlow()

    private val _harmonicSignalParametersFlow: MutableStateFlow<HarmonicSignalParameters> = MutableStateFlow(
        HarmonicSignalParameters(
            amplitude = 10.v,
            frequency = 50.hz,
            phase = 0.deg
        )
    )
    val harmonicSignalParametersFlow = _harmonicSignalParametersFlow

    fun updateAmplitude(newAmplitude: Double) {
        val newHarmonicSignalParameters = _harmonicSignalParametersFlow.value.copy(
            amplitude = newAmplitude.v
        )
        _harmonicSignalParametersFlow.value = newHarmonicSignalParameters
    }

    fun updateFrequency(newFrequency: Double) {
        val newHarmonicSignalParameters = _harmonicSignalParametersFlow.value.copy(
            frequency = newFrequency.hz
        )
        _harmonicSignalParametersFlow.value = newHarmonicSignalParameters
    }

    fun updatePhase(newPhase: Double, isDegrees: Boolean) {
        val newHarmonicSignalParameters = _harmonicSignalParametersFlow.value.copy(
            phase = if (isDegrees) {newPhase.deg} else {newPhase.rad}
        )
        _harmonicSignalParametersFlow.value = newHarmonicSignalParameters
    }

    fun updateHarmonicSignalParameters(newParameters: HarmonicSignalParameters) {
        _harmonicSignalParametersFlow.value = newParameters
    }

    fun onShowSignal() {
        Log.i("APP", "onShowSignal")
        viewModelScope.launch {
            _events.emit(UiEvent.Navigate(DrawDestination))
        }
        viewModelScope.launch(Dispatchers.Default) {
            updateGraphData()
            updateSpectrumData()
        }
    }

    fun onBackToSetup() {
        viewModelScope.launch {
            _events.emit(UiEvent.PopBack)
        }
    }

    private val _graphDataFlow: MutableStateFlow<GraphData?> = MutableStateFlow(
        null
    )

    val graphDataFlow = _graphDataFlow

    suspend fun updateGraphData() {
        val parameters = _harmonicSignalParametersFlow.value
        val samplesCount = 10000
        val periodsCount = 5
        val period = 2 * Math.PI / parameters.frequency
        val totalPeriod = period * periodsCount
        val sampleTime = totalPeriod / samplesCount

        _graphDataFlow.emit(null)
        val newPointsData: MutableList<Point> = mutableListOf(Point(0f, 0f))
        for (i in 0 until samplesCount) {
            val x = (i * sampleTime).inWholeNanoseconds.toDouble().div(1e9)
            val y = (parameters.amplitude.value) * sin(x * parameters.frequency.value + parameters.phase.inRadians)
            newPointsData.addLast(Point(x.toFloat(), y.toFloat()))
        }
        val newGraphData = GraphData(
            pointsData = newPointsData,
            xAmplitude = (periodsCount * period).inWholeNanoseconds.toDouble().div(1e9),
            yAmplitude = parameters.amplitude.value
        )
        Log.i("COUNTS", "$newGraphData")
        _graphDataFlow.emit(newGraphData)
    }

    private val _spectrumDataFlow: MutableStateFlow<GraphData?> = MutableStateFlow(
        null
    )
    val spectrumDataFlow = _spectrumDataFlow

    suspend fun updateSpectrumData() {

        val parameters = _harmonicSignalParametersFlow.value
        val samplesCount = 4096
        val samplingFrequency = parameters.frequency.value * 10
        val sampleTime = 1.0 / samplingFrequency

        val timeSignal = Array(size = samplesCount) { 0.0 }
        for (i in 0 until samplesCount) {
            val t = i * sampleTime
            timeSignal[i] = (parameters.amplitude.value) * sin(2 * Math.PI * parameters.frequency.value * t + parameters.phase.inRadians)
        }

        val complexSignal = Array(samplesCount) { i ->
            Complex(timeSignal[i].toFloat(), 0f)
        }

        val spectrum = FFT.fft(complexSignal)

        _spectrumDataFlow.emit(null)
        val spectrumPoints = mutableListOf<Point>()
        var maxAmplitude = 0f


        for (k in 0 until samplesCount / 2) {
            val frequency = k * samplingFrequency / samplesCount
            val amplitude = (spectrum[k].abs() / samplesCount) * 2

            if (amplitude > maxAmplitude) {
                maxAmplitude = amplitude
            }

            spectrumPoints.add(Point(frequency.toFloat(), amplitude))
        }

        val newSpectrumData = GraphData(
            pointsData = spectrumPoints,
            xAmplitude = samplingFrequency / 2.0,
            yAmplitude = maxAmplitude.toDouble()
        )

        Log.i("COUNTSSPECTRUM", "$newSpectrumData")
        _spectrumDataFlow.emit(newSpectrumData)
    }
}



sealed class UiEvent {
    data class Navigate(val route: Any) : UiEvent()
    object PopBack : UiEvent()
}