package com.example.basiccmsjetpack.screens.home.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.basiccmsjetpack.datasources.UsersPagingModel
import com.example.basiccmsjetpack.models.UserDataModelItem
import com.example.basiccmsjetpack.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _eventShowLoading = MutableStateFlow(false)

    private var _users = MutableStateFlow<Flow<PagingData<UserDataModelItem>>>(emptyFlow())
    private val _state = MutableStateFlow(UserListViewState())
    val state: StateFlow<UserListViewState>
        get() = _state

    init {
        viewModelScope.launch {
            combine(
                _eventShowLoading,
                _users
            ) { showLoading, users ->
                UserListViewState(
                    loading = showLoading,
                    users = users
                )
            }.catch { throwable ->
                // TODO: emit a UI error here. For now we'll just rethrow
                throw throwable
            }.collect {
                _state.value = it
            }
        }
    }

    fun loadUsers() {
        _users.value = Pager(PagingConfig(pageSize = 10)) {
            UsersPagingModel(userRepository)
        }
            .flow
            .cachedIn(viewModelScope)
    }
}

data class UserListViewState(
    val loading: Boolean = false,
    val users: Flow<PagingData<UserDataModelItem>> = emptyFlow()
)
