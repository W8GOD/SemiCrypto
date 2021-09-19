package com.discover.simple.semicrypto

data class UserResponse (
    var page: Int,
    var per_page: Int,
    var total: Int,
    var total_pages: Int,
    var data: List<User>,
    var support: Support
)