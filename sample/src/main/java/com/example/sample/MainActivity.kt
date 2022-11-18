package com.example.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.sample.databinding.ActivityMainBinding
import us.smailbarkouch.android_breadcrumb.BreadCrumb

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.breadcrumbBar.apply {
            addBreadCrumbItem(BreadCrumb(title = "Item 1"))
            addBreadCrumbItem(BreadCrumb(title = "Item 2"))
            addBreadCrumbItem(BreadCrumb(title = "Item 3"))
            addBreadCrumbItem(BreadCrumb(title = "Item 4"))
        }
    }
}