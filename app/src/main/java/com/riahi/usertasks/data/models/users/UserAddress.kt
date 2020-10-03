package com.riahi.usertasks.data.models.users

import com.riahi.usertasks.data.models.users.GeoLocation

data class UserAddress (
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: GeoLocation
)