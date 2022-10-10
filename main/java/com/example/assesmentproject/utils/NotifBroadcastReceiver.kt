import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.assesmentproject.R


class NotifBroadcastReceiver : BroadcastReceiver() {
//    val NOTIFICATION_CHANNEL_ID = "10001"
//    var NOTIFICATION_ID = "notification-id"
//    var NOTIFICATION = "notification"
//    var NOTIFICATION_MSG = "notificationmsg"
    var MsgTitle = ""
    var MsgBody: String= ""

    val channelId = "notification_channel"
    val channelName = "com.deerika.picker"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(p0: Context?, p1: Intent?) {

        Log.e("notify broadcast","working")

//        if (p0 != null) {
//            //generateNotification(p0)
//
//
//            val bundle = p1?.extras
//
//            var keySet = bundle?.keySet()
//
//
//            Log.e("NotifBroadcastReceiver","$keySet")
//            val title = bundle?.getString("message")
//
//            Log.e("NotifBroadcastReceiver","$title")
//
//
//        }



        if (p1?.getExtras() != null) {



//            for (key in p1?.getExtras()!!.keySet()) {
//                val value: Any = p1.getExtras()!!.get(key)!!
//
//                Log.e("keyChecking","key is $key and value is $value")
//
//
//
//
//            }
            MsgBody = p1.getExtras()!!.get("gcm.notification.body").toString()
            MsgTitle = p1.getExtras()!!.get("gcm.notification.title").toString()

            if(MsgTitle.equals("Order Assigned")){

                if (p0 != null) {
                    generateNotification(p0,MsgTitle,MsgBody)
                }

            }



        }



    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun generateNotification(context: Context,title: String, message: String){

//        val intent = Intent(context, MainActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)


//        val pendingIntent = PendingIntent.getActivity(
//            context, 0, intent,
//            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
//        )


        var builder: NotificationCompat.Builder =
            NotificationCompat.Builder(context, channelId)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.arrow)
                .setContentTitle(title)
                .setContentText(message)
              //  .setContentIntent(pendingIntent)



        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager





        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)

        }






    }

}
