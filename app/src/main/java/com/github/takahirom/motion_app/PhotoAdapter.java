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

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.takahirom.motion_app.datasource.PixabayResponse;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.List;

class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    private final ColorDrawable[] loadingPlaceholders;
    private Activity activity;
    private OnItemClickListener onItemClickListener;
    private List<PhotoItem> photoItems = new ArrayList<>();

    PhotoAdapter(Activity activity, OnItemClickListener onItemClickListener) {
        this.activity = activity;
        this.onItemClickListener = onItemClickListener;
        final int[] placeholderColors = activity.getResources().getIntArray(R.array.loading_placeholders_light);
        loadingPlaceholders = new ColorDrawable[placeholderColors.length];
        for (int i = 0; i < placeholderColors.length; i++) {
            loadingPlaceholders[i] = new ColorDrawable(placeholderColors[i]);
        }
    }


    void setItems(List<PixabayResponse.Hit> hitPhotos) {
        for (PixabayResponse.Hit hitPhoto : hitPhotos) {
            photoItems.add(new PhotoItem(hitPhoto));
        }
        notifyDataSetChanged();
    }

    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(activity).inflate(R.layout.item_photo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PhotoAdapter.ViewHolder holder, final int position) {
        final PhotoItem item = photoItems.get(position);
        // For flexbox
        final float imageScale = activity.getResources().getDisplayMetrics().density / 3;
        final int width = (int) (item.hitPhoto.getWebformatWidth() * imageScale);
        final int height = (int) (item.hitPhoto.getWebformatHeight() * imageScale);
        holder.photoImageView.getLayoutParams().width = width;
        holder.photoImageView.getLayoutParams().height = height;

        ViewCompat.setBackground(holder.photoImageView, loadingPlaceholders[position % loadingPlaceholders.length]);
        Glide
                .with(activity)
                .load(item.hitPhoto.getWebformatURL().replace("640", "340"))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .fitCenter()
                .override(width, height)
                .into(holder.photoImageView);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v, item.hitPhoto);
            }
        });


    }

    @Override
    public int getItemCount() {
        return photoItems.size();
    }

    interface OnItemClickListener {
        void onItemClick(View photoView, PixabayResponse.Hit item);
    }

    private static class PhotoItem {
        final PixabayResponse.Hit hitPhoto;
        // You can add field

        PhotoItem(PixabayResponse.Hit hitPhoto) {
            this.hitPhoto = hitPhoto;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView photoImageView;

        ViewHolder(View itemView) {
            super(itemView);
            photoImageView = (ImageView) itemView.findViewById(R.id.photo);
            FlexboxLayoutManager.LayoutParams lp = (FlexboxLayoutManager.LayoutParams)
                    itemView.getLayoutParams();
            lp.setFlexGrow(1.0f);
        }
    }
}
