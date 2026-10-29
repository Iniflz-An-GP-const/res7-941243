package gnrco.res7.mobile.ui.composable.navigation

import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import gnrco.res7.mobile.ui.composable.screen.about.GCEOEAboutScreen
import gnrco.res7.mobile.ui.composable.screen.game.GCEOEGameScreen
import gnrco.res7.mobile.ui.composable.screen.high_scores.GCEOEHighScoresScreen
import gnrco.res7.mobile.ui.composable.screen.main_menu.GCEOEMainMenuScreen
import gnrco.res7.mobile.ui.composable.screen.splash.GCEOESplashScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val activity = LocalActivity.current

    NavHost(
        navController = navController,
        startDestination = NavRoute.Splash,
        modifier = modifier,
    ) {
        composable<NavRoute.Splash> {
            GCEOESplashScreen(
                onMainMenuScreenNavigation = {
                    navController.navigate(route = NavRoute.MainMenu) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<NavRoute.MainMenu> {
            GCEOEMainMenuScreen(
                onPlayClick = { difficulty ->
                    navController.navigate(
                        route = NavRoute.Game(
                            difficulty
                        )
                    )
                },
                onHighScoresScreenNavigation = { navController.navigate(route = NavRoute.HighScores) },
                onAboutScreenNavigation = { navController.navigate(route = NavRoute.About) },
                onExit = { activity?.finishAffinity() }
            )
        }

        composable<NavRoute.Game> { backStackEntry ->
            val route = backStackEntry.toRoute<NavRoute.Game>()
            GCEOEGameScreen(
                difficulty = route.difficulty,
                onMainMenu = {
                    navController.navigate(route = NavRoute.MainMenu) {
                        popUpTo(NavRoute.MainMenu) {
                            inclusive = true
                        }
                    }
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable<NavRoute.About> {
            GCEOEAboutScreen(onBackClick = { navController.popBackStack() })
        }

        composable<NavRoute.HighScores> {
            GCEOEHighScoresScreen(onBackClick = { navController.popBackStack() })
        }
    }
}
