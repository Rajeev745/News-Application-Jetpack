package com.example.insightfeed.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManagerRepository {

    suspend fun saveAppEntry()

    fun readAppEntry(): Flow<Boolean>
}