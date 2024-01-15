package com.newsApp.common

object Utils {

        sealed class Either<out L, out R> {
            data class Error<out L>(val exception: L) : Either<L, Nothing>()
            data class Success<out R>(val data: R) : Either<Nothing, R>()
        }

        sealed class Failure {
            object NetworkConnection : Failure()
            object ServerError : Failure()
            object UnknownError : Failure()
        }
}