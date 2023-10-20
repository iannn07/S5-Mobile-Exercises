package com.example.session1112_firebase

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.session1112_firebase.ui.theme.Session1112FirebaseTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val db = Firebase.firestore
        super.onCreate(savedInstanceState)
        getData(db)
        getDetailedData(db)
        setContent {
            Session1112FirebaseTheme {
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
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

fun getData(db:FirebaseFirestore){
    db.collection("class")
        .get()
        .addOnSuccessListener { result ->
            for (document in result) {
                Log.d("data", "${document.id} => ${document.data}")
            }
        }
        .addOnFailureListener { exception ->
            Log.w("data", "Error getting documents.", exception)
        }
}

fun getDetailedData(db:FirebaseFirestore){
    db.collection("class")
        .document("QIR3GPyeRB3zn3GhKixO")
        .collection("organization")
        .get()
        .addOnSuccessListener { result ->
            for (document in result) {
                Log.d("data", "${document.id} => ${document.data}")
            }
        }
        .addOnFailureListener { exception ->
            Log.w("data", "Error getting documents.", exception)
        }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Session1112FirebaseTheme {
        Greeting("Android")
    }
}