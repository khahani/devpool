package androidx.compose.samples.crane.di.module

import android.util.Log
import com.android.volley.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class PixabayInterceptor @Inject constructor() : Interceptor {

    companion object {
        private val TAG = PixabayInterceptor::class.simpleName
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val url = original.url.newBuilder()
            .addQueryParameter("key", BuildConfig.BUILD_TYPE)
            .build()
        val request = original.newBuilder()
            .url(url)
            .build()

        val networkResponse = chain.proceed(request)
            .networkResponse ?: throw PixabayHeaderInterceptorException()

        networkResponse.headers.forEach { (key, value) ->
            Log.d(TAG, "response header: $key= $value")
        }
        return networkResponse
    }
}

class PixabayHeaderInterceptorException : Throwable()
