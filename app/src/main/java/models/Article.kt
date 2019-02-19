package models

import com.google.gson.annotations.SerializedName

/**
 * Created by HP on 2/6/2019.
 */

class Article {
    @SerializedName("sectionName")
    val sectionName: String? = null
    @SerializedName("webPublicationDate")
    val date: String? = null
    @SerializedName("fields")
    val field: Field? = null
}
