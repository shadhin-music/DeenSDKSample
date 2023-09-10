package com.deenislamsdk

import android.content.res.Configuration
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.deenislam.sdk.DeenSDKCallback
import com.deenislam.sdk.DeenSDKCore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeenActivity : AppCompatActivity(), DeenSDKCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val msisdn:EditText = findViewById(R.id.phone_number)
        val authBtn:AppCompatButton = findViewById(R.id.login)
        val initSDKbtn:AppCompatButton = findViewById(R.id.initSDKbtn)
        val tasbeehBtn:AppCompatButton = findViewById(R.id.tasbeeh)
        val forbiddenBtn:AppCompatButton = findViewById(R.id.prayertime)
        val prayernotifyon:AppCompatButton = findViewById(R.id.prayernotifyon)
        val prayernotifyoff:AppCompatButton = findViewById(R.id.prayernotifyoff)
        val checkNotifyBtn:AppCompatButton = findViewById(R.id.checkNotifyBtn)


        initSDKbtn.setOnClickListener {
            if(msisdn.text.isNotEmpty()){
                DeenSDKCore.initDeen(
                    this,
                    msisdn.text.toString(),
                    this@DeenActivity
                )
            }else{
                Toast.makeText(this,"Enter number", Toast.LENGTH_SHORT).show()
            }
        }


        authBtn.setOnClickListener {
            if(msisdn.text.isNotEmpty()){
                DeenSDKCore.openDeen()
            }else{
                Toast.makeText(this,"Enter number", Toast.LENGTH_SHORT).show()
            }
        }

        tasbeehBtn.setOnClickListener {
            if(msisdn.text.isNotEmpty()){
                DeenSDKCore.openFromRC("tasbeeh")
            }else{
                Toast.makeText(this,"Enter number", Toast.LENGTH_SHORT).show()
            }
        }

        forbiddenBtn.setOnClickListener {
            if(msisdn.text.isNotEmpty()){
                DeenSDKCore.openFromRC("prayer_time")
            }else{
                Toast.makeText(this,"Enter number", Toast.LENGTH_SHORT).show()
            }
        }

        prayernotifyon.setOnClickListener {
            if(msisdn.text.isNotEmpty()){
                DeenSDKCore.prayerNotification(true)
            }else{
                Toast.makeText(this,"Enter number", Toast.LENGTH_SHORT).show()
            }
        }

        prayernotifyoff.setOnClickListener {
            if(msisdn.text.isNotEmpty()){
                DeenSDKCore.prayerNotification(false)
            }else{
                Toast.makeText(this,"Enter number", Toast.LENGTH_SHORT).show()
            }
        }

        checkNotifyBtn.setOnClickListener {
            val baseContext = this
            if(msisdn.text.isNotEmpty()){
                CoroutineScope(Dispatchers.Main).launch {
                    if(DeenSDKCore.isPrayerNotificationEnabled(baseContext))
                        Toast.makeText(applicationContext,"Notification is enabled", Toast.LENGTH_SHORT).show()
                    else
                        Toast.makeText(applicationContext,"Notification is disabled", Toast.LENGTH_SHORT).show()

                }
            }else{
                Toast.makeText(this,"Enter number", Toast.LENGTH_SHORT).show()
            }
        }

        testCrash()


    }

    fun testCrash() {
        val mutableMap: MutableMap<String, Boolean> = MyMapClass.getMap()
    }

    override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean,
                                               newConfig: Configuration
    ) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)
        if (isInPictureInPictureMode) {
            // Hide the full-screen UI (controls, etc.) while in PiP mode.
        } else {
            // Restore the full-screen UI.
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        DeenSDKCore.destroySDK()
    }

    override fun onDeenSDKInitSuccess() {
        Toast.makeText(this, "Auth Success Callback", Toast.LENGTH_SHORT).show()
    }

    override fun onDeenSDKInitFailed() {
        Toast.makeText(this, "Auth Failed Callback", Toast.LENGTH_SHORT).show()
    }

    override fun onDeenSDKRCFailed() {
        Toast.makeText(this, "RC code failed", Toast.LENGTH_SHORT).show()
    }

    override fun DeenPrayerNotificationOn() {
        Toast.makeText(this, "Prayer notification enable Callback", Toast.LENGTH_SHORT).show()
    }

    override fun DeenPrayerNotificationOff() {
        Toast.makeText(this, "Prayer notification disable Callback", Toast.LENGTH_SHORT).show()
    }

    override fun DeenPrayerNotificationFailed() {
        Toast.makeText(this, "Prayer notification failed Callback", Toast.LENGTH_SHORT).show()
    }
}