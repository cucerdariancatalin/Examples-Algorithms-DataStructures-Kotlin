package com.example.myapplication.kotlin


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_co_routines.*
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * A simple [Fragment] subclass.
 */
class CoRoutinesFragment : Fragment() {
    lateinit var viewmodel: TestViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_co_routines, container, false)
    }

    override fun onStart() {
        super.onStart()
        initViewModel()
        initViews()
        observe()
        suspendingUsingDelay()
        suspendingUsingDispatchIO()
    }

    private fun observe() {
        viewmodel.modifiedByDispatcher.observe(this, Observer { value ->
            update_dispatcher_text.text = value
        })
        viewmodel.modifiedByAsync.observe(this, Observer { value ->
            async_result_text.text = value
        })
    }

    private fun initViewModel() {
        viewmodel = ViewModelProviders.of(activity!!)[TestViewModel::class.java]
    }

    private fun initViews() {
        update_text_reset_button.setOnClickListener {
            update_text.text = "Suspending using delay.....waiting"
            suspendingUsingDelay()
        }
        update_dispatcher_text_reset_button.setOnClickListener {
            update_dispatcher_text.text = "Suspending using dispatcher....waiting"
            suspendingUsingDispatchIO()
        }

        async_two_text_reset_button.setOnClickListener {
            async_result_text.text = "Async result waiting....."
            testAsync()
            runBlocking {
                testMeasureAsync()
            }
        }
    }

    private suspend fun testMeasureAsync() {
        val time = measureTimeMillis {
            val time1 = uiScope.async(start = CoroutineStart.LAZY) { measureTimeOne() }
            val time2 = uiScope.async(start = CoroutineStart.LAZY) { measureTimeTwo() }
            time1.start()
            time2.start()
            viewmodel.modifiedByAsync.postValue(time1.await().toString().plus("  ").plus(time1.await()))
        }
    }

    private fun testAsync() {
        uiScope.launch(Dispatchers.IO) {
            val asyncone = async { executeAsyncOne() }
            val asynctwo = async { executeAsyncTwo() }
            viewmodel.modifiedByAsync.postValue(asyncone.await().plus("  "))
            Thread.sleep(1000)
            viewmodel.modifiedByAsync.postValue(asyncone.await().plus("  ").plus(asynctwo.await()))
        }
    }

    private fun executeAsyncTwo(): String {
        Thread.sleep(1000)
        return "Async1 completed"
    }

    private fun executeAsyncOne(): String {
        Thread.sleep(4000)
        return "Async 2 completed"
    }

    val testJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + testJob)
    private fun suspendingUsingDelay() {
        uiScope.launch(Dispatchers.IO) {
            update_text.text = testSuspendUsingDelay()
        }
        update_text.text = "Suspending using delay.....waiting"
    }

    suspend fun testSuspendUsingDelay(): String {
        delay(5000)
        return "Suspending using delay.....Completed"
    }

    private fun suspendingUsingDispatchIO() {
        uiScope.launch(Dispatchers.IO) {
            Thread.sleep(3000)
            viewmodel.modifiedByDispatcher.postValue("Suspending using delay.....completed\"")
        }
    }


    private suspend fun measureTimeOne(): Int {
        for (i in 0..100000) {

        }
        return 1
    }

    private suspend fun measureTimeTwo(): Int {
        for (i in 0..100) {

        }
        return 2
    }

}
