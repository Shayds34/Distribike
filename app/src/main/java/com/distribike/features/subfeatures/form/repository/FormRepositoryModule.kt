package com.distribike.features.subfeatures.form.repository

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
class FormRepositoryModule {

    /**
     * Binds instance
     */
    @ActivityRetainedScoped
    @Provides
    fun providesFormRepositoryImpl(impl: FormRepositoryImpl): FormRepository = impl
}