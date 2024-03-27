package com.example.composearticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion.Justify
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composearticle.ui.theme.ComposeArticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeArticleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Article(name = stringResource(R.string.article_title),
                        intro = stringResource(R.string.article_intro),
                        body = stringResource(R.string.article_body))
                }
            }
        }
    }
}

@Composable
fun Article(name: String = "No title sent",intro: String = "No intro sent",
            body: String = "No article body sent" ,modifier: Modifier = Modifier) {
    Column {
        val image = painterResource(R.drawable.bg_compose_background)
        Image(
            painter = image,
            contentDescription = null
        )
        Text(
            text = name,
            fontSize = 24.sp,
            modifier = Modifier.
                padding(16.dp)
        )
        Text(
            text = intro,
            textAlign = Justify,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)

        )
        Text(
            text = body,
            textAlign = Justify,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArticlePreview() {
    ComposeArticleTheme {
        Article(name = stringResource(R.string.article_title),
            intro = stringResource(R.string.article_intro),
            body = stringResource(R.string.article_body))
    }
}