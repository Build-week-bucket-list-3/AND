package com.example.bucketlist.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.example.bucketlist.R
import kotlinx.android.synthetic.main.fragment_host.*

class HostFragment : Fragment() , View.OnClickListener {
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_host, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        btn_nav_test.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            btn_nav_test -> navController.navigate(R.id.action_hostFragment_to_navGraphTestFragment)
        }
    }
}