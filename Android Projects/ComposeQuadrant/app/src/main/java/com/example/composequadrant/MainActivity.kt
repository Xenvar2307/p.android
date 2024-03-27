package com.example.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign.Companion.Justify
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuadrantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    InfoQuad()
                }
            }
        }
    }
}

@Composable
fun InfoQuad()
{
    Column (
        Modifier.fillMaxWidth()
    ) {
        Row (
            Modifier.weight(1f)
        ) {
            Info(name = "Text", body = "Displays text and follows the recommended Material Design guidelines.",
                color = Color(0xFFEADDFF), modifier = Modifier.weight(1f))
            Info(name = "Image", body = "Creates a composable that lays out and draws a given Painter class object.",
                color = Color(0xFFD0BCFF), modifier = Modifier.weight(1f))
        }
        Row (modifier = Modifier
            .weight(1f)) {
            Info(name = "Row", body = "A layout composable that places its children in a horizontal sequence.",
                color = Color(0xFFB69DF8), modifier = Modifier.weight(1f))
            Info(name = "Column", body = "A layout composable that places its children in a vertical sequence.",
                color = Color(0xFFF6EDFF), modifier = Modifier.weight(1f))
        }
    }

}

@Composable
fun Info(name: String = "No name sent",body: String = "No body sent",color: Color, modifier: Modifier = Modifier) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(color = color)
            .padding(16.dp)
    ){
        Text(
            text = "$name composable",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
        )
        Text(
            text = body,
            textAlign = Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InfoPreview() {
    ComposeQuadrantTheme {
        InfoQuad()

    }
}