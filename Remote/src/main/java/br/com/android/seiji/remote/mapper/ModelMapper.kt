package br.com.android.seiji.remote.mapper

interface ModelMapper<M, E> {
    fun mapFromModel(model: M): E
}