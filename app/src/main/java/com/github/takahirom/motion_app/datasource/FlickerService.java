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

package com.github.takahirom.motion_app.datasource;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface FlickerService {
    /**
     * Flicker Public feed API
     * @see <a href="https://www.flickr.com/services/feeds/docs/photos_public/">Public feed</a>
     */
    @GET("/services/feeds/photos_public.gne?format=json&is_commons=1&&nojsoncallback=1&tags=servalcat")
    public Call<FlickerResponse> getPublicPhotos();
}
