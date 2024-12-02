package com.umesh.funfactscompose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umesh.animalfactscompose.R

@Composable
fun TopBar(value: String) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = value, color = Color.Black, fontSize = 30.sp, fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo"
        )
    }
}

@Composable
fun TextComponent(
    textValue: String,
    textSize: TextUnit,
    textColor: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Light,
) {
    Text(text = textValue, fontSize = textSize, color = textColor, fontWeight = fontWeight)
}

@Preview
@Composable
fun TextComponentPreview(){
    TextComponent(textValue = "Check Text Component", textSize =30.sp, fontWeight = FontWeight.Light )
}


@Composable
fun TextFieldComponent(
    onTextChanged: (name: String) -> Unit,
) {
    var currentValue by remember { mutableStateOf("") }
    val localFocusManager = LocalFocusManager.current

    OutlinedTextField(
        value = currentValue,
        onValueChange = {
            currentValue = it
            onTextChanged(it)
        },
        label = { Text("Enter Name") },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions {
            localFocusManager.clearFocus()
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedLabelColor =Color.Blue,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedBorderColor = Color.Blue,  // Set the focused border color to green
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            cursorColor = Color.Blue,
            focusedTextColor = Color.Blue,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface
        ),
        textStyle = MaterialTheme.typography.bodyLarge,
        shape = MaterialTheme.shapes.small
    )
}

@Preview
@Composable
fun TextFieldComponentPreview() {
    TextFieldComponent(onTextChanged = { name ->
        println("Text changed: $name")
    })
}

@Composable
fun AnimalCard(image: Int, selected: Boolean, animalSelected: (animalName: String) -> Unit) {
    val localFocusManager = LocalFocusManager.current

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(24.dp)
            .size(130.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(
                    width = 2.dp,
                    color = if (selected) Color.Blue else Color.Transparent,
                    shape = RoundedCornerShape(8.dp)
                )
        )
        {

            Image(
                modifier = Modifier
                    .padding(10.dp, 25.dp)
                    .fillMaxSize()
                    .clickable {
                        val animalName = if (image == R.drawable.elephant) "Elephant" else "Horse"
                        animalSelected(animalName)
                        localFocusManager.clearFocus()
                    },
                painter = painterResource(id = image),
                contentDescription = "elephant"
            )
        }
    }
}


@Preview
@Composable
fun AnimalCardPreview() {
    AnimalCard(R.drawable.horse, true) { "Horse" }
}

@Composable
fun ButtonComponent(
    goToDetailScreen: () -> Unit,
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            goToDetailScreen()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF3F6D92),
            contentColor = Color.White
        )
    ) {
        TextComponent(
            textValue = "Go to Details Screen",
            textSize = 18.sp,
            textColor = Color.White
        )
    }
}

@Preview
@Composable
fun ButtonComponentPreview() {
    ButtonComponent(goToDetailScreen = {

    })
}

@Composable
fun FactComposable(value: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(18.dp, 24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.open_quote_svgrepo_com),
                contentDescription = "open quote",
            )
            Spacer(modifier = Modifier.size(24.dp))

            TextComponent(textValue = value, textSize = 24.sp, fontWeight = FontWeight.Normal)

            Spacer(modifier = Modifier.size(24.dp))

            Image(
                painter = painterResource(id = R.drawable.open_quote_svgrepo_com),
                contentDescription = "open quote",
                modifier = Modifier.rotate(180f)
            )

        }
    }
}

@Preview
@Composable
fun FactComposablePreview() {
    FactComposable("Hii")
}