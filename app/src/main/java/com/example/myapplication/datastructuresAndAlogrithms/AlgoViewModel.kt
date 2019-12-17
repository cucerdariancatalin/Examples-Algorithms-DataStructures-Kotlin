package com.example.myapplication.datastructuresAndAlogrithms

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class AlgoViewModel(appContext: Application) : AndroidViewModel(appContext) {
    var fibonacciUpdate: MutableLiveData<String> =
        MutableLiveData<String>().apply { postValue("Fibonacci result") }

   suspend fun executeFibonacci(factorialRecursion: Long): Long {
        if (factorialRecursion == 0L) {
            return 1
        } else {
            if (factorialRecursion == 1L) {
                return 1
            } else {
                return (executeFibonacci(factorialRecursion - 1) + executeFibonacci(
                    factorialRecursion - 2
                ))
            }
        }
    }
}