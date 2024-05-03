package tz.co.hosannahighertech.myfirstgeminiapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tz.co.hosannahighertech.myfirstgeminiapp.BuildConfig
import tz.co.hosannahighertech.myfirstgeminiapp.R


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var displayText by remember { mutableStateOf("") }
    var promptText by remember { mutableStateOf("") }

    var isEnabled by remember { mutableStateOf(true) }
    val scroll = rememberScrollState(0)

    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope()

    /**
     * gemini-pro => Text only
     * gemini-pro-vision => Text and Images
     *
     */
    val generativeModel = GenerativeModel(modelName = "gemini-pro", apiKey = BuildConfig.AI_API_KEY)

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
                        .verticalScroll(scroll)
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
                            .weight(1f),
                        enabled = isEnabled
                    )

                    Button(
                        onClick = {
                            isEnabled = false

                            coroutineScope.launch(Dispatchers.IO) {
                                val response = generativeModel.generateContent(promptText)

                                displayText = "[$promptText]\n"
                                promptText = ""

                                response.text?.let { responseText ->
                                    displayText += responseText
                                }

                                isEnabled = true
                            }

                            //dismiss keyboard
                            keyboardController?.hide()
                        },
                        enabled = isEnabled
                    ) {
                        Text(text = stringResource(id = R.string.action_send))
                    }
                }
            }
        })
}