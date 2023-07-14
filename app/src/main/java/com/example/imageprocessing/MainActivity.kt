package com.example.imageprocessing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imageprocessing.ui.theme.ImageProcessingTheme
import com.huhx.picker.constant.AssetPickerConfig
import com.huhx.picker.data.AssetInfo
import com.huhx.picker.data.PickerPermissions
import com.huhx.picker.view.AssetPicker

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageProcessingTheme {
                ImageVisualizationScreen()
            }
        }
    }
}


@Composable
fun ImageVisualizationScreen() {
    Column(
        modifier = Modifier.padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.insert_image),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(16.dp))

//        TODO: add a button which allow user to find image in physical device


    }
}

//@Composable
//fun ImagePicker(
//    onPicked: (List<AssetInfo>) -> Unit,
//    onClose: (List<AssetInfo>) -> Unit,
//) {
//    PickerPermissions(permissions = listOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)) {
//        AssetPicker(
//            assetPickerConfig = AssetPickerConfig(gridCount = 3),
//            onPicked = onPicked,
//            onClose = onClose
//        )
//    }
//}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ImageProcessingTheme {
        ImageVisualizationScreen()
    }
}