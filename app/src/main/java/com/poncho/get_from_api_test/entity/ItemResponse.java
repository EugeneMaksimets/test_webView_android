package com.poncho.get_from_api_test.entity;

import com.google.gson.annotations.SerializedName;

public class ItemResponse {
    @SerializedName("id")
    private int id;

    @SerializedName("type")
    private String type;

    @SerializedName("message")
    private String message;

    @SerializedName("url")
    private String url;

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
