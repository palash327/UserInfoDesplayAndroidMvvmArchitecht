package com.example.assesmentproject.users

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assesmentproject.MainActivity
import com.example.assesmentproject.R



class UsersAdapter(var context: Context, private var processedlistdetails: List<Users>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_data_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ProductViewHolder -> {

                addview(holder, position)


            }
        }
    }


    override fun getItemCount(): Int {
        return processedlistdetails?.size ?: 0
    }


    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tile: TextView = view.findViewById(R.id.tv_title)
        var firstname: TextView = view.findViewById(R.id.tv_firstname)
        var lastmane: TextView = view.findViewById(R.id.tv_lastname)
        var email: TextView = view.findViewById(R.id.tv_email)
        var countryname: TextView = view.findViewById(R.id.tv_country_name)
        var image: ImageView = view.findViewById(R.id.img_user_image)
        var llmain: LinearLayout = view.findViewById(R.id.ll_parent_layout)

    }

    private fun addview(holder: ProductViewHolder, position: Int) {
        val user = processedlistdetails?.get(position)
        holder.tile.text = user!!.title
        holder.firstname.text =  user.firstname
        holder.lastmane.text = user.lastname
        holder.email.text =user.email
        holder.countryname.text="Country Name|"+user.country



        Glide.with(context)
            .load(user.image)
            .placeholder(R.drawable.ic_arrow_right)
            .override(100, 100) // resizes the image to these dimensions (in pixel). resize does not respect aspect ratio
            .into(holder.image);

        holder.llmain.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)

            intent.putExtra("email",user.email )
            intent.putExtra("joineddate",user.DateJoined)
            intent.putExtra("dob",user.dob)
            intent.putExtra("city", user.city)
            intent.putExtra("state", user.state)
            intent.putExtra("country", user.country)
            intent.putExtra("postcode", user.postcode)
            intent.putExtra("image", user.image)

            context.startActivity(intent)

        }

    }

}
