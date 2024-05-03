package tz.co.hosannahighertech.myfirstgeminiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import tz.co.hosannahighertech.myfirstgeminiapp.screens.MainScreen
import tz.co.hosannahighertech.myfirstgeminiapp.ui.theme.MyFirstGeminiAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFirstGeminiAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyFirstGeminiAppTheme {
        MainScreen(modifier = Modifier.fillMaxSize())
    }
}