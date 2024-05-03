package tz.co.hosannahighertech.myfirstgeminiapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import tz.co.hosannahighertech.myfirstgeminiapp.R


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var displayText by remember { mutableStateOf("") }
    var promptText by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                ),
            )
        }, content = {
            Column(
                modifier = modifier
                    .padding(it),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = displayText,
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                        .fillMaxWidth()
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = promptText,
                        onValueChange = { text ->
                            promptText = text
                        },
                        modifier = Modifier
                            .padding(4.dp)
                            .weight(1f)
                    )

                    Button(onClick = {
                        displayText += "$promptText\n"
                        promptText = ""

                        //dismiss keyboard
                        keyboardController?.hide()
                    }) {
                        Text(text = stringResource(id = R.string.action_send))
                    }
                }
            }
        })
}