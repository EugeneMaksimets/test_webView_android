package com.poncho.get_from_api_test.validator;

import android.util.Log;
import android.view.View;

import com.poncho.get_from_api_test.MainActivity;
import com.poncho.get_from_api_test.entity.ItemResponse;
import com.poncho.get_from_api_test.service.ShowImageFromUrlService;
import com.poncho.get_from_api_test.service.WebViewService;

public class ItemValidator {

    private final MainActivity activity;

    public ItemValidator(MainActivity activity) {
        this.activity = activity;
    }

    public void validateType(ItemResponse itemResponse) {
        if (itemResponse.getType() != null) {
            switch (itemResponse.getType()) {
                case "text":
                    activity.runOnUiThread(() -> {
                        activity.getProgressBar().setVisibility(View.GONE);
                        activity.getWebView().setVisibility(View.GONE);
                        activity.getImageView().setVisibility(View.GONE);
                        activity.getTextView().setVisibility(View.VISIBLE);
                        activity.getTextView().setText(itemResponse.getMessage());
                    });
                    break;
                case "image":
                    activity.runOnUiThread(() -> {
                        activity.getProgressBar().setVisibility(View.GONE);
                        activity.getWebView().setVisibility(View.GONE);
                        activity.getTextView().setVisibility(View.GONE);
                        activity.getImageView().setVisibility(View.VISIBLE);
                        ShowImageFromUrlService.loadImage(itemResponse.getUrl(),
                                activity.getImageView(),
                                activity.getDeviceWidth());
                    });
                    break;
                case "webview":
                    activity.runOnUiThread(() -> {
                        activity.getProgressBar().setVisibility(View.VISIBLE);
                        activity.getImageView().setVisibility(View.GONE);
                        activity.getTextView().setVisibility(View.GONE);
                        WebViewService.startWebView(activity.getWebView(),
                                itemResponse.getUrl(),
                                activity.getProgressBar());

                        activity.getWebView().setVisibility(View.VISIBLE);
                    });
                    break;
                default:
                    activity.getNextItem();
                    break;
            }
        } else {
            Log.e(MainActivity.TAG, "Type is null");
        }
    }
}
