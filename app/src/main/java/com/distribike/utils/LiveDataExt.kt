package com.distribike.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun <T> MutableLiveData<T>.asLiveData(): LiveData<T> {
    return this
}

suspend inline fun <T : Any?> MutableLiveData<out T>.offer(value: T) =
    withContext(Dispatchers.Main) {
        this@offer.value = value
    }