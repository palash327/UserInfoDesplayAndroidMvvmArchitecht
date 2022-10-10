

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface WebServices {

    @GET("api/?results=100")
    fun processedorderdetails(): Call<ResponseBody>

    @GET("api/?results=1")
    fun processedorderdetailsNew(): Call<ResponseBody>




}