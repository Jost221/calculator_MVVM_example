package com.example.calculator.ui.main

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    onHistoryClick: () -> Unit,
    viewModel: MainViewModel = koinViewModel()
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CalculatorTopBar(
                onCloseClick = { (context as? Activity)?.finish() },
                onHistoryClick = onHistoryClick
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                Log.i("construct", "AllView")

                Spacer(modifier = Modifier.weight(1f))

                CalculatorDisplay(
                    stateFlow = viewModel.uiState
                )

                CalculatorKeyboard(
                    onButtonClick = viewModel::onButtonClick
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorTopBar(
    onCloseClick: () -> Unit,
    onHistoryClick: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onCloseClick) {
                Text(
                    text = "✕",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        title = {
            Text(
                text = "Калькулятор",
                fontWeight = FontWeight.SemiBold
            )
        },
        actions = {
            IconButton(onClick = onHistoryClick) {
                Text(
                    text = "☰",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
        )
    )
}

@Composable
fun CalculatorDisplay(
    stateFlow: StateFlow<UiState>,
    modifier: Modifier = Modifier
) {
    val uiState by stateFlow.collectAsStateWithLifecycle()

    Log.i("construct", "CalculatorDisplay")
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = uiState.preview,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = uiState.expression.ifEmpty { "0" },
            fontSize = 48.sp,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1
        )
    }
}

@Composable
fun CalculatorKeyboard(
    onButtonClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val buttons = listOf(
        listOf("AC", "+/-", "%", "÷"),
        listOf("7",  "8",  "9", "×"),
        listOf("4",  "5",  "6", "-"),
        listOf("1",  "2",  "3", "+"),
        listOf("0",  ".",  "⌫", "="),
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        buttons.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                row.forEach { label ->
                    CalcButton(
                        label = label,
                        modifier = Modifier.weight(1f),
                        onClick = { onButtonClick(label) }
                    )
                }
            }
        }
    }
}

@Composable
fun CalcButton(
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val containerColor = when (label) {
        "÷", "×", "-", "+", "=" -> MaterialTheme.colorScheme.primary
        "AC", "+/-", "%", "⌫"   -> MaterialTheme.colorScheme.secondary
        else                     -> MaterialTheme.colorScheme.surface
    }

    Button(
        onClick = onClick,
        modifier = modifier.aspectRatio(1f),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Text(
            text = label,
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
