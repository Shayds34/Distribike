package com.distribike.utils

import kotlinx.coroutines.flow.MutableStateFlow

suspend fun <T> MutableStateFlow<T>.setState(block: suspend (T) -> T) {
    val oldValue = this.value
    val newValue = block(oldValue)
    this.value = newValue
}