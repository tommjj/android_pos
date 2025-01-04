package com.android.pos.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.android.pos.R


@Composable
fun PasswordTextField(
    value: String = "",
    onValueChange: (String) -> Unit = {},
    modifier: Modifier = Modifier,
    label : @Composable() (() -> Unit)? = null,
    keyboardOptions : KeyboardOptions = KeyboardOptions.Default,
    shape: Shape = Shapes().small,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int. MAX_VALUE,
    minLines: Int = 1,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors()
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        colors= colors,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        value = value,
        onValueChange = { onValueChange(it) },
        label = label,
        modifier = modifier.fillMaxWidth(),
        visualTransformation = when {
            passwordVisible -> VisualTransformation.None
            else -> PasswordVisualTransformation()
        },
        keyboardOptions =keyboardOptions,
        shape = shape,
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    painterResource(
                        when {
                            passwordVisible -> R.drawable.visibility_24px
                            else -> R.drawable.visibility_off_24px
                        }
                    ), contentDescription = "visibility"
                )
            }
        },
    )
}