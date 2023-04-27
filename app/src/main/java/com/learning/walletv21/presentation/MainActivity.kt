package com.learning.walletv21.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.learning.walletv21.presentation.home.biometrics.BiometricPromptScreen
import com.learning.walletv21.presentation.loginid_welcome.SplashViewModel
import com.learning.walletv21.presentation.navigation.graphs.SetupNavGraph
import com.learning.walletv21.presentation.theme.WalletV21Theme
import com.learning.walletv21.utils.Constants.AUTH_GRAPH
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@ExperimentalAnimationApi
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    @Inject
    lateinit var splashViewModel: SplashViewModel


    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  MicroblinkSDK.setLicenseKey(BLINK_ID_LICENCE,this)
        installSplashScreen().setKeepOnScreenCondition{
            !splashViewModel.isLoading.value
        }
        setContent {
            WalletV21Theme {
                val screen by splashViewModel.startDestination

                navController = rememberNavController()
                // SetupNavGraph(navController = navController)
               /* NavHost(navController = navController, startDestination =  Screen.ClaimListScreen.route){
                    composable(
                        route = Screen.ClaimListScreen.route
                    ){
                        ClaimListScreen(navController = navController)
                    }
                    composable(
                        route = Screen.ClaimDetailScreen.route + "/{$PARAM_CLAIM_ID}"
                    ){
                        ClaimDetailScreen()
                    }
                }
                */
                SetupNavGraph(navController = navController, startDestination = AUTH_GRAPH/*HOME_ROOT_GRAPH*//*screen*/)
                //BiometricScreen(onClick = {launchBiometric()})
                //BiometricPromptScreen()
            }
        }
    }

}

