package com.palak.notiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.app.NotificationChannel
import android.graphics.Color
import android.app.Notification
import androidx.fragment.app.Fragment


class MainActivity : AppCompatActivity() {

    lateinit var stringFragmentName : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println("FRAGG : onCreate Main savedInstanceState : $savedInstanceState, intent : $intent")
        if(intent != null){
            val values : String? = intent.getStringExtra("INTENT_FRAG_NAME")
            println("FRAGG : onCreate values : $values " + " flags : "+intent.flags)
            if(values != null && stringFragmentName == null){
                stringFragmentName = values
            }
        }

        stringFragmentName = "fragment1"

        loadFragment(true)
    }

    override fun onBackPressed() {

        val currentFragment : Fragment? = supportFragmentManager.findFragmentById(R.id.frame)

        if(currentFragment is Fragment1){
            finish()
        }

        if(currentFragment is Fragment3){
            //then we need to land to Fragment2
            val returnedFragment = supportFragmentManager.findFragmentByTag(Fragment2::class.simpleName)

            if(returnedFragment != null){
                //means it is created before.. we just normally go back.
                super.onBackPressed()
            }
            else{
                //means it is not created anytime.. we might came here from notification. so lets create it.
                supportFragmentManager.popBackStackImmediate(currentFragment.id,0)
                stringFragmentName = "fragment2"
                loadFragment(false)
            }
        }

        if(currentFragment is Fragment2){
            //then we need to land to Fragment1
            val returnedFragment = supportFragmentManager.findFragmentByTag(Fragment1::class.simpleName)

            if(returnedFragment != null){
                //means it is created before.. we just normally go back.
                super.onBackPressed()
            }
            else{
                //means it is not created anytime.. we might came here from notification. so lets create it.
                supportFragmentManager.popBackStackImmediate(currentFragment.id,0)
                stringFragmentName = "fragment1"
                loadFragment(false)
            }
        }
    }

    fun goToFragment1(addToBackStack: Boolean) {
        addFragment(Fragment1(), R.id.frame, addToBackStack, false, null)
        println("FRAGG : backEntry count " +supportFragmentManager.backStackEntryCount)
    }

    fun goToFragment2(addToBackStack: Boolean){
        addFragment(Fragment2(), R.id.frame, addToBackStack, false, null)
        println("FRAGG : backEntry count " +supportFragmentManager.backStackEntryCount)
    }

    fun goToFragment3(addToBackStack: Boolean){
        addFragment(Fragment3(), R.id.frame, addToBackStack, false, null)
        println("FRAGG : backEntry count " +supportFragmentManager.backStackEntryCount)
    }

    fun loadFragment(addToBackStack : Boolean) {

        if(stringFragmentName.equals("fragment1")){
            goToFragment1(addToBackStack)
        }
        else if(stringFragmentName.equals("fragment2")){
            goToFragment2(addToBackStack)
        }
        else if(stringFragmentName.equals("fragment3")){
            goToFragment3(addToBackStack)
        }

        println("FRAGG : backEntry count " +supportFragmentManager.backStackEntryCount)

    }

    fun startNotif(){

        val notificationIntent = Intent(this, MainActivity::class.java)
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        notificationIntent.putExtra("INTENT_FRAG_NAME", "fragment3")

        // from stackoverflow.com/questions/11551195/intent-from-notification-does-not-have-extras
        //showFullQuoteIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // stackoverflow.com/questions/7370324/notification-passes-old-intent-extras/9330144
        // @see http://developer.android.com/reference/android/app/PendingIntent.html#FLAG_UPDATE_CURRENT

        // both of these approaches now work: FLAG_CANCEL, FLAG_UPDATE; the uniqueInt may be the real solution.
        //PendingIntent pendingIntent = PendingIntent.getActivity(this, uniqueInt, showFullQuoteIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        val uniqueInt = (System.currentTimeMillis() and 0xfffffff).toInt()
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            val channelId = "some_channel_id"
            val channelName = "Some Channel"

            val importance = NotificationManager.IMPORTANCE_LOW

            val notificationChannel = NotificationChannel(channelId, channelName, importance)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            notificationManager.createNotificationChannel(notificationChannel)

            val notification = Notification.Builder(this@MainActivity,channelId)
                .setContentTitle("Some Message")
                .setContentText("You've received new messages!")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()

            notificationManager.notify(50, notification)
        }


    }
}
