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
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import static com.github.takahirom.motion_app.DetailActivity.EXTRA_ITEM;

public class ContentDetailActivity extends AppCompatActivity {

    public static Intent getLaunchIntent(Context context, PhotoDetailAdapter.Item item) {
        final Intent intent = new Intent(context, ContentDetailActivity.class);
        intent.putExtra(EXTRA_ITEM, item);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_detail);

        PhotoDetailAdapter.Item item = (PhotoDetailAdapter.Item) getIntent().getSerializableExtra(EXTRA_ITEM);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        TextView textView = (TextView) findViewById(R.id.text_content_detail);
        textView.setText(item.text);

        ImageView imageView = (ImageView) findViewById(R.id.image_content_detail);
        imageView.setImageResource(item.imageRes);
    }

}
