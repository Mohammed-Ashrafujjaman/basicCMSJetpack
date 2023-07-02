package com.example.basiccmsjetpack.repositories

import android.content.Context
import com.example.basiccmsjetpack.models.PostListModelItem
import com.example.basiccmsjetpack.networks.SafeApiRequest
import com.example.basiccmsjetpack.networks.api.PostInterface
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val postApi: PostInterface
) {
    suspend fun getPostDetails(userId: Int) = withContext(Dispatchers.IO) {
        val postItem = SafeApiRequest.apiRequest(context) {
            postApi.getPostDetails(userId)
        }
        postItem
    }

    suspend fun addPost(userId: Int, post: PostListModelItem) = withContext(Dispatchers.IO) {
        SafeApiRequest.apiRequest(context) {
            postApi.addPost(userId, post)
        }
    }
}
