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

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PixabayResponse implements Parcelable {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("totalHits")
    @Expose
    private Integer totalHits;
    @SerializedName("hits")
    @Expose
    private List<Hit> hits = null;
    public final static Parcelable.Creator<PixabayResponse> CREATOR = new Creator<PixabayResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PixabayResponse createFromParcel(Parcel in) {
            PixabayResponse instance = new PixabayResponse();
            instance.total = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.totalHits = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(instance.hits, (Hit.class.getClassLoader()));
            return instance;
        }

        public PixabayResponse[] newArray(int size) {
            return (new PixabayResponse[size]);
        }

    };

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(Integer totalHits) {
        this.totalHits = totalHits;
    }

    public List<Hit> getHits() {
        return hits;
    }

    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(total);
        dest.writeValue(totalHits);
        dest.writeList(hits);
    }

    public int describeContents() {
        return 0;
    }

    public static class Hit implements Parcelable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("pageURL")
        @Expose
        private String pageURL;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("tags")
        @Expose
        private String tags;
        @SerializedName("previewURL")
        @Expose
        private String previewURL;
        @SerializedName("previewWidth")
        @Expose
        private Integer previewWidth;
        @SerializedName("previewHeight")
        @Expose
        private Integer previewHeight;
        @SerializedName("webformatURL")
        @Expose
        private String webformatURL;
        @SerializedName("webformatWidth")
        @Expose
        private Integer webformatWidth;
        @SerializedName("webformatHeight")
        @Expose
        private Integer webformatHeight;
        @SerializedName("imageWidth")
        @Expose
        private Integer imageWidth;
        @SerializedName("imageHeight")
        @Expose
        private Integer imageHeight;
        @SerializedName("imageSize")
        @Expose
        private Integer imageSize;
        @SerializedName("views")
        @Expose
        private Integer views;
        @SerializedName("downloads")
        @Expose
        private Integer downloads;
        @SerializedName("favorites")
        @Expose
        private Integer favorites;
        @SerializedName("likes")
        @Expose
        private Integer likes;
        @SerializedName("comments")
        @Expose
        private Integer comments;
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("user")
        @Expose
        private String user;
        @SerializedName("userImageURL")
        @Expose
        private String userImageURL;
        public final static Parcelable.Creator<Hit> CREATOR = new Creator<Hit>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Hit createFromParcel(Parcel in) {
                Hit instance = new Hit();
                instance.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.pageURL = ((String) in.readValue((String.class.getClassLoader())));
                instance.type = ((String) in.readValue((String.class.getClassLoader())));
                instance.tags = ((String) in.readValue((String.class.getClassLoader())));
                instance.previewURL = ((String) in.readValue((String.class.getClassLoader())));
                instance.previewWidth = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.previewHeight = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.webformatURL = ((String) in.readValue((String.class.getClassLoader())));
                instance.webformatWidth = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.webformatHeight = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.imageWidth = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.imageHeight = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.imageSize = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.views = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.downloads = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.favorites = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.likes = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.comments = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.userId = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.user = ((String) in.readValue((String.class.getClassLoader())));
                instance.userImageURL = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public Hit[] newArray(int size) {
                return (new Hit[size]);
            }

        };

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getPageURL() {
            return pageURL;
        }

        public void setPageURL(String pageURL) {
            this.pageURL = pageURL;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getPreviewURL() {
            return previewURL;
        }

        public void setPreviewURL(String previewURL) {
            this.previewURL = previewURL;
        }

        public Integer getPreviewWidth() {
            return previewWidth;
        }

        public void setPreviewWidth(Integer previewWidth) {
            this.previewWidth = previewWidth;
        }

        public Integer getPreviewHeight() {
            return previewHeight;
        }

        public void setPreviewHeight(Integer previewHeight) {
            this.previewHeight = previewHeight;
        }

        public String getWebformatURL() {
            return webformatURL;
        }

        public void setWebformatURL(String webformatURL) {
            this.webformatURL = webformatURL;
        }

        public Integer getWebformatWidth() {
            return webformatWidth;
        }

        public void setWebformatWidth(Integer webformatWidth) {
            this.webformatWidth = webformatWidth;
        }

        public Integer getWebformatHeight() {
            return webformatHeight;
        }

        public void setWebformatHeight(Integer webformatHeight) {
            this.webformatHeight = webformatHeight;
        }

        public Integer getImageWidth() {
            return imageWidth;
        }

        public void setImageWidth(Integer imageWidth) {
            this.imageWidth = imageWidth;
        }

        public Integer getImageHeight() {
            return imageHeight;
        }

        public void setImageHeight(Integer imageHeight) {
            this.imageHeight = imageHeight;
        }

        public Integer getImageSize() {
            return imageSize;
        }

        public void setImageSize(Integer imageSize) {
            this.imageSize = imageSize;
        }

        public Integer getViews() {
            return views;
        }

        public void setViews(Integer views) {
            this.views = views;
        }

        public Integer getDownloads() {
            return downloads;
        }

        public void setDownloads(Integer downloads) {
            this.downloads = downloads;
        }

        public Integer getFavorites() {
            return favorites;
        }

        public void setFavorites(Integer favorites) {
            this.favorites = favorites;
        }

        public Integer getLikes() {
            return likes;
        }

        public void setLikes(Integer likes) {
            this.likes = likes;
        }

        public Integer getComments() {
            return comments;
        }

        public void setComments(Integer comments) {
            this.comments = comments;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getUserImageURL() {
            return userImageURL;
        }

        public void setUserImageURL(String userImageURL) {
            this.userImageURL = userImageURL;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(pageURL);
            dest.writeValue(type);
            dest.writeValue(tags);
            dest.writeValue(previewURL);
            dest.writeValue(previewWidth);
            dest.writeValue(previewHeight);
            dest.writeValue(webformatURL);
            dest.writeValue(webformatWidth);
            dest.writeValue(webformatHeight);
            dest.writeValue(imageWidth);
            dest.writeValue(imageHeight);
            dest.writeValue(imageSize);
            dest.writeValue(views);
            dest.writeValue(downloads);
            dest.writeValue(favorites);
            dest.writeValue(likes);
            dest.writeValue(comments);
            dest.writeValue(userId);
            dest.writeValue(user);
            dest.writeValue(userImageURL);
        }

        public int describeContents() {
            return 0;
        }

    }

}
