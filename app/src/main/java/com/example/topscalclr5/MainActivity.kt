package com.example.topscalclr5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.topscalclr5.ui.theme.TopsCalcLR5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipsApp()
        }
    }
}

@Composable
fun DemoSlider(sliderPosition: Float, onPositionChange: (Float) -> Unit ) {
    Slider(
        modifier = Modifier.padding(10.dp),
        valueRange = 0f..25f,
        value = sliderPosition,
        onValueChange = { onPositionChange(it) }
    )
}
@Preview(showBackground = true)
@Composable
fun TipsApp() {
    var sum by remember { mutableStateOf("") } // сумма заказа
    var bludo by remember { mutableStateOf("") } // количество блюд
    var tips by remember { mutableStateOf(0f) }
    var skidka by remember { mutableIntStateOf(1) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Калькулятор чаевых")

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = sum,
            onValueChange = { newValue ->
                sum = newValue
            },
            label = { Text("Сумма заказа") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = bludo,
            onValueChange = { newValue ->
                bludo = newValue
                val count_bludo = newValue.toIntOrNull()
                skidka = when { // условие для кол-ва блюд
                    count_bludo == null -> 0
                    count_bludo in 1..2 -> 3
                    count_bludo in 3..5 -> 5
                    count_bludo in 6..10 -> 7
                    count_bludo > 10 -> 10
                    else -> 0
                }
            },
            label = { Text("Количество блюд") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        DemoSlider(
            sliderPosition = tips,
            onPositionChange = { tips = it }
        )
        Text("Чаевые: ${tips.toInt()}%")

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Скидка")
            Spacer(modifier = Modifier.width(10.dp))
            RadioButton(
                selected = skidka == 3,
                onClick = null
            )
            Text("3%")
            Spacer(modifier = Modifier.width(10.dp))
            RadioButton(
                selected = skidka == 5,
                onClick = null
            )
            Text("5%")
            Spacer(modifier = Modifier.width(10.dp))
            RadioButton(
                selected = skidka == 7,
                onClick = null
            )
            Text("7%")
            Spacer(modifier = Modifier.width(10.dp))
            RadioButton(
                selected = skidka == 10,
                onClick = null
            )
            Text("10%")
        }

    }
}