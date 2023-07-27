package team.pompom.mukmap.extension

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMap
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.zip
import team.pompom.mukmap.model.base.DomainResultModel

/**
 * local / remote 통신시애, suspend function 의 결과값을 ( success / error ) domainResult 로 변환해주는 flow
 */
fun <T> domainResultFlow(
    action: suspend () -> T,
) = flow<DomainResultModel<T>> {
    emit(DomainResultModel.Success(action.invoke()))
}.catch { exception ->
    emit(DomainResultModel.Error(error = exception))
}

fun <T, K, R> Flow<DomainResultModel<T>>.domainResultZip(
    domainResultFlow: Flow<DomainResultModel<K>>,
    convert: suspend (t: T, k: K) -> R
): Flow<DomainResultModel<R>> = zip(domainResultFlow) { firstFlowResult, secondFlowResult ->
    val firstResult = when (firstFlowResult) {
        is DomainResultModel.Error -> return@zip DomainResultModel.Error(firstFlowResult.error)
        is DomainResultModel.Success -> firstFlowResult.data
    }
    val secondResult = when (secondFlowResult) {
        is DomainResultModel.Error -> return@zip DomainResultModel.Error(secondFlowResult.error)
        is DomainResultModel.Success -> secondFlowResult.data
    }
    return@zip DomainResultModel.Success(convert(firstResult, secondResult))
}

fun <T, R> Flow<DomainResultModel<T>>.successMap(
    convert: (t: T) -> R
) = map {
    when (it) {
        is DomainResultModel.Error -> DomainResultModel.Error(it.error)
        is DomainResultModel.Success -> DomainResultModel.Success(convert(it.data))
    }
}

@OptIn(FlowPreview::class)
fun <T, K> Flow<DomainResultModel<T>>.domainResultFlatMapConcat(
    domainResultFlow: (prevResult: T) -> Flow<DomainResultModel<K>>,
) = flatMapConcat {
    when (it) {
        is DomainResultModel.Error -> flowOf(DomainResultModel.Error(it.error))
        is DomainResultModel.Success -> domainResultFlow.invoke(it.data)
    }
}