/*
 * Copyright (C) 2017 takahirom
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.takahirom.motion_app;


import android.app.Application;
import android.os.Build;

import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;
import com.github.takahirom.motion_app.datasource.PixabayService;

import java.io.IOException;
import java.util.Collections;

import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MotionApplication extends Application {
    private PixabayService pixabayService;

    @Override
    public void onCreate() {
        super.onCreate();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                final Request request = chain.request();
                final HttpUrl httpUrl = request.url().newBuilder().addQueryParameter("key", BuildConfig.PIXABAY_API_KEY).build();

                final Request newRequest = request.newBuilder().url(httpUrl).build();
                return chain.proceed(newRequest);
            }
        })
                .addInterceptor(logging);

        // for Android 7.0 http://stackoverflow.com/a/41874704/4339442
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) {
            ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                    .tlsVersions(TlsVersion.TLS_1_2)
                    .cipherSuites(
                            CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA)
                    .build();
            builder.connectionSpecs(Collections.singletonList(spec));
        }
        final OkHttpClient client = builder
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        pixabayService = retrofit.create(PixabayService.class);

        Glide.get(this).setMemoryCategory(MemoryCategory.HIGH);
    }

    public PixabayService getPixabayService() {
        return pixabayService;
    }
}
