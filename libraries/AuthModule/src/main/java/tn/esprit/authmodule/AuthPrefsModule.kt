package tn.esprit.authmodule

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tn.esprit.authmodule.repos.JWTManager
import tn.esprit.authmodule.repos.JWTManagerImpl
import tn.esprit.authmodule.repos.UserAuthManager
import tn.esprit.authmodule.repos.UserAuthManagerImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthPrefsModule {

    @Binds
    abstract fun provideUserAuthManager(impl: UserAuthManagerImpl): UserAuthManager

    @Binds
    abstract fun provideJWTManager(impl: JWTManagerImpl): JWTManager

}