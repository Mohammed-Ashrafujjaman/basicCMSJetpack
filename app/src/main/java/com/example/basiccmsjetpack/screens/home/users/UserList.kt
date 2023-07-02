package com.example.basiccmsjetpack.screens.home.users

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.basiccmsjetpack.models.UserDataModelItem
import com.example.basiccmsjetpack.screens.compositions.GeneralAppBar

@Composable
fun UserList(
    viewModel: UserListViewModel,
    navController: NavHostController
) {
    val state by viewModel.state.collectAsState()
    val pageUsers = state.users.collectAsLazyPagingItems()
    LaunchedEffect(Unit) {
        viewModel.loadUsers()
    }
    UserListSkeleton(
        navController = navController,
        showLoading = state.loading,
        users = pageUsers,
        retryDataLoad = {
            viewModel.loadUsers()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListSkeleton(
    navController: NavHostController,
    showLoading: Boolean = false,
    users: LazyPagingItems<UserDataModelItem>,
    retryDataLoad: () -> Unit = {}
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val lazyListState = rememberLazyListState()
    val expandedFab by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex == 0
        }
    }

    Scaffold(
        Modifier
            .navigationBarsPadding()
            .imePadding()
            .statusBarsPadding(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                },
                expanded = expandedFab,
                icon = { Icon(Icons.Filled.Add, "New User") },
                text = { Text(text = "New User") }
            )
        },
        topBar = {
            GeneralAppBar(navController = navController, subTitle = "Users List")
        }
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LazyColumn(
                    Modifier.fillMaxSize(),
                    state = lazyListState,
                    contentPadding = PaddingValues(top = 4.dp, bottom = 4.dp)
                ) {
                    items(
                        count = users.itemCount,
                        key = users.itemKey { it.id }
                    ) { index ->
                        val user = users[index]

                        if (user == null) {
                            Text("Loading...")
                        } else {
                            IndividualUserInfo(
                                modifier = Modifier.padding(
                                    start = 12.dp,
                                    top = 4.dp,
                                    end = 12.dp,
                                    bottom = 4.dp
                                ),
                                name = user.name,
                                email = user.email,
                                gender = user.gender,
                                status = user.status,
                                statusColor = user.getStatusColor(),
                                userImageUrl = user.getAvatarImageUrl(),
                                onClick = {}
                            )
                        }
                    }
                }
            }
        }
    }
}
