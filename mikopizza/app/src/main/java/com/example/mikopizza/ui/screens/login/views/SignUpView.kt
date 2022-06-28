package com.example.mikopizza.ui.screens.login.views


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mikopizza.R
import com.example.mikopizza.ui.components.TextInputForSignUp
import com.example.mikopizza.ui.screens.login.models.LoginViewState
import com.example.mikopizza.ui.theme.AppTheme

@Composable
fun SignUpView(
    viewState: LoginViewState,
    onEmailFieldChange: (String)->Unit,
    onPasswordFieldChange: (String)->Unit,
    onPhoneNumberFieldChange: (String)->Unit,
    onFullNameFieldChange: (String)->Unit,
    onCancelClicked: ()->Unit,
    onRegisterClick: ()-> Unit
){
    Column(modifier = Modifier.fillMaxSize()){
        TextInputForSignUp(
            modifier = Modifier.padding(top = 0.dp),
            header= stringResource(id = R.string.email_hint),
            textFieldValue = viewState.emailValue,
            enabled = !viewState.isProgress,
            onTextFieldChange = {
                if(!viewState.isProgress) onEmailFieldChange.invoke(it)
            },
        )
        TextInputForSignUp(
            modifier = Modifier.padding(top = 0.dp),
            header= stringResource(id = R.string.password_hint),
            textFieldValue = viewState.passwordValue,
            onTextFieldChange ={
                if(!viewState.isProgress) onPasswordFieldChange.invoke(it)
            },
            secureText = true,
            enabled = !viewState.isProgress
        )
        TextInputForSignUp(
            modifier = Modifier.padding(top = 0.dp),
            header= stringResource(id = R.string.full_name_hint),
            textFieldValue = viewState.fullNameValue,
            onTextFieldChange ={
                if(!viewState.isProgress) onFullNameFieldChange.invoke(it)
            },
            secureText = false,
            enabled = !viewState.isProgress
        )
        TextInputForSignUp(
            modifier = Modifier.padding(top = 0.dp),
            header= stringResource(id = R.string.phone_number_hint),
            textFieldValue = viewState.phoneNumberValue,
            onTextFieldChange ={
                if(!viewState.isProgress) onPhoneNumberFieldChange.invoke(it)
            },
            secureText = false,
            enabled = !viewState.isProgress
        )
        Button(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
                .height(60.dp),
            onClick = {
                if(!viewState.isProgress)
                    onRegisterClick.invoke()
            },
            shape = RoundedCornerShape(size = 45.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppTheme.colors.buttonColor
            )
        )
        {
            if (viewState.isProgress){
                CircularProgressIndicator(
                    strokeWidth = 2.dp,
                    color = AppTheme.colors.activeTextColor
                )
            }
            else {
                Text(
                    text = stringResource(id = R.string.action_register),
                    fontWeight = FontWeight.Light,
                    color = AppTheme.colors.systemTextColor,
                    style = TextStyle(fontSize = 25.sp)
                )
            }
        }
        Button(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .height(60.dp),
            onClick = {
                if(!viewState.isProgress)
                    onCancelClicked.invoke()
            },
            shape = RoundedCornerShape(size = 45.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppTheme.colors.buttonColor
            )
        )
        {
            if (viewState.isProgress){
                CircularProgressIndicator(
                    strokeWidth = 2.dp,
                    color = AppTheme.colors.activeTextColor
                )
            }
            else {
                Text(
                    text = stringResource(id = R.string.action_cancel),
                    fontWeight = FontWeight.Light,
                    color = AppTheme.colors.systemTextColor,
                    style = TextStyle(fontSize = 25.sp)
                )
            }
        }
    }
}