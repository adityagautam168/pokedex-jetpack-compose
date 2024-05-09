package com.example.pokedex.modules.pokemon_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onQueryChange: (query: Flow<String>) -> Unit,
    onSearch: (query: String) -> Unit,
) {
    val queryFlow = remember {
        MutableStateFlow("")
    }
    val query by queryFlow.collectAsState()

    val focusManager = LocalFocusManager.current
    var isFocused by remember {
        mutableStateOf(false)
    }

//    val isKeyboardVisible by keyboardState()
//    Timber.tag("MyTest").d("isKeyboardVisible: $isKeyboardVisible")
//    if (!isKeyboardVisible) {
//        focusManager.clearFocus()
//    }

    return Box(
        modifier
            .background(Color.White, shape = CircleShape)
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        BasicTextField(
            value = query,
            onValueChange = {
                queryFlow.value = it
                onQueryChange(queryFlow)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black, fontSize = TextUnit.Unspecified),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    focusManager.clearFocus()
                    onSearch(query)
                },
            ),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    isFocused = it.isFocused
                }
        )
        if (!isFocused && query.isBlank() && hint.isNotBlank()) {
            Text(
                text = hint,
                color = Color.LightGray,
            )
        }
    }
}