package com.distribike.features.subfeatures.form.entity.di

import com.distribike.features.subfeatures.form.entity.FormSaverEntity
import com.distribike.features.subfeatures.form.entity.FormSaverEntityImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface FormSaverEntityModule {

    /**
     * Binds instance
     */
    @Singleton
    @Binds
    fun bindsFormSaverEntityImpl(impl: FormSaverEntityImpl): FormSaverEntity
}