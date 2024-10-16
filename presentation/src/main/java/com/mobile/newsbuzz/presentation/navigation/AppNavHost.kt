
package com.mobile.newsbuzz.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.mobile.newsbuzz.presentation.newsdetails.NewsDetailsScreen
import com.mobile.newsbuzz.presentation.newslist.NewsListScreen
import com.mobile.newsbuzz.presentation.utils.NewsUiModel
import kotlin.reflect.typeOf

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destinations.NewsList
    ) {
        composable<Destinations.NewsList> {
            NewsListScreen(
                navController = navController
            )
        }
        composable<Destinations.NewsDetails>(
            typeMap = mapOf(typeOf<NewsUiModel>() to NewsUiModelParameterType)
        ) { backStackEntry ->
            val news = backStackEntry.toRoute<Destinations.NewsDetails>().news
            NewsDetailsScreen(
                news = news,
                navController = navController
            )
        }
    }
}
