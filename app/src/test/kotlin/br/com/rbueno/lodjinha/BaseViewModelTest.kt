package br.com.rbueno.lodjinha

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.rbueno.lodjinha.viewmodel.BaseViewModel
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.Mock

abstract class BaseViewModelTest<T : BaseViewModel> {

    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    protected lateinit var viewModel: T

    @Mock
    protected lateinit var loadingObserver: Observer<Boolean>
    @Mock
    protected lateinit var errorObserver: Observer<String>

    @Before
    open fun setup() {
        viewModel.errorLiveData.observeForever(errorObserver)
        viewModel.loadingLiveData.observeForever(loadingObserver)
    }

    @After
    open fun teardown() {
        viewModel.loadingLiveData.removeObserver(loadingObserver)
        viewModel.errorLiveData.removeObserver(errorObserver)

    }

}