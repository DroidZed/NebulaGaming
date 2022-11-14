package tn.esprit.nebulagaming.utils

import android.net.Uri
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent

object HelperFunctions {

    fun launchURL(it: View, url: String) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(it.context, Uri.parse(url))
    }
}