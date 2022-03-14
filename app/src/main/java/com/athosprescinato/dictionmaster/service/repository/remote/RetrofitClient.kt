package com.athosprescinato.dictionmaster.service.repository.remote

import com.athosprescinato.dictionmaster.service.constants.DictionConstants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitClient private constructor() {

    companion object {

        private lateinit var retrofit: Retrofit
        private val baseurl = "https://od-api.oxforddictionaries.com/api/v2/"
        private const val app_id = "0a5d63c4"
        private const val app_key = "4b51e0b7d83e139d9cae5071b6ee47dd"



        private fun getRetrofitInstance() : Retrofit {

            val httpClient =  OkHttpClient.Builder()
            httpClient.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request =
                        chain.request()
                            .newBuilder()
                            .addHeader(DictionConstants.HEADER.APP_ID, app_id)
                            .addHeader(DictionConstants.HEADER.APP_KEY, app_key)
                            .build()
                    return chain.proceed(request)
                }

            })


            if(!Companion::retrofit.isInitialized) {
                val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
                retrofit = Retrofit.Builder()
                    .baseUrl(baseurl)
                    .client(httpClient.build())
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build()
            }

            return retrofit
        }


        fun <S> createService(serviceClass : Class<S> ): S {

            return getRetrofitInstance().create(serviceClass)
        }



    }

}