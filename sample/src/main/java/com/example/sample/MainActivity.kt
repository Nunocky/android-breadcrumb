package com.example.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.sample.databinding.ActivityMainBinding
import org.nunocky.breadcrumb.BreadCrumb
import org.nunocky.breadcrumb.BreadCrumbView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.breadcrumbBar2.apply {
            addBreadCrumbItem(BreadCrumb(title = "Item 1"))
            addBreadCrumbItem(BreadCrumb(title = "Item 2"))
            addBreadCrumbItem(BreadCrumb(title = "Item 3"))
            addBreadCrumbItem(BreadCrumb(title = "Item 4"))
            addBreadCrumbItem(BreadCrumb(title = "Item 5"))
        }

        binding.breadcrumbBar.setOnItemClickListener(object : BreadCrumbView.OnItemClickListener {
            override fun onItemClick(position: Int) {
                this@MainActivity.position = position
                binding.breadcrumbBar.setPosition(position)
            }
        })

        binding.button.setOnClickListener {
            position -= 1
            binding.breadcrumbBar.setPosition(position)
            binding.breadcrumbBar2.setPosition(position)
        }

        binding.button2.setOnClickListener {
            position += 1
            binding.breadcrumbBar.setPosition(position)
            binding.breadcrumbBar2.setPosition(position)
        }
    }
}