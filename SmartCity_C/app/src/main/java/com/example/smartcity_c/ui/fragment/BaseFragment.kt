package com.example.smartcity_c.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import java.util.zip.Inflater

abstract class BaseFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutInflaterId(),container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }
    abstract fun init()
    abstract fun getLayoutInflaterId(): Int
}