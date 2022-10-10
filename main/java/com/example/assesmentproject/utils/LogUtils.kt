

import android.util.Log

object LogUtils {
    private const val isLog: Boolean = true

    public fun logv(TAG: String,Message: String)
    {
        if(isLog)
        {
            Log.v(TAG, Message)
        }
    }

    public fun loge(TAG: String,Message: String)
    {
        if(isLog)
        {
            Log.e(TAG, Message)
        }
    }

    public fun logi(TAG: String,Message: String)
    {
        if(isLog)
        {
            Log.i(TAG, Message)
        }
    }

    public fun logd(TAG: String,Message: String)
    {
        if(isLog)
        {
            Log.d(TAG, Message)
        }
    }

    public fun logw(TAG: String,Message: String)
    {
        if(isLog)
        {
            Log.w(TAG, Message)
        }
    }
}