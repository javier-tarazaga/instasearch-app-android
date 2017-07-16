package com.javiertarazaga.instasearch.presentation.view.activity;

import android.os.Bundle;
import android.widget.Button;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Main application screen. This is the app entry point.
 */
public class MainActivity extends BaseActivity {

  @Bind(com.javiertarazaga.instasearch.presentation.R.id.btn_LoadData) Button btn_LoadData;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(com.javiertarazaga.instasearch.presentation.R.layout.activity_main);
    ButterKnife.bind(this);
  }

  /**
   * Goes to the user list screen.
   */
  @OnClick(com.javiertarazaga.instasearch.presentation.R.id.btn_LoadData)
  void navigateToUserList() {
    this.navigator.navigateToUserList(this);
  }
}
