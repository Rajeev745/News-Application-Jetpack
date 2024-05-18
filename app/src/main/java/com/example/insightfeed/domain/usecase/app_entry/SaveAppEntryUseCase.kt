package com.example.insightfeed.domain.usecase.app_entry

import com.example.insightfeed.domain.manager.LocalUserManagerRepository

class SaveAppEntryUseCase(
    private val localUserManagerRepository: LocalUserManagerRepository
) {

    suspend operator fun invoke() {
        localUserManagerRepository.saveAppEntry()
    }
}