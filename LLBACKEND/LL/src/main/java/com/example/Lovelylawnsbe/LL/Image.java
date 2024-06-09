package com.example.Lovelylawnsbe.LL;

import jakarta.persistence.Embeddable;

@Embeddable
public class Image {
    private String url;
    private String thumbnail;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}

