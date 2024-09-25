package org.yomasoftware.project.domain

sealed class ResultOf<out R> {
    data object Loading : ResultOf<Nothing>()
    data class Success<out R>(val value:R) : ResultOf<R>()
    data class Failure(
        val message: String?,
        val throwable: Throwable?
    ):ResultOf<Nothing>()
}
