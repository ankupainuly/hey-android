// Generated by view binder compiler. Do not edit!
package com.example.hey.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.hey.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityOTPBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final ImageView imageView7;

  @NonNull
  public final EditText inputMobileNumber;

  @NonNull
  public final ProgressBar progressbarSendingOtp;

  @NonNull
  public final Button setupButton;

  @NonNull
  public final TextView textView6;

  @NonNull
  public final TextView textView7;

  @NonNull
  public final TextView textView8;

  @NonNull
  public final TextView textView9;

  private ActivityOTPBinding(@NonNull ScrollView rootView, @NonNull ImageView imageView7,
      @NonNull EditText inputMobileNumber, @NonNull ProgressBar progressbarSendingOtp,
      @NonNull Button setupButton, @NonNull TextView textView6, @NonNull TextView textView7,
      @NonNull TextView textView8, @NonNull TextView textView9) {
    this.rootView = rootView;
    this.imageView7 = imageView7;
    this.inputMobileNumber = inputMobileNumber;
    this.progressbarSendingOtp = progressbarSendingOtp;
    this.setupButton = setupButton;
    this.textView6 = textView6;
    this.textView7 = textView7;
    this.textView8 = textView8;
    this.textView9 = textView9;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityOTPBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityOTPBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_o_t_p, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityOTPBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.imageView7;
      ImageView imageView7 = rootView.findViewById(id);
      if (imageView7 == null) {
        break missingId;
      }

      id = R.id.input_mobile_number;
      EditText inputMobileNumber = rootView.findViewById(id);
      if (inputMobileNumber == null) {
        break missingId;
      }

      id = R.id.progressbar_sending_otp;
      ProgressBar progressbarSendingOtp = rootView.findViewById(id);
      if (progressbarSendingOtp == null) {
        break missingId;
      }

      id = R.id.setupButton;
      Button setupButton = rootView.findViewById(id);
      if (setupButton == null) {
        break missingId;
      }

      id = R.id.textView6;
      TextView textView6 = rootView.findViewById(id);
      if (textView6 == null) {
        break missingId;
      }

      id = R.id.textView7;
      TextView textView7 = rootView.findViewById(id);
      if (textView7 == null) {
        break missingId;
      }

      id = R.id.textView8;
      TextView textView8 = rootView.findViewById(id);
      if (textView8 == null) {
        break missingId;
      }

      id = R.id.textView9;
      TextView textView9 = rootView.findViewById(id);
      if (textView9 == null) {
        break missingId;
      }

      return new ActivityOTPBinding((ScrollView) rootView, imageView7, inputMobileNumber,
          progressbarSendingOtp, setupButton, textView6, textView7, textView8, textView9);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
