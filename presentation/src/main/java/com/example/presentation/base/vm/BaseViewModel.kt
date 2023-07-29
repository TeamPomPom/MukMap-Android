package com.example.presentation.base.vm

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import team.pompom.mukmap.model.base.DomainResultModel

abstract class BaseViewModel<Event: ViewEvent, UiState: ViewState, Effect: ViewSideEffect> : ViewModel() {
    abstract fun setInitialState() : UiState
    abstract fun handleEvents(event: Event)

    private val initState: UiState by lazy { setInitialState() }

    private val _viewState: MutableState<UiState> = mutableStateOf(initState)
    val viewState: State<UiState> = _viewState

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()

    private val _effect: Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        subscribeToEvents()
    }

    private fun subscribeToEvents() {
        viewModelScope.launch {
            _event.collect {
                handleEvents(it)
            }
        }
    }

    fun setEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }

    protected fun setState(reducer: UiState.() -> UiState) {
        val newState = viewState.value.reducer()
        _viewState.value = newState
    }

    protected fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }

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