package br.com.android.seiji.presentation.mapper

interface Mapper<out V, in D> {
    fun mapToView(type: D): V
}