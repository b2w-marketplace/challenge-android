package br.com.dafle.alodjinha.ui.base

import br.com.dafle.alodjinha.util.RxImmediateSchedulerRule
import org.junit.Rule

open class BaseTest {

    @Rule
    @JvmField var testSchedulerRule = RxImmediateSchedulerRule()
}