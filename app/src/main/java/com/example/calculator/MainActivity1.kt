package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            Scaffold(
//                content = { padding: PaddingValues ->
//                    Column(
//                        modifier = Modifier
//                            .padding(padding)
//                            .background(Color.LightGray)
//                            .fillMaxSize()
//                        ,
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.SpaceBetween
//                    ) {
//                        Header(padding)
//                        Result()
//                        NumbersKeyboard()
//                    }
//                }
//            )
//        }
//    }
//}

@Composable
fun Header(padding: PaddingValues){
    Row() {
        Button(
            onClick= {},
            modifier = Modifier.width(400.dp)
        ) {
            Text("⏲")
        }
    }

}

@Composable
fun Result(){
    Column() {
        TextField(
            value = "",
            readOnly = true,
            onValueChange = {},
            textStyle = TextStyle(fontSize = 28.em),
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )

        TextField(
            value = "",
            placeholder = { Text("result")},
            readOnly = true,
            onValueChange = {},
            textStyle = TextStyle(textAlign = TextAlign.End),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        )
    }

}

@Composable
fun NumbersKeyboard(){
    Column(
        modifier =
            Modifier.padding(horizontal = 5.dp)
    ) {
        NumberRow("AC", "^2", "%", "/")
        NumberRow("7", "8", "9", "*")
        NumberRow("4", "5", "6", "-")
        NumberRow("1", "2", "3", "+")
        NumberRow("0", ".", "CE", "=")

    }
}

@Composable
fun NumberRow(first: String, second: String, theerd: String, forth: String){
    Row() {
        ButtonKeyboard(first);
        ButtonKeyboard(second);
        ButtonKeyboard(theerd)
        ButtonKeyboard(forth)
    }

}

@Composable
fun ButtonKeyboard(text: String){
    Button(
        onClick = {},
        modifier = Modifier
            .width(90.dp)
            .height(90.dp)
    ){
        Text(text)
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=1080px,height=2340px,dpi=440",
    showSystemUi = true
)
fun HeaderPreview(){
    Scaffold(
        content = { padding: PaddingValues ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .background(Color.LightGray)
                    .fillMaxSize()
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Header(padding)
                Result()
                NumbersKeyboard()
            }
        }
    )
}