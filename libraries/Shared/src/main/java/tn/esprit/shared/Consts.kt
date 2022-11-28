package tn.esprit.shared

object Consts {

    const val APP_PREFS = "STAR_DUST"

    const val USER_KEY = "USER_INFO"

    const val FUNCTION_URL = "https://ead0-197-14-204-40.eu.ngrok.io"

    const val FROM_IMG_EXTRACTION_REGEX = "(img src=\")([a-zA-Z0-9_,:\\/\\.-]*)(\"[a-z=\"])*"

    const val FROM_VID_EXTRACTION_REGEX = "(video poster=\")([a-zA-Z0-9_,:\\/\\.-]*)(\"[a-z=\"])*"

    const val LINK_EXTRACTION_REGEX = "(<a href=\")([a-z]{5}:\\/\\/[a-zA-Z\\/\\.\\-\\_\\,0-9]*)(\" ?\\/?>)"

    const val ID_PROD = "ID_PROD"

    const val POST_DATA = "POST_DATA"
}
