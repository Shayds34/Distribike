package com.distribike.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DispatchersModule {

    @Provides
    @Named(DispatchersName.UI_LAYOUT)
    @Singleton
    fun provideUiLayoutDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Named(DispatchersName.UI_VIEWMODEL)
    @Singleton
    fun provideUiViewModelDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Named(DispatchersName.DOMAIN)
    @Singleton
    fun provideDomainDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Named(DispatchersName.DATA)
    @Singleton
    fun provideDataDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Named(DispatchersName.ENTITY)
    @Singleton
    fun provideEntityDispatcher(): CoroutineDispatcher = Dispatchers.IO

    /**
     * Exists as long as the app live
     * Don't use here global scope
     * https://stackoverflow.com/questions/61255807/coroutine-scope-on-application-class-android
     */
    @Provides
    @Named(ScopesName.ALWAYS_ALIVE_APP_SCOPE)
    @Singleton
    fun provideCoroutineAppScope(
        @Named(DispatchersName.DOMAIN) dispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + dispatcher)

    /**
     * Exists as long as the app live
     * Don't use here global scope
     * https://stackoverflow.com/questions/61255807/coroutine-scope-on-application-class-android
     */
    @Provides
    @Named(ScopesName.PROFILE_SESSION_SCOPE)
    @Singleton
    fun provideCoroutineProfileScope(
        @Named(DispatchersName.DOMAIN) dispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + dispatcher)
}