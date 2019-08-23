package com.oyacanli.quiz.model

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import timber.log.Timber
import javax.inject.Inject


class Timer @Inject constructor() : ITimer {

    private var handler = Handler()
    private lateinit var runnable: Runnable

    private var _secondsLeft: MutableLiveData<Int> = MutableLiveData()
    override var secondsLeft : LiveData<Int> = _secondsLeft

    override fun setCurrentTime(seconds : Int){
        _secondsLeft.value = seconds
    }

    init {
        _secondsLeft.value = 60
        Timber.d("Timer is initialized")
    }

    override fun restart() {
        _secondsLeft.value = 60
        runTimer()
        Timber.d("Timer started")
    }

    override fun stop() {
        handler.removeCallbacks(runnable)
        Timber.d("Timer paused")
    }

    override fun resume(){
        runTimer()
        Timber.d("Timer resumed")
    }

    override fun runTimer() {
        //Start running a count-down timer
        runnable = Runnable {
            _secondsLeft.value = _secondsLeft.value?.minus(1)
            Timber.d("Current time: ${_secondsLeft.value}")
            if(_secondsLeft.value != 0 ){
                handler.postDelayed(runnable, 1000)
            }
        }

        // This is what initially starts the timer
        handler.postDelayed(runnable, 1000)
    }
}