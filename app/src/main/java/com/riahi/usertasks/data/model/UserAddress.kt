package com.riahi.usertasks.data.model

data class UserAddress (
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: GeoLocation
)