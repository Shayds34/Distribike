package com.distribike.features.subfeatures.motorcycleform.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

/**
 * Dagger module for data-forms
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
class MotorcycleFormRepositoryModule {

    /**
     * Binds instance
     */
    @ActivityRetainedScoped
    @Provides
    fun providesFormRepositoryImpl(impl: MotorcycleFormRepositoryImpl): MotorcycleFormRepository = impl
}