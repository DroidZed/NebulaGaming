package tn.esprit.nebulagaming.utils

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils

object HelperFunctions {

    fun launchURL(context: Context, url: String) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }

    fun usePicasso(
        url: String,
        placeholder: Int,
        view: ImageView
    ) =
        Picasso.get()
            .load(url)
            .networkPolicy(NetworkPolicy.NO_STORE, NetworkPolicy.NO_CACHE)
            .placeholder(placeholder)
            .into(view)

    fun toastMsg(context: Context, message: String) =
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    fun getGravatar(email: String): String =
        "https://s.gravatar.com/avatar/${md5(email)}?d=retro"

    private fun md5(content: String): String {
        val hash = String(Hex.encodeHex(DigestUtils.md5(content)))
        Log.d("digest", hash)
        return hash
    }
}