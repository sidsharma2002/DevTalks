package com.siddharth.devtalks.auth

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.siddharth.devtalks.LocalNavHostController
import com.siddharth.devtalks.LocalViewModelFactory
import com.siddharth.devtalks.R
import com.siddharth.devtalks.navigation.NavRoute

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = viewModel(factory = LocalViewModelFactory.current),
    navController: NavHostController = LocalNavHostController.current
) {

    val resultCode = 101
    val context = LocalContext.current

    val activityResult =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
            onResult = {
                // if (it.resultCode != resultCode) return@rememberLauncherForActivityResult
                val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    firebaseAuthWithGoogle(account.idToken!!, context as Activity, navController)
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Toast.makeText(context, e.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }
        )


    Box(modifier = Modifier.fillMaxSize()) {
        Button(modifier = Modifier
            .wrapContentSize()
            .align(Alignment.BottomCenter)
            .padding(bottom = 30.dp),
            onClick = {
                startLogin(
                    context as Activity,
                    context.getString(R.string.default_web_client_id),
                    activityResult
                )
            }) {
            Text(text = "Login with Google")
        }
    }
}

fun firebaseAuthWithGoogle(idToken: String, activity: Activity, navController: NavHostController) {
    val credential = GoogleAuthProvider.getCredential(idToken, null)
    val auth = Firebase.auth
    auth.signInWithCredential(credential)
        .addOnCompleteListener(activity) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                val user = auth.currentUser
                navController.navigate(NavRoute.HomeScreen.route) {
                    popUpTo(NavRoute.AuthScreen.route) { inclusive = true }
                    launchSingleTop = true
                }
            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(activity.applicationContext, "LoggedIn!", Toast.LENGTH_SHORT).show()
            }
        }
}

fun startLogin(
    activity: Activity,
    webClientId: String,
    activityResult: ManagedActivityResultLauncher<Intent, ActivityResult>
) {
    // Configure Google Sign In
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(webClientId)
        .requestEmail()
        .build()

    val googleSignInClient = GoogleSignIn.getClient(activity, gso)
    val intent = googleSignInClient.signInIntent
    activityResult.launch(intent)
}
