package models

import com.google.gson.annotations.SerializedName

/**
 * Created by HP on 2/6/2019.
 */

class Field {
    @SerializedName("headline")
    val newsTitle: String? = null
    @SerializedName("byline")
    val authorName: String? = null
    @SerializedName("shortUrl")
    val webUrl: String? = null
    @SerializedName("thumbnail")
    val imgUrl: String? = null
}
