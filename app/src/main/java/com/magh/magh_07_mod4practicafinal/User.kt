package com.magh.magh_07_mod4practicafinal

import java.io.Serializable

data class User (
    val firstName : String?,
    val lastName : String?,
    val country : String?,
    val email :String?,
    val password : String?
): Serializable