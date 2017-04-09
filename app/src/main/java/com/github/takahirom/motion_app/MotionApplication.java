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

import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;
import com.github.takahirom.motion_app.datasource.FlickerService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MotionApplication extends Application {
    private FlickerService flickerService;

    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.flickr.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        flickerService = retrofit.create(FlickerService.class);

        Glide.get(this).setMemoryCategory(MemoryCategory.HIGH);
    }

    public FlickerService getFlickerService() {
        return flickerService;
    }
}
