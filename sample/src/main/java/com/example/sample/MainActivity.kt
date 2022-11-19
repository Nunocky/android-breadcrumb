package com.example.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.sample.databinding.ActivityMainBinding
import org.nunocky.breadcrumb.BreadCrumbView
import org.nunocky.breadcrumb.BreadCrumbView.BreadCrumbItem

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.breadcrumbBar2.apply {
            addBreadCrumbItem(BreadCrumbItem(title = "Item 1"))
            addBreadCrumbItem(BreadCrumbItem(title = "Item 2"))
            addBreadCrumbItem(BreadCrumbItem(title = "Item 3"))
            addBreadCrumbItem(BreadCrumbItem(title = "Item 4"))
            addBreadCrumbItem(BreadCrumbItem(title = "Item 5"))
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