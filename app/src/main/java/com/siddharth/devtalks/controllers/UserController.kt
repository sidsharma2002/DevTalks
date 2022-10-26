package com.siddharth.devtalks.controllers

import com.siddharth.devtalks.models.User

object UserController {
    private var user: User? = null

    fun notifyLogIn(user: User) {
        this.user = user.copy()
    }

    fun getUserIfLoggedIn() : User? {
        return user?.copy()
    }
}