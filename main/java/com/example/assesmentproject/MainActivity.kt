package com.example.assesmentproject

import LogUtils.logd
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.assesmentproject.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private var orderlist_binding: ActivityMainBinding? = null
    private val binding get() = orderlist_binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        orderlist_binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var email =intent.getStringExtra("email")
        Log.d("ssssdds","****"+email)
        var joindate=intent.getStringExtra("joineddate")
        var dob=intent.getStringExtra("dob")
        var city=intent.getStringExtra("city")
        var state=intent.getStringExtra("state")
        var country=intent.getStringExtra("country")
        var postcode=intent.getStringExtra("postcode")
        var image=intent.getStringExtra("image")


       //binding views using viewbinding
        binding.tvEmail.setText("Email:"+ email)
        binding.tvDoj.setText("Date Joined:"+ joindate)
        binding.tvDob.setText("DOB:"+ dob)
        binding.tvCity.setText("City:"+ city)
        binding.tvState.setText("State:"+ state)
        binding.tvCountry.setText("Country:"+  country)
        binding.tvPostcode.setText("PostCode:"+  postcode)

        Glide.with(this)
            .load(image)
            .placeholder(R.drawable.ic_arrow_right)
            .override(100, 100) // resizes the image to these dimensions (in pixel). resize does not respect aspect ratio
            .into(binding.imgUser);

    }


}