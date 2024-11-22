package com.example.counterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.counterapp.ui.theme.CounterAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Habilitar o modo Edge-to-Edge
        enableEdgeToEdge()

        // Configurar a cor da barra de status
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
        window.statusBarColor = android.graphics.Color.WHITE // Cor de fundo da barra de status
        setContent {
            CounterApp()
        }
    }
}

@Composable
fun CounterApp() {
    var result by remember {mutableStateOf(0.0)}
    var display by remember {mutableStateOf(" ")}
    var input by remember { mutableStateOf(" ")}
    var historic by remember { mutableStateOf(" ")}
    var lastOperator by remember { mutableStateOf(" ") }

    fun calc() {
        if (lastOperator ==  " "){
            result += (display.toDoubleOrNull() ?: 0.0)
        } else if (lastOperator == "+"){
            result += (display.toDoubleOrNull() ?: 0.0)
        } else if (lastOperator == "-") {
            result -= (display.toDoubleOrNull() ?: 0.0)
        } else if(lastOperator == "X"){
            result *= (display.toDoubleOrNull() ?: 1.0)
        } else if (lastOperator == "/") {
            result /= (display.toDoubleOrNull() ?: 1.0)
        }
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom,
    ) {
        Text(
            text = historic,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            color = Color.LightGray
        )

        Text(
            text = display,
            fontSize = 44.sp,
            textAlign = TextAlign.Right,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ){
            Button(
                onClick = {
                    display += "7"
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(100, 181, 246)
                ),
                modifier = Modifier
                    .weight(1f)
                    .size(90.dp)
                    .clip(CircleShape),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "7",
                    color = Color.White,
                    fontSize = 24.sp
                )
            }

            Button(
                onClick = {
                    display += "8"
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(100, 181, 246)
                ),
                modifier = Modifier
                    .weight(1f)
                    .size(90.dp)
                    .clip(CircleShape),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "8",
                    color = Color.White,
                    fontSize = 24.sp
                )
            }

            Button(
                onClick = {
                    display += "9"
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(100, 181, 246)
                ),
                modifier = Modifier
                    .weight(1f)
                    .size(90.dp)
                    .clip(CircleShape),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "9",
                    color = Color.White,
                    fontSize = 24.sp
                )
            }

            Button(
                onClick = {
                    if(display == " "){
                        lastOperator = "X"
                        historic = historic.dropLast(2) + "X "
                    } else {
                        calc()
                        lastOperator = "X"
                        historic += "$display X "
                        display = " "
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(red = 255, green = 165, blue = 0)
                ),
                modifier = Modifier
                    .weight(1f)
                    .size(90.dp)
                    .clip(CircleShape),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "X",
                    color = Color.White,
                    fontSize = 24.sp
                )
            }
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ){
            Button(
                onClick = {
                    display += "4"
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(100, 181, 246)
                ),
                modifier = Modifier
                    .weight(1f)
                    .size(90.dp)
                    .clip(CircleShape),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "4",
                    color = Color.White,
                    fontSize = 24.sp
                )
            }

            Button(
                onClick = {
                    display += "5"
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(100, 181, 246)
                ),
                modifier = Modifier
                    .weight(1f)
                    .size(90.dp)
                    .clip(CircleShape),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "5",
                    color = Color.White,
                    fontSize = 24.sp
                )
            }

            Button(
                onClick = {
                    display += "6"
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(100, 181, 246)
                ),
                modifier = Modifier
                    .weight(1f)
                    .size(90.dp)
                    .clip(CircleShape),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "6",
                    color = Color.White,
                    fontSize = 24.sp
                )
            }

            Button(
                onClick = {
                    if(display == " "){
                        lastOperator = "-"
                        historic = historic.dropLast(2) + "- "
                    } else {
                        calc()
                        lastOperator = "-"
                        historic += "$display - "
                        display = " "
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(red = 255, green = 165, blue = 0)
                ),
                modifier = Modifier
                    .weight(1f)
                    .size(90.dp)
                    .clip(CircleShape)
                    .background(Color.Blue),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "-",
                    color = Color.White,
                    fontSize = 24.sp
                )
            }
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ){
            Button(
                onClick = {
                    display += "1"
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(100, 181, 246)
                ),
                modifier = Modifier
                    .weight(1f)
                    .size(90.dp)
                    .clip(CircleShape),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "1",
                    color = Color.White,
                    fontSize = 24.sp
                )
            }

            Button(
                onClick = {
                    display += "2"
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(100, 181, 246)
                ),
                modifier = Modifier
                    .weight(1f)
                    .size(90.dp)
                    .clip(CircleShape),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "2",
                    color = Color.White,
                    fontSize = 24.sp
                )
            }

            Button(
                onClick = {
                    display += "3"
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(100, 181, 246)
                ),
                modifier = Modifier
                    .weight(1f)
                    .size(90.dp)
                    .clip(CircleShape),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "3",
                    color = Color.White,
                    fontSize = 24.sp
                )
            }

            Button(
                onClick = {
                    if(display == " "){
                        lastOperator = "+"
                        historic = historic.dropLast(2) + "+ "
                    } else {
                        calc()
                        lastOperator = "+"
                        historic += "$display + "
                        display = " "
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(red = 255, green = 165, blue = 0)
                ),
                modifier = Modifier
                    .weight(1f)
                    .size(90.dp)
                    .clip(CircleShape),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "+",
                    color = Color.White,
                    fontSize = 24.sp
                )
            }
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ){
            Button(
                onClick = {
                    result = 0.0
                    display = " "
                    historic = " "
                    lastOperator = " "
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(139,0,0)
                ),
                modifier = Modifier
                    .weight(1f)
                    .size(90.dp)
                    .clip(CircleShape),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "C",
                    fontSize = 24.sp,
                    color = Color.White
                )
            }

            Button(
                onClick = {
                    display += "0"
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(100, 181, 246)
                ),
                modifier = Modifier
                    .weight(1f)
                    .size(90.dp)
                    .clip(CircleShape),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "0",
                    color = Color.White,
                    fontSize = 24.sp
                )
            }

            Button(
                onClick = {
                    if(display == " "){
                        lastOperator = "/"
                        historic = historic.dropLast(2) + "/ "
                    } else {
                        calc()
                        lastOperator = "/"
                        historic += "$display / "
                        display = " "
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(red = 255, green = 165, blue = 0)
                ),
                modifier = Modifier
                    .weight(1f)
                    .size(90.dp)
                    .clip(CircleShape),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "/",
                    color = Color.White,
                    fontSize = 24.sp
                )
            }

            Button(
                onClick = {
                    if(lastOperator != " ") {
                        calc()
                        lastOperator = " "
                        historic = " "
                        display = result.toString()
                        result = 0.0
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(255, 131, 0)
                ),
                modifier = Modifier
                    .weight(1f)
                    .size(90.dp)
                    .clip(CircleShape),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "=",
                    color = Color.White,
                    fontSize = 24.sp
                )
            }
        }
    }
}