package com.example.weather.os

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

interface Strings {

    fun get(resId: Int): CharSequence
}

@Module
@InstallIn(SingletonComponent::class)
class StringsModule {

    @Provides
    fun strings(@ApplicationContext context: Context): Strings = AndroidStrings(context)
}

private class AndroidStrings(private val context: Context) : Strings {

    override fun get(resId: Int) = context.getText(resId)
}
