package team.pompom.mukmap.base

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import team.pompom.mukmap.model.base.BaseEntity
import team.pompom.mukmap.model.base.DomainResultModel

/**
 * local / remote 통신시애, suspend function 의 결과값을 ( success / error ) domainResult 로 변환해주는 flow
 */
fun <T: BaseDataModel, E : BaseEntity> domainResultFlow(
    mapper: BaseMapper<T, E>,
    action: suspend () -> T,
) = flow<DomainResultModel<E>> {
    val data = action.invoke()
    emit(DomainResultModel.Success(mapper.toEntity(data)))
}.catch { exception ->
    emit(DomainResultModel.Error(error = exception))
}