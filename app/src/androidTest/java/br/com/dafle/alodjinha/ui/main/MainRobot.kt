package br.com.dafle.alodjinha.ui.main

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import br.com.dafle.alodjinha.R
import br.com.dafle.alodjinha.matcher.hasItemCount
import br.com.dafle.alodjinha.matcher.withRecyclerView
import br.com.dafle.alodjinha.service.ProductService
import io.reactivex.Observable
import junit.framework.TestCase.assertEquals
import org.hamcrest.Matchers.allOf
import org.mockito.Mockito.`when`

class CoinExeption: Throwable() {

    override val message: String?
        get() = super.message

    override fun getLocalizedMessage(): String {
        return "An error ocourred"
    }
}

fun itens(mBusiness: ProductService,
          testRule: ActivityTestRule<MainActivity>,
          func: MainRobot.() -> Unit) = MainRobot(mBusiness, testRule).apply { func() }

class MainRobot(private var mBusiness: ProductService,
                private var testRule: ActivityTestRule<MainActivity>) {

    private val intent = Intent().apply { }

    fun startActivityWithZeroCoins() {
        `when`(mBusiness.fetch())
                .thenReturn(Observable.just(listOf()))
        testRule.launchActivity(intent)
    }

    fun startActivityWithEightCoinsList() {
        val list = mutableListOf<Coin>()
        list.add(Coin("1", "Real", "2"))
        list.add(Coin("1", "Dolar", "2"))
        list.add(Coin("1", "Yen", "2"))
        list.add(Coin("1", "Rupias", "2"))
        list.add(Coin("1", "Eu nao sei", "2"))
        list.add(Coin("1", "Outra coisa", "2"))
        list.add(Coin("1", "Belo Teste", "2"))
        list.add(Coin("1", "Mais um dado", "2"))
        `when`(mBusiness.fetch())
                .thenReturn(Observable.just(list))
        testRule.launchActivity(intent)
    }

    fun startActivityWithErrorCallCoins() {
        `when`(mBusiness.fetch())
                .thenReturn(Observable.error(CoinExeption()))
        testRule.launchActivity(intent)
    }

    fun checkListWithData() {
        checkTitleItemAtPositionWithId(R.id.recyclerView, "Real", R.id.tvTitle, 0)
        checkTitleItemAtPositionWithId(R.id.recyclerView, "Mais um dado", R.id.tvTitle, 7)
        onView(withId(R.id.recyclerView)).check(matches(hasItemCount(8)))
        assertEquals(8, testRule.activity.viewModel.list.value?.size)
    }

    fun checkEmptyStateAndList() {
        onView(withId(R.id.recyclerView)).check(matches(hasItemCount(0)))
        assertEquals(0, testRule.activity.viewModel.list.value?.size)
    }

    fun checkIfListIsNull() {
        onView(withId(R.id.recyclerView)).check(matches(hasItemCount(0)))
        assertEquals(null, testRule.activity.viewModel.list.value)
    }

    private fun checkTitleItemAtPositionWithId(recyclerViewId: Int, title: String, itemViewId: Int, position: Int) {
        onView(
                allOf(
                        withParent(
                                withRecyclerView(recyclerViewId).atPosition(position)
                        ),
                        withId(itemViewId)
                )
        ).check(matches(withText(title)))
    }
}