package com.example.johnproject2.Registration;

import android.app.Activity;
import android.content.Context;

import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class InputValidation {
  private Context context;


  public InputValidation(Context context) {
    this.context = context;
  }


  public boolean isInputEditTextFilled(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message) {
    String value = textInputEditText.getText().toString().trim();
    if (value.isEmpty()) {
      textInputLayout.setError(message);
      hideKeyboardFrom(textInputEditText);
      return false;
    } else {
      textInputLayout.setErrorEnabled(false);
    }

    return true;
  }




  private void hideKeyboardFrom(View view) {
    InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
  }
}

