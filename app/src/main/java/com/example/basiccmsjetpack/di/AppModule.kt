package com.example.basiccmsjetpack.di


import com.example.basiccmsjetpack.networks.ApiClient
import com.example.basiccmsjetpack.networks.api.CommentsInterface
import com.example.basiccmsjetpack.networks.api.PostInterface
import com.example.basiccmsjetpack.networks.api.TodosInterface
import com.example.basiccmsjetpack.networks.api.UserInterface
import com.example.basiccmsjetpack.utils.MoshiUtil
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return MoshiUtil.getMoshi()
    }

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit {
        return ApiClient.getRetrofit(moshi)
    }

    @Singleton
    @Provides
    fun provideUserApiInterface(retrofit: Retrofit): UserInterface {
        return retrofit.create(UserInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideTodoApiInterface(retrofit: Retrofit): TodosInterface {
        return retrofit.create(TodosInterface::class.java)
    }

    @Singleton
    @Provides
    fun providePostApiInterface(retrofit: Retrofit): PostInterface {
        return retrofit.create(PostInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideCommentsApiInterface(retrofit: Retrofit): CommentsInterface {
        return retrofit.create(CommentsInterface::class.java)
    }
}
