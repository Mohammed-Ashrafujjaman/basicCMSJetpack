package com.example.basiccmsjetpack.networks.api


import com.example.basiccmsjetpack.models.CommentListModelItem
import retrofit2.Response
import retrofit2.http.*

interface CommentsInterface {
    @GET("posts/{postId}/comments")
    suspend fun getCommentsDetails(@Path("postId") userId: Int): Response<List<CommentListModelItem>>

    @POST("posts/{postId}/comments")
    suspend fun addComment(
        @Path("postId") postId: Int,
        @Body comment: CommentListModelItem
    ): Response<CommentListModelItem>

    @DELETE("comments/{id}")
    suspend fun deleteComment(@Path("id") id: Int): Response<Unit>

    @PUT("comments/{id}")
    suspend fun updateComment(
        @Path("id") id: Int,
        @Body comment: CommentListModelItem
    ): Response<CommentListModelItem>
}
