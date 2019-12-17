package com.example.myapplication.kotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class TestViewModel(appContext: Application) : AndroidViewModel(appContext) {
    var modifiedByDispatcher: MutableLiveData<String> =
        MutableLiveData<String>().apply { postValue("Suspending using dispatcher....waiting") }

    var modifiedByAsync: MutableLiveData<String> =
        MutableLiveData<String>().apply { postValue("Async result waiting.....") }
}