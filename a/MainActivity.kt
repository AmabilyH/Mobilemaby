package edu.app.primeiromabitivo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "tela_a"
    ) {

        composable("tela_a") {
            ScreenA(navController)
        }

        composable("tela_b") {
            ScreenB(navController)
        }
    }
}

@Composable
fun ScreenA(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Gostaria de acessar o perfil de Fulana?")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate("tela_b")
        }) {
            Text("Acessar perfil")
        }
    }
}

@Composable
fun ScreenB(navController: NavController) {

    var isFollowed by remember { mutableStateOf(false) }
    var likeCount by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("Esse é o perfil de Fulana")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            isFollowed = !isFollowed
        }) {
            Text(if (isFollowed) "Seguindo" else "Seguir")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            if (isFollowed)
                "Você está seguindo Fulana"
            else
                "Você não está seguindo"
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            likeCount++
        }) {
            Text("Likes: $likeCount")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("Voltar")
        }
    }
}