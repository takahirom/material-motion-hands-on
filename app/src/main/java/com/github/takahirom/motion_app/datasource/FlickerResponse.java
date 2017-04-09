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

import java.util.List;

public class FlickerResponse {

    private String title;
    private String link;
    private String description;
    private String modified;
    private String generator;
    private List<Item> items = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public static class Item implements Parcelable {

        private String title;
        private String link;
        private Media media;
        private String dateTaken;
        private String description;
        private String published;
        private String author;
        private String authorId;
        private String tags;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public Media getMedia() {
            return media;
        }

        public void setMedia(Media media) {
            this.media = media;
        }

        public String getDateTaken() {
            return dateTaken;
        }

        public void setDateTaken(String dateTaken) {
            this.dateTaken = dateTaken;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPublished() {
            return published;
        }

        public void setPublished(String published) {
            this.published = published;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthorId() {
            return authorId;
        }

        public void setAuthorId(String authorId) {
            this.authorId = authorId;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.title);
            dest.writeString(this.link);
            dest.writeParcelable(this.media, flags);
            dest.writeString(this.dateTaken);
            dest.writeString(this.description);
            dest.writeString(this.published);
            dest.writeString(this.author);
            dest.writeString(this.authorId);
            dest.writeString(this.tags);
        }

        public Item() {
        }

        protected Item(Parcel in) {
            this.title = in.readString();
            this.link = in.readString();
            this.media = in.readParcelable(Media.class.getClassLoader());
            this.dateTaken = in.readString();
            this.description = in.readString();
            this.published = in.readString();
            this.author = in.readString();
            this.authorId = in.readString();
            this.tags = in.readString();
        }

        public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
            @Override
            public Item createFromParcel(Parcel source) {
                return new Item(source);
            }

            @Override
            public Item[] newArray(int size) {
                return new Item[size];
            }
        };
    }

    public static class Media implements Parcelable {

        private String m;

        public String getM() {
            return m;
        }

        public void setM(String m) {
            this.m = m;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.m);
        }

        public Media() {
        }

        protected Media(Parcel in) {
            this.m = in.readString();
        }

        public static final Parcelable.Creator<Media> CREATOR = new Parcelable.Creator<Media>() {
            @Override
            public Media createFromParcel(Parcel source) {
                return new Media(source);
            }

            @Override
            public Media[] newArray(int size) {
                return new Media[size];
            }
        };
    }
}
