package com.example.cryptocurreny

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurreny.ui.theme.CryptoCurrenyTheme
import com.example.cryptocurreny.view.CoinDetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoCurrenyTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "coin_list_screen") {
                    composable("coin_list_screen") {
                        CoinListScreen(navController =navController)
                    }
                    composable("coin_detail_screen/{CoinId}/{CoinPrice}", arguments = listOf(
                        navArgument("CoinId") {
                            type = NavType.StringType
                        }, navArgument("CoinPrice") {
                            type = NavType.StringType
                        }
                    )) {

                        val CoinId = remember {
                            it.arguments?.getString("CoinId")
                        }
                        val CoinPrice = remember {
                            it.arguments?.getString("CoinPrice")
                        }
                        CoinDetailScreen(id = CoinId ?:"", price = CoinPrice?:"", navController =navController )
                    }
                }
            }
        }
    }
}


