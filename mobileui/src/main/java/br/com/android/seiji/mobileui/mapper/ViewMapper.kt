package br.com.android.seiji.mobileui.mapper

interface ViewMapper<in P, out V> {

    fun mapToView(presentation: P): V

}