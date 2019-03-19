package com.danilodequeiroz.lodjinha.common.presentation

import com.danilodequeiroz.lodjinha.home.domain.BaseViewModel

abstract class ListState {
    abstract val pageNum:Int
    abstract val loadedAllItems:Boolean
    abstract val data: List<BaseViewModel>
}

data class DefaultState(override val data: List<BaseViewModel>, override val loadedAllItems: Boolean = false, override val pageNum: Int = 0) : ListState()
data class EmptyListState(override val data: List<BaseViewModel> = emptyList(), override val loadedAllItems: Boolean = false, override val pageNum: Int = 0) : ListState()
data class LoadingState(override val data: List<BaseViewModel>, override val loadedAllItems: Boolean = false, override val pageNum: Int = 0) : ListState()
data class PaginatingState(override val data: List<BaseViewModel>, override val loadedAllItems: Boolean, override val pageNum: Int) : ListState()
data class ErrorState(val errorMessage: String, override val data: List<BaseViewModel>, override val loadedAllItems: Boolean = false, override val pageNum: Int = 0) : ListState()
