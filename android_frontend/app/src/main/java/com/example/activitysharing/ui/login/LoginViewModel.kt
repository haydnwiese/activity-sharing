package com.example.activitysharing.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel: ViewModel() {

    enum class AuthenticationState {
        AUTHENTICATED, UNAUTHENTICATED, FAILED_AUTHENTICATION
    }

    var email: String = ""
    var password: String = ""

    val authenticationState: LiveData<AuthenticationState> = Transformations.map(FirebaseUserLiveData()) { user ->
        if (user != null) {
            AuthenticationState.AUTHENTICATED
        } else {
            AuthenticationState.UNAUTHENTICATED
        }
    }

    fun login() {
        Firebase.auth.signInWithEmailAndPassword(email, password)
    }
}