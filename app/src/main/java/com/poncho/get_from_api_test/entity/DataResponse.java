package com.poncho.get_from_api_test.entity;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataResponse {
    @SerializedName("data")
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public static class Item {
        @SerializedName("id")
        private String id;

        public String getId() {
            return id;
        }
    }
}

