package com.poncho.get_from_api_test.service;

import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ShowImageFromUrlService {
    public static void loadImage(String url, ImageView imageView, int width) {
        try {
            Picasso.get()
                    .load(url)
                    .resize(width, 0)
                    .into(imageView);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
