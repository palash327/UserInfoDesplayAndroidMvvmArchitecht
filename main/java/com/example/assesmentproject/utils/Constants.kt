package com.deerika.picker.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

object Constants {

    const val userInfo = "profileData"

    const val url = "https://randomuser.me/"



    var retrofit: Retrofit? = null
    var retrofitHeader: Retrofit? = null

    fun getWebClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(url)
                .build()
        }
        return retrofit!!
    }

    fun setLogging(): OkHttpClient? {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        return client

    }

    fun getWebClient2(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(url)
                .client(setLogging())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }




    /**
     * Get shared preferences file
     */
    fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences("Data", Context.MODE_PRIVATE)//table name
    }

    /**
     * Validate email Id format
     */
    fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target)
            .matches()
    }

    fun uploadImage2(
        bitmap: Bitmap,
        context: Context
    ): File {
        return getImageUri35(bitmap, context)
    }

    private fun getImageUri35(inImage: Bitmap, context: Context): File {

        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"

        val externalStorageVolumes =
            ContextCompat.getExternalFilesDirs(context, null)
        val primaryExternalStorage = externalStorageVolumes[0]
        //////////
        val mFile: File
        val fn = primaryExternalStorage.toString() + File.separator + imageFileName + ".jpg"
        mFile = File(fn)

        try {
            val out = FileOutputStream(mFile)
            inImage.compress(Bitmap.CompressFormat.JPEG, 80, out)

            out.flush()
            out.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return mFile
    }


    fun getId(context: Context): Int {
        val prefs = Constants.getPrefs(context)
        val gson = Gson()
        val profileString = prefs.getString(Constants.userInfo, "")
        return if (profileString!!.isNotEmpty()) {
            0
        } else {
            0
        }
    }





    /**hide keyboard**/
    fun hideSoftKeyboard(view: View, context: Context) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)
    }


    /**
     * Singleton Gson Instance
     */
    var gson: Gson? = null
    fun getGsonInstance(): Gson {
        if (gson == null) {
            gson = Gson()
        }
        return gson!!
    }

    fun roundOffNumber(value: String): String {
        val a = BigDecimal(value)
        val scaled = a.setScale(0, RoundingMode.HALF_UP);
        return scaled.toString()
    }


}