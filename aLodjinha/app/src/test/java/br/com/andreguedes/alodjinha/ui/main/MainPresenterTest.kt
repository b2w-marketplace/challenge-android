package br.com.andreguedes.alodjinha.ui.main

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MainPresenterTest {

    @Mock private lateinit var view : MainContract.View

    private lateinit var presenter: MainContract.Presenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        presenter = MainPresenter(view)
    }

    @Test
    fun shouldInitHomeFragment() {
        presenter.initHomeFragment()

        verify(view).initHomeFragment()
    }

}