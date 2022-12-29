package tn.esprit.nebulagaming.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import com.google.firebase.messaging.FirebaseMessaging
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import java.util.*

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

    fun subscribeToTopic(context: Context, channelName: String, channelId: String, topic: String) {

        val channel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_HIGH
        )

        val manager = context.getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)

        FirebaseMessaging.getInstance().subscribeToTopic(topic)
            .addOnCompleteListener { task ->
                var msg = "Subscribed Successfully"
                if (!task.isSuccessful) {
                    msg = "Subscription failed"
                }
                Log.d("NOTIF-SUB", msg)
            }
    }

    /**
     * @param pattern Pattern like: DD-MM-YYYY
     * @param d Date object
     * @return Parsed date corresponding to the pattern supplied.
     */
    fun parseDateUsingPattern(pattern: String, d: Date? = null): String =
        SimpleDateFormat(pattern, Locale.ENGLISH).format(d ?: Date())
}