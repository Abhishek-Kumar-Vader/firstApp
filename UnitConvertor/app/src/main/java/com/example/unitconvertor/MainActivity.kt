package com.example.unitconvertor

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.example.unitconvertor.ui.theme.UnitConvertorTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConvertorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){

    var inputvalue by remember {mutableStateOf("")}
    var ouputValue by remember {mutableStateOf("")}
    var inputUnit by remember {mutableStateOf("Meters")}
    var OutputUnit by remember {mutableStateOf("Meters")}
    var iExpanded  by remember {mutableStateOf(false)}
    var oExpanded  by remember {mutableStateOf(false)}
    val conversionFactor = remember { mutableStateOf(1.00) }
    val oConversionFactor = remember { mutableStateOf(1.00) }

//    val customTextStyle = TextStyle(
//        fontFamily= FontFamily.SansSerif,
//        fontSize=36.sp,
//        color= Color.White
//    )

    fun ConvertUnits(){
        val inputvalueDouble = inputvalue.toDoubleOrNull() ?:0.0
        val result = (inputvalueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() / 100.0
        ouputValue=result.toString()
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        // here the UI elements will be stacked below each other
        Text("Unit Convertor",style = MaterialTheme.typography.headlineLarge)
//        Text("Unit Convertor",style = customTextStyle)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputvalue, onValueChange ={inputvalue=it
            ConvertUnits()},label = {Text("Enter a Value")})
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            // inout box
            Box{
                //input button
                Button(onClick = { iExpanded=true }) {
                    Text(text=inputUnit)
                    Icon(Icons.Default.ArrowDropDown,contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded=false }) {
                    DropdownMenuItem(text = { Text("Centimetres")}, onClick = {
                        iExpanded=false
                        inputUnit="Centimetres"
                        conversionFactor.value=0.01
                        ConvertUnits()
                    })
                    DropdownMenuItem(text = { Text("Meters")}, onClick = {
                        iExpanded=false
                        inputUnit="Meters"
                        conversionFactor.value=1.0
                        ConvertUnits() })
                    DropdownMenuItem(text = { Text("Feet")}, onClick = {
                        iExpanded=false
                        inputUnit="Feet"
                        conversionFactor.value=0.3048
                        ConvertUnits() })
                    DropdownMenuItem(text = { Text("Millimeters")}, onClick = {
                        iExpanded=false
                        inputUnit="Millimeters"
                        conversionFactor.value=0.001
                        ConvertUnits() })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            // output box
            Box {
                //output button
                Button(onClick = { oExpanded=true }) {
                    Text(text=OutputUnit)
                    Icon(Icons.Default.ArrowDropDown,contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                    DropdownMenuItem(text = { Text("Centimetres")}, onClick = {
                        oExpanded=false
                        OutputUnit="Centimetres"
                        oConversionFactor.value=0.01
                        ConvertUnits() })
                    DropdownMenuItem(text = { Text("Meters")}, onClick = {
                        oExpanded=false
                        OutputUnit="Meters"
                        oConversionFactor.value=1.0
                        ConvertUnits() })
                    DropdownMenuItem(text = { Text("Feet")}, onClick = {
                        oExpanded=false
                        OutputUnit="Feet"
                        oConversionFactor.value=0.3048
                        ConvertUnits() })
                    DropdownMenuItem(text = { Text("Millimeters")}, onClick = {
                        oExpanded=false
                        OutputUnit="Millimeters"
                        oConversionFactor.value=0.001
                        ConvertUnits()
                    })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: $ouputValue $OutputUnit",
            style = MaterialTheme.typography.headlineMedium)
    }
}



@Preview(showBackground = true)
@Composable

fun UnitConverterPreview(){
    UnitConverter()
}