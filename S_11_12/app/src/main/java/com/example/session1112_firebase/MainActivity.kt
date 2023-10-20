@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.session1112_firebase

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        addData(db)
        setContent {
            Session1112FirebaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    saveButton(db)
                }
            }
        }
    }
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

fun addData(db: FirebaseFirestore){
    val data = hashMapOf(
        "Name" to "Quagmire",
        "Age" to 32
    )

    db.collection("class_1")
        .add(data)
        .addOnSuccessListener { documentReference ->
            Log.d("data", "DocumentSnapshot with ID: ${documentReference.id}")
        }
        .addOnFailureListener{ e ->
            Log.w("data", "Error adding document", e)
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun saveButton(db: FirebaseFirestore, modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var yob by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = name,
            label = { Text("Tell me your Name") },
            onValueChange = { name = it }
        )
        OutlinedTextField(
            value = age,
            label = { Text("Tell me your Age") },
            onValueChange = { age = it }
        )
        OutlinedTextField(
            value = yob,
            label = { Text("Tell me your birth year") },
            onValueChange = { yob = it }
        )
        Button(onClick = { addDataInput(name, age.toInt(), yob.toInt(), db) }) {
            Text(text = "Save")
        }
    }
}

fun addDataInput(name: String, age: Int, yob: Int, db: FirebaseFirestore){
    val inputtedDdata = hashMapOf(
        "Name" to name,
        "Age" to age,
        "Year" to yob
    )
    db.collection("class_record")
        .add(inputtedDdata)
        .addOnSuccessListener { documentReference ->
            Log.d("data", "DocumentSnapshot with ID: ${documentReference.id}")
        }
        .addOnFailureListener { e ->
            Log.w("data", "Error adding document", e)
        }
}