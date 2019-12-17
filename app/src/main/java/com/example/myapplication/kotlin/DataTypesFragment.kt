package com.example.myapplication.kotlin


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.myapplication.R

/**
 * A simple [Fragment] subclass.
 */
class DataTypesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_types, container, false)
    }

    override fun onResume() {
        super.onResume()
        callingFunctionForInlineFunction(method)

        var arr = emptyArray<Int>()
    }

    fun test() {
        var list = ArrayList<String>()
    }

    // inline function
    private inline fun callingFunctionForInlineFunction(method: () -> Unit) {

    }

    val method = {
        Log.d("Inline", "Inline function")
        Unit
    }


}
