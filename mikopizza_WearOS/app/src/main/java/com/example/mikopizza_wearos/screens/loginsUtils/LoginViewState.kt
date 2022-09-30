package com.example.mikopizza_wearos.screens.loginsUtils

enum class LoginSubState{
    SignIn,
}

sealed class LoginAction{
    data class OpenDashBoard(val email: String) : LoginAction()
    object None: LoginAction()
}

data class LoginViewState(
    val loginSubState: LoginSubState = LoginSubState.SignIn,
    val emailValue: String = "",
    val passwordValue: String = "",
    val isProgress: Boolean = false,
    val loginAction: LoginAction = LoginAction.None
)