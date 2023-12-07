package com.example.zad3

import android.inputmethodservice.Keyboard
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zad3.ui.theme.Zad3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Zad3Theme {
                val viewModel = viewModel<CalculatorViewModel>()
                val state = viewModel.state
                val buttonSpacing = 8.dp
                Calculator(state = state,
                    buttonSpacing = buttonSpacing,
                    onAction = viewModel::onAction,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black))
            }
        }
    }
}

@Preview
@Composable
fun CalculatorPreview(){
    val viewModel = viewModel<CalculatorViewModel>()
    val state = viewModel.state
    val buttonSpacing = 8.dp
    Calculator(state = state,
        buttonSpacing = buttonSpacing,
        onAction = viewModel::onAction,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black))

}

@Composable
fun Calculator(
    state: CalculatorState,
    buttonSpacing: Dp = 8.dp,
    onAction: (CalculatorAction) -> Unit,
    modifier: Modifier = Modifier
){
    Box(modifier = modifier)
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ){
            Text (
                text = "Enter number1, then operation, then number2 and click '='",
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                color = Color.White

            )
            Text(
                text = state.number1 + (state.operation?.symbol ?: "") + state.number2,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                fontSize = 32.sp,
                maxLines = 2,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
            //row 1
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ){
                CalculatorButton(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .aspectRatio(2f)
                        .weight(2f),
                    symbol = "AC",
                    onClick = {
                        onAction(CalculatorAction.Clear)
                    }
                )

                CalculatorButton(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "Del",
                    onClick = {
                        onAction(CalculatorAction.Delete)
                    }
                )

                CalculatorButton(
                    modifier = Modifier
                        .background(Color.Green)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "/",
                    onClick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
                    }
                )
            }
            //row 2
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ){
                CalculatorButton(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "7",
                    onClick = {
                        onAction(CalculatorAction.Number(7))
                    }
                )

                CalculatorButton(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "8",
                    onClick = {
                        onAction(CalculatorAction.Number(8))
                    }
                )

                CalculatorButton(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "9",
                    onClick = {
                        onAction(CalculatorAction.Number(9))
                    }
                )

                CalculatorButton(
                    modifier = Modifier
                        .background(Color.Green)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "*",
                    onClick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
                    }
                )


            }
            //row3
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ){
                CalculatorButton(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "4",
                    onClick = {
                        onAction(CalculatorAction.Number(4))
                    }
                )

                CalculatorButton(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "5",
                    onClick = {
                        onAction(CalculatorAction.Number(5))
                    }
                )

                CalculatorButton(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "6",
                    onClick = {
                        onAction(CalculatorAction.Number(6))
                    }
                )

                CalculatorButton(
                    modifier = Modifier
                        .background(Color.Green)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "-",
                    onClick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))
                    }
                )


            }

            //row 4
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ){
                CalculatorButton(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "1",
                    onClick = {
                        onAction(CalculatorAction.Number(1))
                    }
                )

                CalculatorButton(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "2",
                    onClick = {
                        onAction(CalculatorAction.Number(2))
                    }
                )

                CalculatorButton(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "3",
                    onClick = {
                        onAction(CalculatorAction.Number(3))
                    }
                )

                CalculatorButton(
                    modifier = Modifier
                        .background(Color.Green)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "+",
                    onClick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Add))
                    }
                )


            }
            //row 5
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ){
                CalculatorButton(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(2f)
                        .weight(2f),
                    symbol = "0",
                    onClick = {
                        onAction(CalculatorAction.Number(0))
                    }
                )

                CalculatorButton(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = ".",
                    onClick = {
                        onAction(CalculatorAction.Decimal)
                    }
                )

                CalculatorButton(
                    modifier = Modifier
                        .background(Color.Green)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "=",
                    onClick = {
                        onAction(CalculatorAction.Result)
                    }
                )
            }

        }
    }
}

@Composable
fun CalculatorButton (
    modifier: Modifier,
    symbol: String,
    onClick: () -> Unit
)
{
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(CircleShape)
            .clickable { onClick() }
            .then(modifier)
    ){
        Text(
            text = symbol,
            fontSize = 36.sp,
            color = Color.White //White?
        )
    }
}

sealed class CalculatorAction {
    data class Number(val number: Int): CalculatorAction()
    object Clear: CalculatorAction()
    object Delete: CalculatorAction()
    object Decimal: CalculatorAction()
    object Result: CalculatorAction()
    data class Operation (val operation: CalculatorOperation): CalculatorAction()
}

sealed class CalculatorOperation (val symbol: String){
    object Add: CalculatorOperation("+")
    object Subtract: CalculatorOperation("-")
    object Multiply: CalculatorOperation("*")
    object Divide: CalculatorOperation("/")
}

data class CalculatorState(
    val number1: String = "",
    val number2: String = "",
    val operation: CalculatorOperation? = null
)

class CalculatorViewModel: ViewModel() {
    var state by mutableStateOf(CalculatorState())
        private set

    fun onAction(action: CalculatorAction) {
        when(action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Clear -> state = CalculatorState()
            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Result -> calcResult()
            is CalculatorAction.Delete -> deleteLast()
        }
    }

    private fun deleteLast() {
        when {
            state.number2.isNotBlank() -> state = state.copy(
                number2 = state.number2.dropLast(1)
            )
            state.operation != null -> state = state.copy (operation = null)
            state.number1.isNotBlank() -> state = state.copy (
                number1 = state.number1.dropLast(1)
            )
        }
    }

    private fun calcResult() {
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()
        if (number1 != null && number2 != null){
        val result = when (state.operation) {
            is CalculatorOperation.Add -> number1 + number2
            is CalculatorOperation.Subtract -> number1 - number2
            is CalculatorOperation.Multiply -> number1 * number2
            is CalculatorOperation.Divide -> number1 / number2
            null -> return
        }
            state = state.copy (
                number1 = result.toString(),
                number2 = "",
                operation = null
            )
        }
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if (state.number1.isNotBlank())
        {
            state = state.copy(operation = operation)
        }
    }
    private fun enterDecimal() {
    if (state.operation == null && !state.number1.contains(".")
        && state.number1.isNotBlank())
    {
        state = state.copy (number1 = state.number1 + ".")
        return
    }

        if (!state.number2.contains(".")
            && state.number2.isNotBlank()) {
            state = state.copy (number2 = state.number2 + ".")

        }


    }

    private fun enterNumber(number: Int) {
        if(state.operation == null)
        {
            if (state.number1.length >= 8){
                return
            }
            state = state.copy(number1 = state.number1 + number)
            return
        }
        if(state.number2.length >= 8) {
            return
        }
        state = state.copy(number2 = state.number2 + number)
    }
}

