package br.com.andrecouto.alodjinha.di.module

import br.com.andrecouto.alodjinha.util.connectivity.BaseConnectionManager
import br.com.andrecouto.alodjinha.util.connectivity.ConnectionManager
import br.com.andrecouto.alodjinha.util.providers.BaseResourceProvider
import br.com.andrecouto.alodjinha.util.providers.ResourceProvider
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface UtilModule {

    /**
     * provide main implementation of [BaseConnectionManager] to check connection status
     */
    @Binds
    @Singleton
    fun bindConnectionManager(connectionManager: ConnectionManager): BaseConnectionManager

    /**
     * Provide main implementation of [BaseResourceProvider] to access app raw resources
     */
    @Binds
    fun bindResourceProvider(resourceProvider: ResourceProvider): BaseResourceProvider

}