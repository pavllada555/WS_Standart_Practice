package com.example.mikopizza_wearos.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mikopizza_wearos.NavigationTree
import com.example.mikopizza_wearos.R
import com.example.mikopizza_wearos.screens.loginsUtils.*
import com.example.mikopizza_wearos.theme.WearAppColorPalette
import com.example.mikopizza_wearos.utils.TextInput
import com.example.mikopizza_wearos.utils.TextVisuals

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    navController: NavController
) {
    val viewState = loginViewModel.viewState.observeAsState(LoginViewState())
    with(viewState.value){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(WearAppColorPalette.primary),
            contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
        ) {
            item {
                when(loginSubState){
                    LoginSubState.SignIn -> SignInView(
                        viewState = this@with,
                        onLoginFieldChange = {
                            loginViewModel.obtainEvent(LoginEvent.EmailChanged(it))
                        },
                        onPasswordFieldChange = {
                            loginViewModel.obtainEvent(LoginEvent.PasswordChanged(it))
                        },
                        onLoginClick = {
                            loginViewModel.obtainEvent(LoginEvent.LoginClicked)
                        }
                    )
                }
            }
        }
    }
    LaunchedEffect(key1 = viewState.value.loginAction) {
        when (val action = viewState.value.loginAction) {
            is LoginAction.OpenDashBoard -> {
                navController.navigate("${NavigationTree.Main.name}/${action.email}") {
                    popUpTo(0)
                }
            }
            else -> Unit
        }
    }
}

@Composable
fun SignInView(
    viewState: LoginViewState,
    onLoginFieldChange: (String) -> Unit,
    onPasswordFieldChange: (String) -> Unit,
    onLoginClick: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    Column(modifier = Modifier.fillMaxSize()){
        TextInput(
            header = stringResource(id = R.string.email),
            textFieldValue = viewState.emailValue,
            onTextFieldChange = {
                if (!viewState.isProgress) onLoginFieldChange.invoke(it)
            },
            modifier = Modifier,
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            textVisuals = TextVisuals.Text,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            enabled = !viewState.isProgress
        )
        TextInput(
            header = stringResource(id = R.string.password),
            textFieldValue = viewState.passwordValue,
            onTextFieldChange = {
                if (!viewState.isProgress) onPasswordFieldChange.invoke(it)
            },
            modifier = Modifier,
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            textVisuals = TextVisuals.Password,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            enabled = !viewState.isProgress
        )
        Button(
            modifier = Modifier,
            onClick = {
                if (!viewState.isProgress)
                    onLoginClick.invoke()
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = WearAppColorPalette.primaryVariant
            )
        ) {
            if (viewState.isProgress){
                CircularProgressIndicator(
                    strokeWidth = 2.dp,
                    color = WearAppColorPalette.secondary
                )
            } else {
                Text(
                    text = stringResource(id = R.string.actionLogin),
                    fontWeight = FontWeight.Medium,
                    color = WearAppColorPalette.primary,
                )
            }
        }
    }

}
