package com.mindgarden.mindgarden.presentation.util.common

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

/**
 * flow EventBus
 */
class Event<T> {
    private val _events = MutableSharedFlow<T>()
    val events = _events.asSharedFlow()

    suspend fun publishEvent(event: T) = _events.emit(event)
}