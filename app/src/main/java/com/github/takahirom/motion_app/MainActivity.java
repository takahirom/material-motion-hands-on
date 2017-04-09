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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.test.mock.MockApplication;
import android.view.View;
import android.widget.Toast;

import com.github.takahirom.motion_app.datasource.FlickerResponse;
import com.github.takahirom.motion_app.datasource.FlickerService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<FlickerResponse> {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();

        final FlickerService flickerService = ((MotionApplication) getApplication()).getFlickerService();
        flickerService.getPublicPhotos().enqueue(this);
    }

    private void setupViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    @Override
    public void onResponse(Call<FlickerResponse> call, Response<FlickerResponse> response) {
        final RecyclerAdapter adapter = new RecyclerAdapter(response.body().getItems(), new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View photoView, FlickerResponse.Item item) {
                startActivity(DetailActivity.getLaunchIntent(MainActivity.this, item));
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFailure(Call<FlickerResponse> call, Throwable t) {

    }
}
