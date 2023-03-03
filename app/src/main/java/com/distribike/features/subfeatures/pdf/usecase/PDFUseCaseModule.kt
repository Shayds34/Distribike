package com.distribike.features.subfeatures.pdf.usecase

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

/**
 * Dagger module to display PDF data
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class PDFUseCaseModule {

    /**
     * Binds instance
     */
    @Binds
    @ActivityRetainedScoped
    abstract fun provideFormUseCaseImpl(impl: PDFUseCaseImpl): PDFUseCase
}