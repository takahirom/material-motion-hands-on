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

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.takahirom.motion_app.datasource.PixabayResponse;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private List<PixabayResponse.Hit> items;

    RecyclerAdapter(List<PixabayResponse.Hit> items, OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        this.items = items;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, final int position) {
        final float imageScale = holder.itemView.getContext().getResources().getDisplayMetrics().density / 3;
        final int width = (int) (items.get(position).getWebformatWidth() * imageScale);
        final int height = (int) (items.get(position).getWebformatHeight() * imageScale);

        Glide
                .with(holder.itemView.getContext())
                .load(items.get(position).getWebformatURL().replace("640", "340"))
                .override(width, height)
                .into(holder.photoImageView);
        holder.photoImageView.getLayoutParams().width = width;
        holder.photoImageView.getLayoutParams().height = height;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v, items.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
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

    interface OnItemClickListener {
        void onItemClick(View photoView, PixabayResponse.Hit item);
    }
}
