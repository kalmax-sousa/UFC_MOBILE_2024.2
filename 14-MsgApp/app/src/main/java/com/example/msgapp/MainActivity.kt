package com.example.msgapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.msgapp.data.local.database.AppDatabase
import com.example.msgapp.repository.MessageRepository
import com.example.msgapp.ui.theme.MsgAppTheme
import com.example.msgapp.ui.view.MessageApp
import com.example.msgapp.viewModel.MessageViewModel
import com.example.msgapp.viewModel.MessageViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "messages-db"
        ).fallbackToDestructiveMigration().build()
        
        val repository = MessageRepository(db.messageDao())

        setContent {
            val viewModel: MessageViewModel = viewModel(factory = MessageViewModelFactory(repository))
            MessageApp(viewModel = viewModel)
        }
    }
}