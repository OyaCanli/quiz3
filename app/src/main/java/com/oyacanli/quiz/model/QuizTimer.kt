package com.oyacanli.quiz.model

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import timber.log.Timber
import javax.inject.Inject


class QuizTimer @Inject constructor() : IQuizTimer {

    private var handler = Handler()
    private var runnable: Runnable? = null

    private var _secondsLeft: MutableLiveData<Int> = MutableLiveData()
    override var secondsLeft : LiveData<Int> = _secondsLeft

    override fun setCurrentTime(seconds : Int){
        _secondsLeft.value = seconds
    }

    init {
        _secondsLeft.value = 60
        Timber.d("QuizTimer is initialized")
    }

    override fun restart() {
        _secondsLeft.value = 60
        runTimer()
        Timber.d("QuizTimer started")
    }

    override fun stop() {
        runnable?.let { handler.removeCallbacks(it) }
        Timber.d("QuizTimer paused")
    }

    override fun resume(){
        runTimer()
        Timber.d("QuizTimer resumed")
    }

    override fun runTimer() {
        //Start running a count-down timer
        runnable = Runnable {
            _secondsLeft.value = _secondsLeft.value?.minus(1)
            //Timber.d("Current time: ${_secondsLeft.value}")
            if(_secondsLeft.value != 0 ){
                handler.postDelayed(runnable!!, 1000)
            }
        }

        // This is what initially starts the timer
        handler.postDelayed(runnable!!, 1000)
    }
}