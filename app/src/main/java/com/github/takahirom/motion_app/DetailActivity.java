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

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.takahirom.motion_app.datasource.PixabayResponse;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_ITEM = "EXTRA_ITEM";

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }

    public static Intent getLaunchIntent(Context context, PixabayResponse.Hit item) {
        final Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_ITEM, item);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final PixabayResponse.Hit item = getIntent().getParcelableExtra(EXTRA_ITEM);

        ActivityCompat.postponeEnterTransition(this);

        final float imageScale = getResources().getDisplayMetrics().density / 3;
        final int width = (int) (item.getWebformatWidth() * imageScale);
        final int height = (int) (item.getWebformatHeight() * imageScale);

        final ImageView imageView = (ImageView) findViewById(R.id.photo);
        Glide
                .with(this)
                .load(item.getWebformatURL().replace("640", "340"))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .dontAnimate()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }
                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        ActivityCompat.startPostponedEnterTransition(DetailActivity.this);
                        return false;
                    }
                })
                .into(imageView);

        final TextView detailText = (TextView) findViewById(R.id.detail_text);
        detailText.setText(fromHtml(item.getTags()));

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitleEnabled(false);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
