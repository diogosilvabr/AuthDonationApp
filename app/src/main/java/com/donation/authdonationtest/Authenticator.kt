package com.donation.authdonationtest

import java.util.UUID

class Authenticator {
    companion object {
        private val users = listOf(
            User(UUID.randomUUID(), "dioguera", "diogo@diogo.com", "1234"),
        )

        fun login(email: String, password: String): User? {
            return users.firstOrNull { it.email == email && it.password == password }
        }
    }
}
