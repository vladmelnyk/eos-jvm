package com.memtrip.eos.http.rpc

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.memtrip.eos.http.rpc.utils.LocalDateTimeAdapter

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Converter

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Api(
    baseUrl: String,
    okHttpClient: OkHttpClient,
    moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(LocalDateTimeAdapter())
        .build(),
    converterFactory: Converter.Factory = MoshiConverterFactory.create(moshi),
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(converterFactory)
        .build(),
    val chain: ChainApi = retrofit.create(ChainApi::class.java),
    val wallet: WalletApi = retrofit.create(WalletApi::class.java),
    val history: HistoryApi = retrofit.create(HistoryApi::class.java)
)