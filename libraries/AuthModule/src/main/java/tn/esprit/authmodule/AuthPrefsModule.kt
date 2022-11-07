package tn.esprit.authmodule

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tn.esprit.authmodule.repos.JWTManager
import tn.esprit.authmodule.repos.JWTManagerImpl
import tn.esprit.authmodule.repos.UserAuthManager
import tn.esprit.authmodule.repos.UserAuthManagerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthPrefsModule {

    @Singleton
    @Provides
    abstract fun provideUserAuthManager(impl: UserAuthManagerImpl): UserAuthManager

    @Singleton
    @Provides
    abstract fun provideJWTManager(impl: JWTManagerImpl): JWTManager

}