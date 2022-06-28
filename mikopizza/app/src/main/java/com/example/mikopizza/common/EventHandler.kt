package com.example.mikopizza.common

interface EventHandler<E> {
    fun obtainEvent(event: E)
}