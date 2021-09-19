package com.discover.simple.semicrypto

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class EmployeeViewModel : ViewModel() {
    val user: Flow<PagingData<User>> = Pager(PagingConfig(pageSize = 6)) {
        UserSource()
    }.flow
}