package com.example.basiccmsjetpack.datasources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.basiccmsjetpack.models.UserDataModelItem
import com.example.basiccmsjetpack.networks.ApiException
import com.example.basiccmsjetpack.repositories.UserRepository
import okio.IOException
import retrofit2.HttpException

class UsersPagingModel(
    private val repository: UserRepository
) : PagingSource<Long, UserDataModelItem>() {
    override fun getRefreshKey(state: PagingState<Long, UserDataModelItem>): Long? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, UserDataModelItem> {
        val pagePosition = params.key ?: 1

        return try {
            val users = repository.getUserDetails(pagePosition)

            if (users == null) {
                LoadResult.Error(ApiException("No Data Found!"))
            } else {
                val nextKey = if (users.isEmpty()) {
                    null
                } else {
                    pagePosition + 1
                }
                LoadResult.Page(
                    data = users,
                    prevKey = if (pagePosition == 1L) null else pagePosition - 1,
                    nextKey = nextKey
                )
            }
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: ApiException) {
            return LoadResult.Error(e)
        }
    }
}
