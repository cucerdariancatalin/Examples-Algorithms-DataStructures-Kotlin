package com.example.myapplication.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.datastructuresAndAlogrithms.AlgorithmsFragment
import com.example.myapplication.kotlin.CoRoutinesFragment
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {
    private var array = arrayOf<String>("Test", "test1", "test3")
    private var list = ArrayList<Int>()

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        initViews()
    }

    private fun initViews() {
        coroutines_test.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, CoRoutinesFragment())?.commitNow()
        }
        algo_test.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, AlgorithmsFragment())?.commitNow()
        }
    }


}
