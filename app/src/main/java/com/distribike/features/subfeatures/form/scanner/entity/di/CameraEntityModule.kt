package com.distribike.features.subfeatures.form.scanner.entity.di

import com.distribike.features.subfeatures.form.scanner.entity.CameraEntity
import com.distribike.features.subfeatures.form.scanner.entity.CameraEntityImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CameraEntityModule {

    /**
     * Binds instance
     */
    @Singleton
    @Binds
    fun bindsCameraEntityImpl(impl: CameraEntityImpl): CameraEntity
}
