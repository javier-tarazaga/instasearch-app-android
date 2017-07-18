package com.javiertarazaga.instasearch.presentation.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class InstagramOAuthActivity extends BaseActivity {

  private static final String TAG = InstagramOAuthActivity.class.getName();

  private static final String CLIENT_ID       = "974bc375f6ac4f0b883484e72d786e24";
  private static final String REDIRECT_URI    = "http://instasearchapp.com/auth/instagram/callback";
  private static final String FAILURE_URL     = "http://instasearchapp.com/auth/failure";
  private static final String AUTH_URI        = "https://instagram.com/oauth/authorize/?client_id="
      + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI + "&response_type=token&scope=public_content";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    WebView wb = new WebView(this);
    setContentView(wb);
    setClient(this, wb);

    wb.loadUrl(AUTH_URI);
  }

  private void setClient(final Activity act, WebView webView) {
    webView.setWebViewClient(new WebViewClient() {

      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.d(TAG, "URL : " + url);
        if (url.startsWith(REDIRECT_URI)) {
          if (url.contains("access_token")) {
            String accessToken = url.split("#access_token=")[1];
            Log.d(TAG, "Instagram TOKEN: " + accessToken);
          } else if (url.contains("error_reason")) {
            String error = url.contains("user_denied") ? "User denied access" : "Authentication failed";
            // Utils.notify(new RuntimeException(error + " at " + TAG));
            Log.e(TAG, error);
            Toast.makeText(act, "Access denied to Instagram", Toast.LENGTH_SHORT).show();
            finish();
          }
          return true;
        } else if (url.startsWith(FAILURE_URL)) {
          // TODO: Alert unknown error
          finish();
          return true;
        }
        return super.shouldOverrideUrlLoading(view, url);
      }
    });
  }
}
