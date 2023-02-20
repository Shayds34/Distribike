package com.distribike.features.subfeatures.form.entity.di

import com.distribike.features.subfeatures.form.entity.FormSaverEntity
import com.distribike.features.subfeatures.form.entity.FormSaverEntityImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
class FormSaverEntityModule {

    /**
     * Binds instance
     */
    @ActivityRetainedScoped
    @Provides
    fun providesFormSaverEntityImpl(impl: FormSaverEntityImpl): FormSaverEntity = impl
}