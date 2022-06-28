package com.example.mikopizza.ui.screens.login.models

enum class LoginSubState{
    SignIn, SignUp, Forgot

}

sealed class LoginAction{
    data class OpenDashBoard(val username: String): LoginAction()
    object None: LoginAction()
}

data class LoginViewState (
    val loginSubState: LoginSubState = LoginSubState.SignIn,
    val emailValue: String = "",
    val passwordValue: String = "",
    val fullNameValue: String = "",
    val rememberMeChecked: Boolean = false,
    val isProgress: Boolean = false,
    val loginAction: LoginAction = LoginAction.None,
    val phoneNumberValue: String = "",
)
