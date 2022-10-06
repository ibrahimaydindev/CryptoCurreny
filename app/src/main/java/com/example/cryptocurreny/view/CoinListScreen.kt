package com.example.cryptocurreny.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cryptocurreny.viewmodel.CoinListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun CoinListScreen(
    navController: NavController, viewModel: CoinListViewModel = hiltViewModel()
) {
    Surface(color=Color.Gray,modifier= Modifier.fillMaxSize()){

    }

}



    


    
