package com.poncho.get_from_api_test.api;

import com.poncho.get_from_api_test.MainActivity;

public class ApiService {
    private ApiConnection apiConnection;

    public ApiService() {
        apiConnection = new ApiConnection();
    }

    public void getAllId(MainActivity activity) {
        apiConnection.getAllId(new ApiCallbackData(activity));
    }

    public void getInfoById(String id, MainActivity activity) {
        apiConnection.getById(id, new ApiCallbackItem(activity));
    }
}