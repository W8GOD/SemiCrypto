package com.discover.simple.core.provider

import com.discover.simple.core.CoreInitializer.init

internal class InitProvider : BaseInitProvider() {

    override fun onCreate(): Boolean {
        context?.let(::init)
        return true
    }
}