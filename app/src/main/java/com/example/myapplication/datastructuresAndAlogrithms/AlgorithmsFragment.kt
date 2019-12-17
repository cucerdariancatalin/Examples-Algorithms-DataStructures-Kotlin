package com.example.myapplication.datastructuresAndAlogrithms

import android.net.MailTo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.myapplication.R
import com.example.myapplication.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_algorithms.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.math.pow

class AlgorithmsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_algorithms, container, false)
    }

    lateinit var viewModel: AlgoViewModel
    val testJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.IO + testJob)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModels()
        initClicks()
        observe()
    }

    private fun observe() {
        viewModel.fibonacciUpdate.observe(this, Observer { value ->
            fibonacci_result_txt.text = value
        })
    }

    private fun initViewModels() {
        viewModel = ViewModelProviders.of(this).get(AlgoViewModel::class.java)
    }

    private fun initClicks() {
        recursion_btn.setOnClickListener {
            val factorialRecursion = factorial_input.text.toString().toLong()
            factorial_result_txt.text = executeFactorialRecursion(factorialRecursion).toString()
        }
        factorial_result_txt.setOnClickListener {
            factorial_result_txt.text = "Factorial result"
        }
        fibonacci_btn.setOnClickListener {
            val fibonacciInput = fibonacci_input.text.toString().toLong()
            uiScope.launch {
                val value = viewModel.executeFibonacci(fibonacciInput).toString()
                viewModel.fibonacciUpdate.postValue(value)
            }

        }
        grading_btn.setOnClickListener {
            grade()
        }
    }

    private fun grade() {
        val rr = arrayOf(
            44,
            84,
            94,
            21,
            0,
            18,
            100,
            18,
            62,
            30,
            61,
            53,
            0,
            43,
            2,
            29,
            53,
            61,
            40
        )

        //  retVal = gradingStudents(rr)
        val inp = arrayOf(1, 2, 3, 4)
        var retVal = reverseArray(inp)

        grading_result_txt.text = retVal.joinToString(",")
    }

    private fun executeFactorialRecursion(number: Long): Long {
        if (number == 0L) {
            return 1L
        } else {
            return number * executeFactorialRecursion((number - 1))
        }
    }


    fun gradingStudents(grades: Array<Int>): Array<Int> {
        // Write your code here

        if (grades.size > 60) {
            return emptyArray()
        }
        var retArr = emptyArray<Int>()
        for (grade in grades) {
            val modulus = grade % 5
            if (modulus == 0) {
                if (grade >= 40) {
                    retArr = retArr.plus(arrayOf(grade))
                    continue
                }
            }
            val nextMultiple = grade + (5 - modulus)
            if (nextMultiple - grade < 3 && nextMultiple > 38) {
                val diff = 5 - modulus
                val updated = grade + diff
                retArr = retArr.plus(arrayOf(updated))
            } else {
                retArr = retArr.plus(arrayOf(grade))
            }
        }

        return retArr

    }

    fun reverseArray(a: Array<Int>): Array<Int> {
        var retArr = emptyArray<Int>()
        if (a.size < 1 || a.size > 1000) {
            return emptyArray()
        } else {

            a.forEachIndexed { index, item ->
                if (item >= 10000) {
                    return emptyArray()
                } else {
                    retArr = retArr.plus(arrayOf(a[a.size - 1 - index]))
                }
            }
        }
        return retArr
    }

   /* fun hourglassSum(arr: Array<Array<Int>>): Int {
        val hourGlassesMatrix = Array<Array<Int>>(12, { Array<Int>(12, { 0 }) })

        for (rowIndex in 0..arr.size) {
            for (columnIndex in 0..arr[rowIndex].size) {
                val item = arr[rowIndex][columnIndex]
                if (item < -9 || item > 9) {
                    return -1
                }

            }
        }
        for (hrRowIndex in 0..hourGlassesMatrix.size) {
            for (hrColumnIndex in 0..hourGlassesMatrix[hrRowIndex].size) {

            }
        }
    }*/

}
