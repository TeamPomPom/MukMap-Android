package team.pompom.mukmap.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import team.pompom.mukmap.model.DomainResultModel

open class BaseViewModel : ViewModel() {
    fun <T> Flow<DomainResultModel<T>>.viewModelsIn(
        onSuccess: suspend (data: T) -> Unit = {},
        onError: suspend (throwable: Throwable) -> Unit = {}
    ) = onEach {
        when (it) {
            is DomainResultModel.Success -> onSuccess(it.data)
            is DomainResultModel.Error -> onError(it.error)
        }
    }.launchIn(viewModelScope)
}