package team.pompom.mukmap.usecase

import team.pompom.mukmap.repository.InitRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InitUseCase @Inject constructor(
    private val initRepository: InitRepository
) {
    fun getAppInitData(appName: String) = initRepository.getInitData(appName)
}