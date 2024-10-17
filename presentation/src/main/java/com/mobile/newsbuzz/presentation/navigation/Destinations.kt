package com.mobile.newsbuzz.presentation.navigation

import com.mobile.newsbuzz.presentation.utils.Constants

sealed class Destinations(val name: String) {
    data object HomeNews : Destinations(Constants.SCREEN_NAME)
}
