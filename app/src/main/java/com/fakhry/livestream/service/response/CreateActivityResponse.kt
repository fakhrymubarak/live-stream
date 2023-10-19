package com.fakhry.livestream.service.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CreateActivityResponse(

    @field:SerializedName("ResponseMetadata")
    val createActivityResponseMetadata: CreateActivityResponseMetadata,

    @field:SerializedName("Result")
    val createActivityResult: CreateActivityResult
)

@Keep
data class CreateActivityResponseMetadata(

    @field:SerializedName("Action")
    val action: String,

    @field:SerializedName("RequestId")
    val requestId: String,

    @field:SerializedName("Version")
    val version: String,

    @field:SerializedName("Service")
    val service: String,

    @field:SerializedName("Region")
    val region: String,

    @field:SerializedName("SystemTime")
    val systemTime: Int
)

data class CreateActivityResult(

    @field:SerializedName("ActivityId")
    val activityId: Long,

    @field:SerializedName("ActivityIdStr")
    val activityIdStr: String
)
