package team.pompom.mukmap.usecase

import team.pompom.mukmap.repository.InitRepository
import javax.inject.Inject

class InitUseCase @Inject constructor(
    private val initRepository: InitRepository
) {
    fun getAppInitData(appName: String) = initRepository.getInitData(appName)
}