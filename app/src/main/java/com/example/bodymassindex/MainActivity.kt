package com.example.bodymassindex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bodymassindex.ui.theme.BodyMassIndexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BodyMassIndexTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Bmi()
                }
            }
        }
    }
}

@Composable
fun Bmi()
{
    var heightInput by remember { mutableStateOf("") }
    var WeightInput by remember { mutableStateOf("") }
    val height = heightInput.toFloatOrNull() ?:0.0f
    val weight = WeightInput.toIntOrNull() ?: 0
    val bmi = if (weight > 0 && height > 0) weight / (height * height) else 0.0

    Column() {
        Text(
            text = stringResource(R.string.Body_Mass_Index),
            fontSize = 40.sp,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp)
        )
        OutlinedTextField(
            label = { Text(text = stringResource(R.string.Height))},
            value = heightInput,
            onValueChange = {heightInput = it.replace(',','.')},
            singleLine = true,
            keyboardOptions = KeyboardOptions (keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()

        )
        OutlinedTextField(
            label = { Text(text = stringResource(R.string.Weight))},
            value = WeightInput,
            onValueChange = {WeightInput = it.replace(',','.')},
            singleLine = true,
            keyboardOptions = KeyboardOptions (keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(R.string.Result, String.format("%.2f", bmi).replace(',','.'))
        )



    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BodyMassIndexTheme {
        Bmi()

    }
}