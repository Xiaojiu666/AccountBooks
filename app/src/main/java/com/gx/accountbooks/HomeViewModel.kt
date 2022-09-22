package com.gx.accountbooks

import androidx.lifecycle.*
import com.gx.task.repository.TaskRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    var homeR: HomeRepository
) : ViewModel() {

    var name = "HomeVIewModel"
    var currentTime: LiveData<Long> = liveData {
        while (true) {
            emit(System.currentTimeMillis())
            delay(1000)
        }
    }

    var currentTimeTransformed:LiveData<String> = Transformations.switchMap(currentTime){
        liveData {
//            LogUtil.d("currentTimeTransformed ${it}")
            emit(timeStampToTime(it))
        }
    }

    fun getData() {
//        LogUtil.d("getData ${currentTimeTransformed.value}")
    }

    // Simulates a long-running computation in a background thread
    private suspend fun timeStampToTime(timestamp: Long): String {
        delay(500)  // Simulate long operation
        val date = Date(timestamp)
        return date.toString()
    }
}