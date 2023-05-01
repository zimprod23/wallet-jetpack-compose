package com.loginid.cryptodid.presentation.claim_list.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.loginid.cryptodid.presentation.TempNav.Screen
import com.loginid.cryptodid.presentation.claim_list.ClaimListViewModel




@Composable
fun ClaimListScreen(
    navController: NavController,
    viewModel: ClaimListViewModel = hiltViewModel()
){
     val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()){
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(
                state.claims
            ){
                ClaimListItem(claim = it, onItemClick = {
                    navController.navigate(Screen.ClaimDetailScreen.route + "/${it.id}")
                })
            }
        }

        if(state.error.isNotBlank()){
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }

        if(state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}