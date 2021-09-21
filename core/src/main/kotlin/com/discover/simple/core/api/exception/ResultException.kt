package com.discover.simple.core.api.exception

open class ResultException protected constructor(
    message: String?,
    cause: Throwable?,
    val code: Int
) : Exception(message, cause) {

    companion object {
        fun create(message: String?, cause: Throwable?, error: ResultError): ResultException {
            return create(message, cause, error.code)
        }

        @JvmStatic
        fun create(message: String?, cause: Throwable?, code: Int): ResultException {
            return ResultException(message, cause, code)
        }
    }
}