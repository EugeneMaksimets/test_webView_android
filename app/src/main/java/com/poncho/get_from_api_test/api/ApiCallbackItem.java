package com.poncho.get_from_api_test.api;

import android.util.Log;
import android.widget.Toast;

import com.poncho.get_from_api_test.MainActivity;
import com.poncho.get_from_api_test.entity.ItemResponse;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ApiCallbackItem implements Callback {
    private MainActivity activity;

    public ApiCallbackItem(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        Log.e(MainActivity.TAG, "Failed to fetch entity", e);

        activity.runOnUiThread(() -> {
            Toast.makeText(activity, "Failed to connection", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (!response.isSuccessful()) {
            Log.e(MainActivity.TAG, "Failed to fetch entity: " + response.message());

            activity.runOnUiThread(() -> {
                Toast.makeText(activity, "Failed to connection", Toast.LENGTH_SHORT).show();
            });

            return;
        }

        String responseString = response.body().string();
        ItemResponse itemResponse = ApiConnection.getGson().fromJson(responseString, ItemResponse.class);
        activity.getItemValidator().validateType(itemResponse);
    }
}
