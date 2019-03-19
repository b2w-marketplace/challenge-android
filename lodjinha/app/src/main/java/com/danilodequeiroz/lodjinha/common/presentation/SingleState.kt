package com.danilodequeiroz.lodjinha.common.presentation

import com.danilodequeiroz.lodjinha.productdetail.domain.DetailedProducViewModel

abstract class SingleState {
    abstract val data: DetailedProducViewModel?
}

data class DefaultSingleState(override val data: DetailedProducViewModel?) : SingleState()
data class LoadingSingleState(override val data: DetailedProducViewModel?) : SingleState()
data class ErrorSingleState(val errorMessage: String, override val data: DetailedProducViewModel?) : SingleState()

abstract class PostState {
    abstract val result: String?
    abstract val message: String?
}

data class NotPostState(override val message: String?, override val result: String?) : PostState()
data class PostSuccessState(override val message: String?, override val result: String?) : PostState()
data class PostingState(override val message: String?, override val result: String?) : PostState()
data class PostErrorState(override val message: String?, override val result: String?) : PostState()
