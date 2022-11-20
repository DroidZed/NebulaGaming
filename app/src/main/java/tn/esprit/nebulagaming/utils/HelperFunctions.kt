package tn.esprit.nebulagaming.utils

import android.content.Context
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import com.squareup.picasso.Downloader
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso

object HelperFunctions {

    fun launchURL(it: View, url: String) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(it.context, Uri.parse(url))
    }

    fun usePicasso(url: String, placeholder: Int, view: ImageView) =
        Picasso.get()
            .load(url)
            .resize(300, 300)
            .centerCrop()
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .placeholder(placeholder)
            .into(view)

    fun useSecurePicasso(
        url: String,
        placeholder: Int,
        context: Context,
        view: ImageView,
        downloader: Downloader,
    ) =
        Picasso
            .Builder(context)
            .downloader(downloader)
            .build()
            .load(url)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .resize(300, 300)
            .centerCrop()
            .placeholder(placeholder)
            .into(view)

    fun toastMsg(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}