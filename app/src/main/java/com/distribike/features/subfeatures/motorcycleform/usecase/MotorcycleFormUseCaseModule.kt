package com.distribike.features.subfeatures.motorcycleform.usecase

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

/**
 * Dagger module for data-forms
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class MotorcycleFormUseCaseModule {

    /**
     * Binds instance
     */
    @Binds
    @ActivityRetainedScoped
    abstract fun provideFormUseCaseImpl(impl: MotorcycleFormUseCaseImpl): MotorcycleFormUseCase
}