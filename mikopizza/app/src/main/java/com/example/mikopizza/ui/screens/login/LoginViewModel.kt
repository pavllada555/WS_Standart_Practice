package com.example.mikopizza.ui.screens.login

import com.example.mikopizza.common.EventHandler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mikopizza.network.ApiService
import com.example.mikopizza.network.models.login.LoginResult
import com.example.mikopizza.network.models.register.RegisterResult
import com.example.mikopizza.ui.screens.login.models.LoginAction
import com.example.mikopizza.ui.screens.login.models.LoginEvent
import com.example.mikopizza.ui.screens.login.models.LoginSubState
import com.example.mikopizza.ui.screens.login.models.LoginViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel(), EventHandler<LoginEvent> {
    private val _viewState: MutableLiveData<LoginViewState> = MutableLiveData(LoginViewState())
    val viewState: LiveData<LoginViewState> = _viewState

    override fun obtainEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.ActionClicked -> switchActionState()
            LoginEvent.LoginActionInvoked -> loginActionInvoked()
            is LoginEvent.EmailChanged -> emailChanged(event.value)
            is LoginEvent.PasswordChanged -> passwordChanged(event.value)
            is LoginEvent.ForgetClicked -> forgetClicked()
            is LoginEvent.CheckboxClicked -> checkboxClicked(event.value)
            is LoginEvent.LoginClicked -> loginClicked()
            is LoginEvent.RegisterClicked -> registerClicked()
            is LoginEvent.FullNameChanged -> fullNameChanged(event.value)
            is LoginEvent.PhoneNumberChanged -> phoneChanged(event.value)
        }
    }

    private fun switchActionState() {
        when (_viewState.value?.loginSubState) {
            LoginSubState.SignIn -> _viewState.postValue(_viewState.value?.copy(loginSubState = LoginSubState.SignUp))
            LoginSubState.SignUp -> _viewState.postValue(_viewState.value?.copy(loginSubState = LoginSubState.SignIn))
            else -> Unit
        }

    }

    private fun registerClicked() {
        performRegister()
    }
    private val apiService by lazy {
        ApiService.create()
    }

    private fun performRegister() {
        viewModelScope.launch {
            _viewState.postValue(_viewState.value?.copy(isProgress = true))
            with (_viewState.value) {
                val registerResult = apiService.tryRegister(
                    email = this!!.emailValue,
                    fullName = this!!.fullNameValue,
                    password = this!!.passwordValue,
                    phoneNumber = this!!.phoneNumberValue
                )
                when (registerResult) {
                    is RegisterResult.Ok -> {
                        _viewState.postValue(
                            _viewState.value?.copy(
                                isProgress = false,
                                loginAction = LoginAction.OpenDashBoard(_viewState.value!!.emailValue)
                            )
                        )
                    }
                    else -> {
                        _viewState.postValue(_viewState.value?.copy(isProgress = false))
                    }
                }
            }
        }
    }

    private fun loginActionInvoked() {
        _viewState.postValue(_viewState.value?.copy(loginAction = LoginAction.None))
    }

    private fun fullNameChanged(value: String) {
        _viewState.postValue(_viewState.value?.copy(fullNameValue = value.filter { it.isLetter() || it.isWhitespace() }))
    }

    private fun phoneChanged(value: String) {
        if (value.countDigits() < 12) {
            _viewState.postValue(_viewState.value?.copy(phoneNumberValue = value))
        }
    }

    private fun emailChanged(value: String) {
        _viewState.postValue(_viewState.value?.copy(emailValue = value))
    }

    private fun passwordChanged(value: String) {
        _viewState.postValue(_viewState.value?.copy(passwordValue = value))
    }

    private fun forgetClicked() {
        _viewState.postValue(_viewState.value?.copy(loginSubState = LoginSubState.Forgot))
    }

    private fun checkboxClicked(value: Boolean) {
        _viewState.postValue(_viewState.value?.copy(rememberMeChecked = value))
    }

    private fun loginClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            _viewState.postValue(_viewState.value?.copy(isProgress = true))

            val loginResult = apiService.tryLogin(
                email = _viewState.value!!.emailValue,
                password = _viewState.value!!.passwordValue
            )
            when (loginResult){
                is LoginResult.Ok -> {
                    _viewState.postValue(
                        _viewState.value?.copy(
                            isProgress = false,
                            loginAction = LoginAction.OpenDashBoard(_viewState.value!!.emailValue)
                        )
                    )
                }
                else -> {
                    _viewState.postValue(_viewState.value?.copy(isProgress = false))
                }
            }
        }
    }

    fun String.countDigits(): Int {
        var counter = 0
        forEach {
            if (it.isDigit()) {
                counter++
            }
        }
        return counter
    }
}