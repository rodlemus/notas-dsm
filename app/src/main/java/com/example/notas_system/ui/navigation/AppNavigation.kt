package com.example.notas_system.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notas_system.ui.theme.login.screen.LoginScreen
import com.example.notas_system.ui.theme.signup.screen.SignupScreen
import com.example.notas_system.ui.theme.notas.screen.NotasScreen
import com.example.notas_system.viewmodel.AuthViewModel
import com.example.notas_system.viewmodel.NotasViewModel

@Composable
fun AppNavigation(viewModel: AuthViewModel, context: Context) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            viewModel.logout()
            LoginScreen(
                viewModel = viewModel,
                context = context,
                onLoginSuccess = { navController.navigate("notas") {
                    popUpTo("login") { inclusive = true }
                } },
                onNavigateToRegister = { navController.navigate("register") }
            )
        }
        composable("register") {
            SignupScreen(
                viewModel = viewModel,
                context = context,
                onRegisterSuccess = { navController.navigate("notas") {
                    popUpTo("notas") { inclusive = true }
                } },
                onNavigateToLogin = { navController.navigate("login") }
            )
        }
        composable("notas") {
            NotasScreen(
                viewModel = NotasViewModel(),
                authViewModel = AuthViewModel(),
                onLogout = {
                    viewModel.logout()
                    navController.navigate("login") {
                        popUpTo("notas") { inclusive = true }
                    }
                }
            )
        }
    }
}