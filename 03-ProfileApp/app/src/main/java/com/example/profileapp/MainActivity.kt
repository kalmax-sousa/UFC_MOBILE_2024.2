package com.example.profileapp

import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val profileImage = findViewById<ImageView>(R.id.profileView)
        val nameText = findViewById<TextView>(R.id.name)
        val descriptionText = findViewById<TextView>(R.id.descriptionText)
        val currentJob = findViewById<TextView>(R.id.currentJobText)
        val experienceLeyout = findViewById<LinearLayout>(R.id.experienceLayout)

        nameText.text = "Kalmax Sousa"
        descriptionText.text = "Desenvolvedor FullStack\nEstudande de Sistemas de Informação"
        currentJob.text = "Bolsista PID - EDA e EDA"

        val experiences = listOf(
            "DeV. FullStack (Estágio) - InsideOne",
            "Bolsista em DevSecOps - Compass.UOL",
            "DeV. FullStack (Estágio) - InsideOne",
        )

        for(experience in experiences){
            val textView = TextView(this)
            textView.text = experience
            textView.textSize = 16f
            textView.gravity = Gravity.CENTER
            experienceLeyout.addView(textView)
        }

        profileImage.setOnClickListener {
            Toast.makeText(this, "Foto de Kalmax", Toast.LENGTH_SHORT).show()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}