package com.example.mikopizza.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mikopizza.R
import com.example.mikopizza.navigation.NavigationTree
import com.example.mikopizza.network.ApiService
import com.example.mikopizza.ui.screens.login.models.LoginAction
import com.example.mikopizza.ui.screens.login.models.LoginEvent
import com.example.mikopizza.ui.screens.login.models.LoginSubState
import com.example.mikopizza.ui.screens.login.models.LoginViewState
import com.example.mikopizza.ui.screens.login.views.ForgotView
import com.example.mikopizza.ui.screens.login.views.SignInView
import com.example.mikopizza.ui.screens.login.views.SignUpView
import com.example.mikopizza.ui.theme.AppTheme
import com.example.mikopizza.ui.theme.MyFont

private val apiService by lazy {
    ApiService.create()
}

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    navController: NavController
) {
    val viewState = loginViewModel.viewState.observeAsState(LoginViewState())
    with(viewState.value) {
        LazyColumn(
            modifier = Modifier
                .background(AppTheme.colors.secondBackgroundColor)
                .fillMaxSize(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
        ) {
            item {
                Image(
                    bitmap = ImageBitmap.imageResource(R.drawable.logo),
                    "",
                    modifier = Modifier
                        .heightIn(min = 200.dp, max = 250.dp)
                        .padding(top = 20.dp)
                        .widthIn(min = 100.dp, max = 400.dp)
                        .fillMaxSize()
                )
                Text(
                    text = "Miko pizza!", style = TextStyle(
                        AppTheme.colors.authorizationTextColor,
                        fontSize = 40.sp
                    ), textAlign = TextAlign.Center,
                    fontFamily = MyFont,
                    modifier = Modifier.padding(start = 65.dp)
                )
            }
            item {
                Text(
                    text = when (loginSubState) {
                        LoginSubState.SignIn -> stringResource(id = R.string.sign_in_title)
                        LoginSubState.SignUp -> stringResource(id = R.string.sign_up_title)
                        LoginSubState.Forgot -> stringResource(id = R.string.forgot_title)
                    },
                    fontFamily = MyFont,
                    style = TextStyle(
                        color = AppTheme.colors.authorizationTextColor,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 25.sp
                    )
                )
            }
            item {
                Row(modifier = Modifier.padding(top = 0.dp)) {
                    Text(
                        text = when (loginSubState) {
                            LoginSubState.SignIn -> stringResource(id = R.string.sign_in_subtitle)
                            LoginSubState.SignUp -> stringResource(id = R.string.sign_up_subtitle)
                            LoginSubState.Forgot -> stringResource(id = R.string.forgot_subtitle)
                        },
                        fontFamily = MyFont,
                        style = TextStyle(
                            color = AppTheme.colors.authorizationTextColor,
                        )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    if (loginSubState != LoginSubState.Forgot) {
                        Text(
                            modifier = Modifier.clickable {
                                loginViewModel.obtainEvent(LoginEvent.ActionClicked)
                            },
                            text = when (loginSubState) {
                                LoginSubState.SignIn -> stringResource(id = R.string.sign_up_action)
                                LoginSubState.SignUp -> stringResource(id = R.string.sign_in_action)
                                else -> ""
                            },
                            fontFamily = MyFont,
                            style = TextStyle(
                                color = AppTheme.colors.activeTextColor,
                                fontStyle = FontStyle.Italic
                            )
                        )
                    }


                }
            }
            item {
                when (loginSubState) {
                    LoginSubState.SignIn -> SignInView(
                        viewState = this@with,
                        onLoginFieldChange = {
                            loginViewModel.obtainEvent(LoginEvent.EmailChanged(it))
                        },
                        onPasswordFieldChange = {
                            loginViewModel.obtainEvent(LoginEvent.PasswordChanged(it))
                        },
                        onCheckedChange = {
                            loginViewModel.obtainEvent(LoginEvent.CheckboxClicked(it))
                        },
                        onForgetClick = {
                            loginViewModel.obtainEvent(LoginEvent.ForgetClicked)
                        },
                        onLoginClick = {
                            loginViewModel.obtainEvent(LoginEvent.LoginClicked)
                        },
                    )
                    LoginSubState.SignUp -> SignUpView(
                        viewState = this@with,
                        onEmailFieldChange = {
                            loginViewModel.obtainEvent(LoginEvent.EmailChanged(it))
                        },
                        onPasswordFieldChange = {
                            loginViewModel.obtainEvent(LoginEvent.PasswordChanged(it))
                        },
                        onFullNameFieldChange = {
                            loginViewModel.obtainEvent(LoginEvent.FullNameChanged(it))
                        },
                        onPhoneNumberFieldChange = {
                            loginViewModel.obtainEvent(LoginEvent.PhoneNumberChanged(it))
                        },
                        onRegisterClick = {
                            loginViewModel.obtainEvent(LoginEvent.RegisterClicked)
                        },
                    )
                    LoginSubState.Forgot -> ForgotView()
                }
            }
        }
    }
    LaunchedEffect(key1 = viewState.value.loginAction) {
        when (val action = viewState.value.loginAction) {
            is LoginAction.OpenDashBoard -> {
                navController.navigate("${NavigationTree.Main.name}/${action.username}") {
                    popUpTo(0)
                }
            }
            else -> Unit
        }
    }
    DisposableEffect(key1 = Unit, effect = {
        onDispose {
            loginViewModel.obtainEvent(LoginEvent.LoginActionInvoked)
        }
    })

}