package models

import com.google.gson.annotations.SerializedName

/**
 * Created by HP on 2/6/2019.
 */

class Response {
    @SerializedName("results")
    val articles: List<Article>? = null
}
