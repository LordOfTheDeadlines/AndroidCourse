package com.abramchuk.task4

import com.google.gson.annotations.SerializedName

data class ArtObject(
        @SerializedName("objectID") val id: Int,
        val objectName: String,
        val title: String,
)
