package com.discover.simple.core.api.response

import com.discover.simple.core.model.Support
import com.discover.simple.core.model.User

data class UserResponse(
    var page: Int,
    var per_page: Int,
    var total: Int,
    var total_pages: Int,
    var data: List<User>,
    var support: Support
)