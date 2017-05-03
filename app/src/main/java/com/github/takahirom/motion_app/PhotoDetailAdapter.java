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

import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.takahirom.motion_app.datasource.PixabayResponse;

import java.util.ArrayList;
import java.util.List;

class PhotoDetailAdapter extends RecyclerView.Adapter<PhotoDetailAdapter.PhotoDetailViewHolder> {
    private List<Item> photoDetails = new ArrayList<>();

    PhotoDetailAdapter() {
    }

    void setPhotoDetails(PixabayResponse.Hit photoDetail) {
        this.photoDetails.add(new Item(R.drawable.ic_person_black_24dp, photoDetail.getUser()));
        this.photoDetails.add(new Item(R.drawable.ic_label_black_24dp, photoDetail.getTags()));
        this.photoDetails.add(new Item(R.drawable.ic_file_download_black_24dp, String.valueOf(photoDetail.getDownloads())));
        this.photoDetails.add(new Item(R.drawable.ic_star_black_24dp, String.valueOf(photoDetail.getFavorites())));
    }

    @Override
    public PhotoDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_detail, parent, false);
        return new PhotoDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoDetailViewHolder holder, int position) {
        Item item = photoDetails.get(position);
        holder.imageView.setImageResource(item.imageRes);
        holder.textView.setText(item.text);
    }

    @Override
    public int getItemCount() {
        return photoDetails.size();
    }

    private static class Item {
        @DrawableRes
        final int imageRes;
        final String text;

        Item(int imageRes, String text) {
            this.imageRes = imageRes;
            this.text = text;
        }
    }

    static class PhotoDetailViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textView;

        PhotoDetailViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_detail);
            textView = (TextView) itemView.findViewById(R.id.text_detail);
        }
    }
}
