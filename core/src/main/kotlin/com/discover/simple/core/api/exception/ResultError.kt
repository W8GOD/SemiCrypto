package com.discover.simple.core.api.exception

enum class ResultError(val code: Int) {
    VALIDATION_ERROR(422),
    TOO_MANY_REQUEST(429),
    UNKNOWN(800000);

    fun `is`(error: ResultError?): Boolean {
        return equals(error)
    }

    companion object {
        private val TAG = ResultError::class.java.name

        @JvmStatic
        fun from(throwable: Throwable): ResultError {
            return if (throwable is ResultException) {
                values().find { it.code == throwable.code } ?: UNKNOWN
            } else {
                UNKNOWN
            }
        }

        fun from(code: Int): ResultError {
            return values().find { it.code == code } ?: UNKNOWN
        }
    }
}