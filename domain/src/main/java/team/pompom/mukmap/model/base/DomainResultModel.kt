package team.pompom.mukmap.model.base

sealed class DomainResultModel<out T> {
    data class Success<T>(val data: T) : DomainResultModel<T>()
    data class Error(val error: Throwable) : DomainResultModel<Nothing>()
}

fun <T> DomainResultModel<T>.isSuccess(): Boolean = this is DomainResultModel.Success
fun <T> DomainResultModel<T>.hasError(): Boolean = this is DomainResultModel.Error