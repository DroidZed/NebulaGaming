package tn.esprit.nebulagaming

import org.junit.Assert.assertEquals
import org.junit.Test
import tn.esprit.shared.Consts.FROM_IMG_EXTRACTION_REGEX
import tn.esprit.shared.Consts.FROM_VID_EXTRACTION_REGEX
import tn.esprit.shared.Consts.LINK_EXTRACTION_REGEX
import java.util.regex.Pattern

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val vidTag =
        "<video poster=\"https://i.kinja-img.com/gawker-media/image/upload/s--FroroYce--/c_fit,fl_progressive,q_80,w_636/8ead71bef3351d6cc5f937b90ac4f373.jpg\" loop=\"\" autoplay=\"\" muted=\"\" playsinline=\"\"><source type=\"video/mp4\" src=\"https://i.kinja-img.com/gawker-media/image/upload/s--NRFnnkM1--/c_fit,fl_progressive,q_80,w_636/8ead71bef3351d6cc5f937b90ac4f373.mp4\"/></video><p><em>Armello </em>developers League of Geeks—<a href=\"https://kotaku.com/solium-infernum-league-of-geeks-grand-strategy-pc-steam-1849571511\">who are already working on hellish strategy game <em>Solium Infernum</em></a>—today announced a <em>second</em> project, called <em>Jumplight Odyssey</em>, which might be of interest to anyone who enjoys management games, spaceships and/or old anime.<br></p><p><a href=\"https://kotaku.com/jumplight-odyssey-pc-strategy-macross-gundam-yamato-1849799021\">Read more...</a></p>"

    private val imgTag =
        "<img src=\"https://i.kinja-img.com/gawker-media/image/upload/s--vM27L2M4--/c_fit,fl_progressive,q_80,w_636/81c98530e75a17b7005c7e7eaae4eb3e.jpg\" /><p><a href=\"https://kotaku.com/spiderman-remastered-review-pc-insomniac-ray-tracing-1849391687\">Insomniac’s 2018 <em>Spider-Man</em></a> was a very good open-world superhero game that featured a large cast of villains, heroes, and supporting characters like Aunt May and Mary Jane. It also included the voice of Peter Parker’s friend, Harry Osborn, who didn’t appear in the game physically, but was heard via recorded messages…</p><p><a href=\"https://kotaku.com/marvel-spider-man-harry-osborn-actor-ps4-ps5-venom-1849799023\">Read more...</a></p>"

    private val aTag =
        "<img src=\"https://i.kinja-img.com/gawker-media/image/upload/s--qGHpe8_U--/c_fit,fl_progressive,q_80,w_636/ee4c64b2f76637898ec8126580e31bf3.jpg\" /><p>Over the past 24 hours a number of people in Japan—including a Square Enix employee—have been arrested on insider trading charges related to a <em>Dragon Quest</em> game announcement. Legendary Sega designer Yuji Naka is reportedly among them.<br></p><p><a href=\"https://kotaku.com/yuji-naka-sonic-hedgehog-dragon-quest-arrested-crime-1849799553\">Read more...</a></p>"

    @Test
    fun this_works() {
        assert(1 + 1 == 2) { "huh ?" }
    }

    @Test
    fun imgTagParsing_isCorrect() {

        val imgPattern = Pattern.compile(FROM_IMG_EXTRACTION_REGEX).matcher(imgTag)

        assertEquals(true, imgPattern.matches())
    }

    @Test
    fun vidTagParsing_isCorrect() {

        val vidPattern = Pattern.compile(FROM_VID_EXTRACTION_REGEX).matcher(vidTag)
        assertEquals(true, vidPattern.matches())
    }

    @Test
    fun anchorTag_isCorrect() {
        val anchorPattern = Pattern.compile(LINK_EXTRACTION_REGEX).matcher(aTag)
        assertEquals(true, anchorPattern.matches())
    }
}