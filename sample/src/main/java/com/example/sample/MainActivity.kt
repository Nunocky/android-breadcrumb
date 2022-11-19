package com.example.sample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.sample.databinding.ActivityMainBinding
import org.nunocky.breadcrumb.BreadCrumbItemClickListener
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
        }

        binding.breadcrumbBar.setItemClickListener(object : BreadCrumbItemClickListener {
            override fun onItemClick(breadCrumbItem: View, position: Int) {
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