package com.example.myfootball.util

import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing
import java.io.File
import java.io.FileNotFoundException

fun <T> lenientGiven(methodCall: T): OngoingStubbing<T> = Mockito.lenient().`when`(methodCall)
fun <T> OngoingStubbing<T>.willReturn(value: T): OngoingStubbing<T> = this.thenReturn(value)