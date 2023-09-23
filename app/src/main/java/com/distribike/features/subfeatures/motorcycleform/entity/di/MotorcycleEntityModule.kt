package com.distribike.features.subfeatures.motorcycleform.entity.di

import com.distribike.features.subfeatures.motorcycleform.entity.MotorcycleEntity
import com.distribike.features.subfeatures.motorcycleform.entity.MotorcycleEntityImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface MotorcycleEntityModule {

    /**
     * Binds instance
     */
    @Singleton
    @Binds
    fun bindsMotorcycleEntityImpl(impl: MotorcycleEntityImpl): MotorcycleEntity
}
