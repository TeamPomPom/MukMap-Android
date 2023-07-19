package team.pompom.mukmap.model

sealed class DomainResultModel<T> {
    data class Success<T>(val data: T) : DomainResultModel<T>()
    data class Error(val error: Throwable) : DomainResultModel<Nothing>()
}