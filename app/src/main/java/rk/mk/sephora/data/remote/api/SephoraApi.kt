package rk.mk.sephora.data.remote.api

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rk.mk.sephora.data.JsonDeserializerWithOptions
import rk.mk.sephora.data.remote.api.SephoraApi.BASE_URL
import rk.mk.sephora.data.remote.api.SephoraApi.retrofit
import rk.mk.sephora.data.remote.remote_model.ItemsRemoteModel
import java.util.concurrent.TimeUnit

/**
 * This singleton object represents Api builder using [Retrofit].
 * @property BASE_URL is the Apis base url
 * @property retrofit is retrofit object used to build the requests
 */
object SephoraApi {

    const val BASE_URL = "https://sephoraios.github.io"
    val retrofit = buildRetrofit(BASE_URL)

    /**
     * This function is used to build a [Retrofit] object.
     *
     * @property baseUrl is the base url of the Api.
     *
     * @return a [Retrofit]
     */
    private fun buildRetrofit(baseUrl: String): Retrofit {
        val loggingInterceptor = provideLoggingInterceptor()
        val httpClient = provideLoggingCapableHttpClient(loggingInterceptor)
        val gson = provideGsonConverter()
        val rxJavaCallAdapterFactory = provideRxJavaCallAdapterFactory()
        return provideRetrofitBuilder(httpClient, gson, rxJavaCallAdapterFactory).baseUrl(baseUrl)
            .build()
    }

    /**
     * This function is used to build a [HttpLoggingInterceptor] object.
     *
     * @return a [HttpLoggingInterceptor]
     */
    private fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message ->
            Log.e("Retrofit logging", message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    /**
     * This function is used to build a [HttpLoggingInterceptor] object.
     *
     * @property loggingInterceptor is the login interceptor used to build the [OkHttpClient].
     *
     * @return a [OkHttpClient]
     */
    private fun provideLoggingCapableHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    /**
     * This function is used to build a retrofit builder.
     *
     * @property okHttpClient
     * @property gson
     * @property rxJavaCallAdapterFactory
     *
     * @return a [Retrofit.Builder]
     */
    private fun provideRetrofitBuilder(
        okHttpClient: OkHttpClient,
        gson: Gson,
        rxJavaCallAdapterFactory: RxJava2CallAdapterFactory
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(rxJavaCallAdapterFactory)
            .client(okHttpClient)
    }

    /**
     * This function is used to provide a [Gson] that converts the received Json to objects.
     *
     * @return a [Gson]
     */
    private fun provideGsonConverter(): Gson {
        return GsonBuilder()
            .serializeNulls()
            .disableHtmlEscaping()
            .registerTypeAdapter(
                ItemsRemoteModel::class.java,
                JsonDeserializerWithOptions<ItemsRemoteModel>()
            )
            .create()
    }

    /**
     * This function is used to provide a [RxJava2CallAdapterFactory] that acts as a callback.
     *
     * @return a [RxJava2CallAdapterFactory]
     */
    private fun provideRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

}