package com.loginid.cryptodid.di

import android.content.Context
import com.loginid.cryptodid.data.repository.DataStoreRepository
import com.loginid.cryptodid.data.repository.UserDataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BoardingModule {

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ) = DataStoreRepository(context = context)

    @Provides
    @Singleton
    fun provideUserDataStoreRepository(
        @ApplicationContext context: Context
    ) = UserDataStoreRepository(context = context)

}