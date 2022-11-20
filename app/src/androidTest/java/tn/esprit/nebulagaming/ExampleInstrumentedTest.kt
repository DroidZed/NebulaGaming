package tn.esprit.nebulagaming

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.prof.rssparser.Parser
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private lateinit var rssParser: Parser
    private lateinit var context: Context
    private val url = "https://www.kotaku.com/rss"

    private val okHttpClient by lazy {
        OkHttpClient()
    }

    @Before
    fun build() {

        context = InstrumentationRegistry.getInstrumentation().targetContext

        rssParser = Parser.Builder()
            .context(context)
            .cacheExpirationMillis(24L * 60L * 60L * 1000L) // one day
            .build()
    }

    @After
    fun destroy() {

    }

    @Test
    fun runRSSParser() {

        runBlocking {
            val request = Request.Builder()
                .url(url)
                .build()

            val result = okHttpClient.newCall(request).execute()

            val raw = runCatching { result.body?.string() }.getOrNull()

            assert(raw != null) { "Something went wrong !" }

            val channel = rssParser.parse(raw!!)

            val imagesList: MutableList<String> = mutableListOf()
            val urlsList: MutableList<String> = mutableListOf()

            channel.articles.stream().forEach {

                val parts = it.description!!.split("<p>")

                imagesList.add(
                    parts[0]
                        .substringAfter("<img src=\"")
                        .substringBefore("\" />")
                )
                urlsList.add(
                    parts[1]
                        .substringAfter("<a href=\"")
                        .substringBefore("\"")
                )
            }

            imagesList.forEach { println(it) }
            //urlsList.forEach { println(it) }

        }

    }
}