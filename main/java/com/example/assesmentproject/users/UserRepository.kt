package com.example.assesmentproject.users

import LogUtils
import WebServices
import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.deerika.picker.utils.Constants
import com.example.assesmentproject.R

import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback

class UserRepository(var application: Application) {


    private var mErrorDatadetails = MutableLiveData<String>()
    private var mSuccessDataprocesseddetails = MutableLiveData<String>()

    private var mGetlistData=MutableLiveData<java.util.ArrayList<Users>>()



    fun observeErrorprocessedlist(): MutableLiveData<String> {
        return mErrorDatadetails
    }

    fun observerSuccessprocessedlist(): MutableLiveData<String> {
        return mSuccessDataprocesseddetails
    }


    fun observeGetlist(): MutableLiveData<java.util.ArrayList<Users>> {
        return mGetlistData
    }

    fun processeddorderlistdeatils() {
        val webClient = Constants.getWebClient2()
        val services = webClient.create(WebServices::class.java)
        val call: Call<ResponseBody> = services.processedorderdetails()


        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: retrofit2.Response<ResponseBody>) {
                LogUtils.loge("UserRepository:Success", response.toString())
                if (response.isSuccessful) {

                    try {
                        val res = response.body()!!.string()
                        val json = JSONObject(res)
                        LogUtils.loge("UserRepository:Success", res.toString())

                        lateinit var modellist: java.util.ArrayList<Users>
                        val jsonArray: JSONArray = json.getJSONArray("results")
                        modellist= java.util.ArrayList()

                  //      mSuccessDataprocesseddetails.value=json.getString("\"Uh oh, something has gone wrong. Please tweet us @randomapi about the issue. Thank you.")

                        for (i in 0 until jsonArray.length()) {
                            var objectjson= jsonArray.getJSONObject(i)
                            Log.d("sdfdf",objectjson.toString())
                            var name: JSONObject =objectjson.getJSONObject("name")
                            var title:String=name.getString("title")
                            var first:String=name.getString("first")
                            var last:String=name.getString("last")

                            var picture:JSONObject=objectjson.getJSONObject("picture")
                            var image:String=picture.getString("thumbnail")

                            var datejoined:JSONObject=objectjson.getJSONObject("registered")
                            var dateofjoin:String=datejoined.getString("date")

                            var userdob:JSONObject=objectjson.getJSONObject("dob")
                            var dob:String=userdob.getString("date")

                            var address:JSONObject=objectjson.getJSONObject("location")

                            var street:JSONObject=address.getJSONObject("street")
                            var city:String=address.getString("city")
                            var postcode:String=address.getString("postcode")
                            var state:String=address.getString("state")

                            var country:String=address.getString("country")
                            var email:String=objectjson.getString("email")



                            modellist.add(Users(image,title,first,last,country,email,dateofjoin,dob,city,state,postcode))


                        }

                        mGetlistData?.setValue(modellist)

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                } else {
                    try {
                        val res = response.errorBody()!!.string()
                        val json = JSONObject(res)


                        LogUtils.loge("UserRepository:Falure", res.toString())
                        val message = json.optString("message")
                        mErrorDatadetails.value = message

                    } catch (e: Exception) {
                        mErrorDatadetails.value = "Netwrok Error"
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                // LogUtils.loge("calling",call.)
                LogUtils.logd("UserRepository", "network Error:faliure")
                mErrorDatadetails.value = application.getString(R.string.network_error)
            }

        })


    }




}