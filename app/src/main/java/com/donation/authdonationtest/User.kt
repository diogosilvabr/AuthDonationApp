package com.donation.authdonationtest

import java.util.UUID

data class User(
    val id: UUID,
    val username: String,
    val email: String,
    val password: String
)
