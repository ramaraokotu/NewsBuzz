package com.mobile.newsbuzz.presentation.navigation

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mobile.newsbuzz.presentation.newslist.NewsListScreen

/**
 * AppNavHost is the navigation host for the app.
 *  start destination is NewsListScreen. and
 *  onNewsClick is used to open the news in custom chrome tab.
 */
@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Destinations.HomeNews.name
    ) {
        composable(route = Destinations.HomeNews.name) {
            NewsListScreen(
                onNewsClick = {
                    openCustomChromeTab(context, it)
                }
            )
        }
    }
}

/**
 * openCustomChromeTab is used to open the news in custom chrome tab.
 */
fun openCustomChromeTab(context: Context, url: String) {
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, Uri.parse(url))
}
