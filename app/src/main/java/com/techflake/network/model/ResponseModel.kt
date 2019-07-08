package com.techflake.network.model

import androidx.databinding.BaseObservable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/* Pojo classes for response*/

class GiphyBaseData : Serializable {

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("slug")
    @Expose
    var slug: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("bitly_gif_url")
    @Expose
    var bitlyGifUrl: String? = null

    @SerializedName("bitly_url")
    @Expose
    var bitlyUrl: String? = null

    @SerializedName("embed_url")
    @Expose
    var embedUrl: String? = null

    @SerializedName("username")
    @Expose
    var username: String? = null

    @SerializedName("source")
    @Expose
    var source: String? = null

    @SerializedName("rating")
    @Expose
    var rating: String? = null

    @SerializedName("content_url")
    @Expose
    var contentUrl: String? = null

    @SerializedName("source_tld")
    @Expose
    var sourceTld: String? = null

    @SerializedName("source_post_url")
    @Expose
    var sourcePostUrl: String? = null

    @SerializedName("is_sticker")
    @Expose
    var isSticker: Int? = null

    @SerializedName("import_datetime")
    @Expose
    var importDatetime: String? = null

    @SerializedName("trending_datetime")
    @Expose
    var trendingDatetime: String? = null

    @SerializedName("images")
    @Expose
    var images: Images? = null

}



class GiphyBaseResponse : Serializable {

    @SerializedName("data")
    @Expose
    var data: List<GiphyBaseData>? = null

}

class Images : Serializable {

    @SerializedName("preview")
    @Expose
    var preview: Preview? = null

    @SerializedName("original")
    @Expose
    var original: Original? = null

    @SerializedName("preview_webp")
    @Expose
    var preview_webp: PreviewWebp? = null
}



class Original : Serializable {

    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("width")
    @Expose
    var width: String? = null
    @SerializedName("height")
    @Expose
    var height: String? = null
    @SerializedName("size")
    @Expose
    var size: String? = null
    @SerializedName("frames")
    @Expose
    var frames: String? = null
    @SerializedName("mp4")
    @Expose
    var mp4: String? = null
    @SerializedName("mp4_size")
    @Expose
    var mp4Size: String? = null
    @SerializedName("webp")
    @Expose
    var webp: String? = null
    @SerializedName("webp_size")
    @Expose
    var webpSize: String? = null
    @SerializedName("hash")
    @Expose
    var hash: String? = null

}


class Preview {

    @SerializedName("width")
    @Expose
    var width: String? = null
    @SerializedName("height")
    @Expose
    var height: String? = null
    @SerializedName("mp4")
    @Expose
    var mp4: String? = null
    @SerializedName("mp4_size")
    @Expose
    var mp4Size: String? = null

}


class PreviewWebp : BaseObservable(),Serializable {

    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("width")
    @Expose
    var width: String? = null
    @SerializedName("height")
    @Expose
    var height: String? = null
    @SerializedName("size")
    @Expose
    var size: String? = null

}
