package com.example.basiccmsjetpack.repositories

import android.content.Context
import com.example.basiccmsjetpack.models.UserDataModelItem
import com.example.basiccmsjetpack.networks.SafeApiRequest
import com.example.basiccmsjetpack.networks.api.UserInterface
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val api: UserInterface
) {
    suspend fun getUserDetails(page: Long) = withContext(Dispatchers.IO) {
        SafeApiRequest.apiRequest(context) {
            api.getUserData(page)
        }
    }

    suspend fun addUser(user: UserDataModelItem) = withContext(Dispatchers.IO) {
        SafeApiRequest.apiRequest(context) {
            api.addUser(user)
        }
    }
}
