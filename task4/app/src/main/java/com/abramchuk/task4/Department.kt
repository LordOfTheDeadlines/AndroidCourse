package com.abramchuk.task4

import com.google.gson.annotations.SerializedName

data class Department(
//        val departments: ArrayList<String>,
        @SerializedName("departmentId") val id: Int,
        @SerializedName("displayName") val name: String,
)
