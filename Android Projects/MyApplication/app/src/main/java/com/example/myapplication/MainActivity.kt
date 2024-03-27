package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                GreetingImage(message = stringResource(R.string.happy_birthday_text),
                    from = stringResource(R.string.developer_signature)
                )
                }

            }
        }
    }
}

@Composable
fun GreetingImage(modifier: Modifier = Modifier, message: String, from: String) {
    val image = painterResource(R.drawable.androidparty)
    Box {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.5F
        )
        GreetingText(
            message = message,
            from = from,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )

    }
}


@Composable
fun GreetingText(modifier: Modifier = Modifier,
                 message: String = "No message sent",
                 from: String = "No author sent"
                 ){
    Column (
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = message,
            fontSize = 80.sp,
            lineHeight = 88.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = "from: $from",
            fontSize = 36.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview(){
    MyApplicationTheme {
        //GreetingText(message = "Happy Red Gobbo, Tomasz!",
        //    from = "Lukas")
        GreetingImage(message = "Happy Red Gobbo, Tomasz!",
            from = "Lukas")
    }
}

/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(color = Color.Magenta) {
        Text(
            text = "Hi, my name is $name!",
            modifier = modifier.padding(24.dp)
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Test preview"
)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Lukas")
    }
}*/