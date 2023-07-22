package team.pompom.mukmap.exception.init

/**
 * 앱 초기화 로직 api 통신 시에, 데이터가 없을 시 exception 발생
 * 이를 presentation layer 에서 처리할 예정
 */
sealed class InitException : Throwable() {
    object NoMinAppVersionException : InitException()
    object NoCurrAppVersionException : InitException()
}