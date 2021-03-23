package com.abramchuk.task4

import com.google.gson.annotations.SerializedName

data class ArtObjectsList(
        @SerializedName("objectIDs") val art_objects_ids: List<Int>,
)
