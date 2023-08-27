package raf.rs.nutritiontracker.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room.databaseBuilder
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import raf.rs.nutritiontracker.database.MealDB
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Date
import java.util.concurrent.TimeUnit

val coreModule = module {

    single<SharedPreferences> {
        androidApplication().getSharedPreferences(
            androidApplication().packageName,
            Context.MODE_PRIVATE
        )
    }

    single {
        databaseBuilder(androidContext(), MealDB::class.java, "MealDB")
            .fallbackToDestructiveMigration()
            .build()
    }

    single { createMoshi() }

    single { createOkHttpClient() }

    single { createRetrofit(moshi = get(), httpClient = get()) }
}

fun createMoshi(): Moshi {
    return Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter())
//        .add(KotlinJsonAdapterFactory()) // has to be added as last adapter
        .build()
}

fun createRetrofit(
    moshi: Moshi,
    httpClient: OkHttpClient
): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
        .client(httpClient)
        .build()
}

fun createOkHttpClient(): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
    httpClient.readTimeout(60, TimeUnit.SECONDS)
    httpClient.connectTimeout(60, TimeUnit.SECONDS)
    httpClient.writeTimeout(60, TimeUnit.SECONDS)

    if (BuildConfig.DEBUG) {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)
    }

    return httpClient.build()
}

inline fun <reified T> create(retrofit: Retrofit): T {
    return retrofit.create<T>(T::class.java)
}