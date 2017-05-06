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
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.takahirom.motion_app.datasource.PixabayResponse;
import com.github.takahirom.motion_app.datasource.PixabayService;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<PixabayResponse> {

    private PhotoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();


        final PixabayService pixabayService = ((MotionApplication) getApplication()).getPixabayService();
        pixabayService.getPublicPhotos().enqueue(this);
    }

    private void setupViews() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        final FlexboxLayoutManager layoutManager = new FlexboxLayoutManager();
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setAlignItems(AlignItems.STRETCH);

        adapter = new PhotoAdapter(this, new PhotoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View photoView, PixabayResponse.Hit item) {
                final Intent launchIntent = DetailActivity.getLaunchIntent(MainActivity.this, item);
                MainActivity.this.startActivity(launchIntent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResponse(Call<PixabayResponse> call, Response<PixabayResponse> response) {
        adapter.setItems(response.body().getHits());
    }

    @Override
    public void onFailure(Call<PixabayResponse> call, Throwable t) {

    }
}
