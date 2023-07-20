package com.umutcansahin.checkinternetpermissionwithmanager

import android.app.Activity
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

//@Singleton
//class NetworkHelper @Inject constructor(
//    @ApplicationContext context: Context
//) {
//    private val _state = MutableStateFlow(false)
//    val state = _state.asStateFlow()
//    lateinit var currentActivity:Activity
//
//}