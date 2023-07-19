package team.pompom.mukmap.model.base

sealed class DomainResultModel<out T> {
    data class Success<T>(val data: T) : DomainResultModel<T>()
    data class Error(val error: Throwable) : DomainResultModel<Nothing>()
}