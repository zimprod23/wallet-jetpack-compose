package com.loginid.cryptodid.presentation.navigation.graphs

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.loginid.cryptodid.presentation.issuer.creditScore.CreditScoreScreen
import com.loginid.cryptodid.presentation.issuer.plaid.PlaidScreen
import com.loginid.cryptodid.presentation.home.vc.VCViewModel.VCEnteryState
import com.loginid.cryptodid.presentation.home.vc.VCViewModel.VCViewModel
import com.loginid.cryptodid.presentation.issuer.MicroBlinkIssuerScreen
import com.loginid.cryptodid.presentation.navigation.screens.IssuerScreen
import com.loginid.cryptodid.utils.Constants
import java.util.*


fun NavGraphBuilder.issuerNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = IssuerScreen.MicroBlinkScreenScreen.route,
        route = Constants.ISSUER_GRAPH
    ){
        composable(
            route = IssuerScreen.MicroBlinkScreenScreen.route
        ){
            val viewModel = hiltViewModel<VCViewModel>()
            val microblink = MicroBlinkIssuerScreen(
                navController = navController,
            ){
                viewModel.saveVC(
                        VCEnteryState(
                            experationDate = Date(),
                            issuerName = "MicroBlink",
                            VCType = "Personal data",
                            VCTitle = "Age",
                            VCContentOverview = "+18",
                            VCAttribute = it
                        )
                )
            }
            microblink.microBlinkScreen()
        }
        /* TO DO */
      /*  composable(
            route = IssuerScreen.VCFromIssuerGateWayScreen.route
        ){

        }
        composable(
            route = IssuerScreen.GovernmentIDVCScreen.route
        ){

        }*/
        composable(
            route = IssuerScreen.CreditScorevcScreen.route
        ){
            CreditScoreScreen()
        }
        composable(
            route = IssuerScreen.PLAIDVCScreen.route
        ){
            PlaidScreen(navController)
        }
    }
}