package com.example.session_7_8_compose

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.session_7_8_compose.ui.theme.Session_7_8_ComposeTheme

class MainActivity : ComponentActivity() {
    var num:Int =  0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Session_7_8_ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val num = remember {
        mutableStateOf(0)
    }

    Column (
        verticalArrangement = Arrangement.Center,
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            fontSize = 24.sp,
            text = "Hello $name",
            modifier = modifier,
            textAlign = TextAlign.Center
        )
        Text(
            fontSize = 50.sp,
            text = num.value.toString(),
            modifier = modifier,
            textAlign = TextAlign.Center
        )
        Button(onClick = { num.value += 1 }) {
            Text(text = "Plus 1")
        }
        Button(onClick = {
            if(num.value <= 0){
                num.value = 0
            } else if (num.value > 0){
                num.value -= 1
            }
        }) {
            Text(text = "Minus 1")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Session_7_8_ComposeTheme {
        Greeting("Android")
    }
}