package tn.esprit.shared

object Consts {
    const val APP_PREFS = "STAR_DUST"

    const val JWT_KEY = "USER_TOKEN"

    const val REFRESH_KEY = "REFRESH_TOKEN"

    const val ROLE_KEY = "USER_ROLE"

    const val U_ID_KEY = "USER_ID"
    
    const val STATUS_KEY = "USER_STATUS"

    const val FUNCTION_URL = "https://deep-crabs-jam-197-15-26-81.loca.lt"

    const val FROM_IMG_EXTRACTION_REGEX = "(img src=\")([a-zA-Z0-9_,:\\/\\.-]*)(\"[a-z=\"])*"

    const val FROM_VID_EXTRACTION_REGEX = "(video poster=\")([a-zA-Z0-9_,:\\/\\.-]*)(\"[a-z=\"])*"

    const val LINK_EXTRACTION_REGEX = "(<a href=\")([a-z]{5}:\\/\\/[a-zA-Z\\/\\.\\-\\_\\,0-9]*)(\" ?\\/?>)"
}
