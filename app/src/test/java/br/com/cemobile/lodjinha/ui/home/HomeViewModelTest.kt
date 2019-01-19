package br.com.cemobile.lodjinha.ui.home

import android.arch.lifecycle.Observer
import br.com.cemobile.domain.features.homedata.GetHomeData
import br.com.cemobile.domain.model.HomeData
import br.com.cemobile.domain.model.Resource
import br.com.cemobile.domain.model.Status
import br.com.cemobile.lodjinha.ui.base.BaseUnitTest
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class HomeViewModelTest : BaseUnitTest() {

    @Captor
    private lateinit var captor: ArgumentCaptor<Resource<HomeData>>
    @Mock
    private lateinit var useCase: GetHomeData
    @Mock
    private lateinit var viewStateObserver: Observer<Resource<HomeData>>
    private lateinit var viewModel: HomeViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = spy(HomeViewModel(useCase, Dispatchers.Unconfined)).apply {
            getResourceLiveData().observeForever(viewStateObserver)
        }
    }

    @Test
    fun `should receive home data information with error`() {
        viewModel.loadHomeDataInformation()
        verify(viewStateObserver, times(2)).onChanged(captor.capture())
        val (loading, success) = captor.allValues
        assertEquals(loading.status, Status.LOADING)
        assertEquals(success.status, Status.ERROR)
    }

}