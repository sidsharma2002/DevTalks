package com.siddharth.devtalks.models

data class User(
    val id: String,
    val name: String,
    val photoUrl: String? = null
)
