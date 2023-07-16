package com.example.imageprocessing

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import com.example.imageprocessing.ui.theme.ImageProcessingTheme
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var imageUri by remember {
                mutableStateOf<Uri?>(null)
            }

            val pickMedia =
                rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                    if (uri != null) {
                        val imageFile = File(uri.toString())
                        //TODO progress imageFile

                        imageUri = uri
                    }
                }

            val launcher =
                rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {
                    if (it) {
                        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    }
                }

            ImageProcessingTheme {
                val context = LocalContext.current

                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.insert_image),
                        fontSize = 24.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(Modifier.height(8.dp))

                    Button(onClick = {
                        when (PackageManager.PERMISSION_GRANTED) {
                            ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                            ) -> {
                                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                            }
                            else -> {
                                launcher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                            }
                        }
                    }) {
                        Text(
                            text = "Select image ...",
                            fontSize = 12.sp,
                        )
                    }

                    Spacer(Modifier.height(8.dp))

                    AsyncImage(
                        model = imageUri,
                        contentDescription = null,
                    )
                }
            }
        }
    }
}