package com.example.assesmentproject.users

import SnackbarUtils
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assesmentproject.R



import com.example.assesmentproject.databinding.ActivityUserDetailsBinding


class UsersActivity : AppCompatActivity() {


    private var orderlist_binding: ActivityUserDetailsBinding? = null
    private val binding get() = orderlist_binding!!
    private lateinit var orderlistadapter: UsersAdapter
    lateinit var mViewModel: UsersViewModel

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        orderlist_binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mViewModel = ViewModelProvider(this).get(UsersViewModel::class.java)

        val window: Window = this.getWindow()
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));



        mViewModel.processeddorderlist()

        setServiceClick()
        observerInit()


    }

    //setting observers
    private fun observerInit() {
        mViewModel.observeErrorprocessedlist().observe(this, Observer {
            binding.processeddetailsprogress.visibility = View.GONE
            SnackbarUtils.showSnackBar(it, binding.processeddetailsprogress, this)
        })
        mViewModel.observetopList().observe(this, Observer {
            binding.processeddetailsprogress.visibility = View.GONE
            binding.orderlistRecyclerViewCart.apply {
                layoutManager = LinearLayoutManager(this@UsersActivity)
                orderlistadapter = UsersAdapter(context, it)
                adapter = orderlistadapter

            }


        })
        mViewModel.observerSuccessprocessedlist().observe(this, Observer {
            binding.processeddetailsprogress.visibility = View.GONE
            SnackbarUtils.showSnackBar(it, binding.processeddetailsprogress, this)


        })



    }

    // button clicks
    private fun setServiceClick() {
        binding.orderlistBackImg.setOnClickListener {

            onBackPressed()
        }

    }





}