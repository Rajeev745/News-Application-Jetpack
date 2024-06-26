package com.example.insightfeed.domain.usecase.app_entry

import com.example.insightfeed.domain.manager.LocalUserManagerRepository
import kotlinx.coroutines.flow.Flow

class ReadAppEntryUseCase(
    private val localUserManagerRepository: LocalUserManagerRepository
) {

    operator fun invoke(): Flow<Boolean> {
        return localUserManagerRepository.readAppEntry()
    }
}