package tn.esprit.nebulagaming.utils

import android.content.Context
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import tn.esprit.apimodule.NetworkClient

object HelperFunctions {

    fun launchURL(it: View, url: String) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(it.context, Uri.parse(url))
    }

    fun usePicasso(url: String, placeholder: Int, view: ImageView) =
        Picasso.get()
            .load(url)
            .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .placeholder(placeholder)
            .into(view)

    fun useSecurePicasso(
        url: String,
        placeholder: Int,
        context: Context,
        view: ImageView,
        height: Int? = 300,
        width: Int? = 300

    ) =
        Picasso
            .Builder(context)
            .downloader(
                OkHttp3Downloader(
                    NetworkClient(context).retrieveSecureOkHttpClientInstance()
                )
            )
            .build()
            .load(url)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .resize(width!!, height!!)
            .centerCrop()
            .placeholder(placeholder)
            .into(view)


    fun toastMsg(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}