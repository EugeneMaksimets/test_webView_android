package com.poncho.get_from_api_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.poncho.get_from_api_test.api.ApiService;
import com.poncho.get_from_api_test.entity.DataResponse;
import com.poncho.get_from_api_test.entity.ItemResponse;
import com.poncho.get_from_api_test.service.NetworkInfoService;
import com.poncho.get_from_api_test.validator.ItemValidator;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private final String ERROR_CONNECT_MESSAGE = "Internet connection failure.\n Check your connection";

    private DataResponse savedDataResponse;
    private ApiService apiService;
    private ItemValidator itemValidator;

    private TextView textView;
    private WebView webView;
    private ImageView imageView;
    private ProgressBar progressBar;

    private int deviceWidth;
    private int listNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);
        Button button = findViewById(R.id.button);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        deviceWidth = displayMetrics.widthPixels;

        itemValidator = new ItemValidator(MainActivity.this);
        apiService = new ApiService();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNextItem();
            }
        });

        if (NetworkInfoService.isConnectedToInternet(MainActivity.this)) {
            apiService.getAllId(MainActivity.this);
        } else {
            createErrorItem(ERROR_CONNECT_MESSAGE);
        }

    }

    private void createErrorItem(String message) {
        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setType("text");
        itemResponse.setMessage(message);
        itemValidator.validateType(itemResponse);
    }

    public void getNextItem() {
        if (NetworkInfoService.isConnectedToInternet(MainActivity.this)) {
            listNumber++;
            try {
                getInfoFromAPI(savedDataResponse);
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                Log.e(MainActivity.TAG, "index: " + listNumber, e);
                listNumber = 0;
                getIdsFromAPI();
            }
        } else {
            createErrorItem(ERROR_CONNECT_MESSAGE);
        }

    }

    public void getIdsFromAPI() {
        apiService.getAllId(MainActivity.this);
    }

    public void getInfoFromAPI(DataResponse dataResponse) {
        String id = dataResponse.getItems().get(listNumber).getId();
        apiService.getInfoById(id, MainActivity.this);
    }

    public void setSavedDataResponse(DataResponse savedDataResponse) {
        this.savedDataResponse = savedDataResponse;
    }

    public WebView getWebView() {
        return webView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getTextView() {
        return textView;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public int getDeviceWidth() {
        return deviceWidth;
    }

    public ItemValidator getItemValidator() {
        return itemValidator;
    }
}