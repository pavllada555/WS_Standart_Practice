package com.example.mikopizza_wearos.utils

interface EventHandler<E>{
    fun obtainEvent(event: E)
}