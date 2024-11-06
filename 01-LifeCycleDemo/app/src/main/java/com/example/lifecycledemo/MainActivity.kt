package com.example.lifecycledemo

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val channelId = "lifecycle_channel_id"
    private var notificationId = 100

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.d("App", "Permissão concedida para enviar notificações.")
        } else {
            Log.d("App", "Permissão negada para enviar notificações.")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            checkNotificationPermission()
        }

        Log.d("App", "OnCreate chamado")
        sendNotification("OnCreate chamado", "O método onCreate foi executado.")
    }

    override fun onStart() {
        super.onStart()
        Log.d("App", "OnStart chamado")
        sendNotification("OnStart chamado", "O método onStart foi executado.")
    }

    override fun onResume() {
        super.onResume()
        Log.d("App", "OnResume chamado")
        sendNotification("OnResume chamado", "O método onResume foi executado.")
    }

    override fun onPause() {
        super.onPause()
        Log.d("App", "OnPause chamado")
        sendNotification("OnPause chamado", "O método onPause foi executado.")
    }

    override fun onStop() {
        super.onStop()
        Log.d("App", "OnStop chamado")
        sendNotification("OnStop chamado", "O método onStop foi executado.")
    }

    override fun onDestroy() {
        Log.d("App", "OnDestroy chamado")
        sendNotification("OnDestroy chamado", "O método onDestroy foi executado.")
        super.onDestroy()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Lifecycle Channel" // Nome do canal
            val descriptionText = "Canal para notificações do ciclo de vida." // Descrição
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun checkNotificationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private fun sendNotification(title: String, message: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            Log.d("App", "Permissão de notificação não concedida.")
            return
        }

        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId++, builder.build())
        }
    }
}
