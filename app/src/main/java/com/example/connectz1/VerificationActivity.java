package com.example.connectz1;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

public class VerificationActivity extends AppCompatActivity {

    private EditText otp1EditText, otp2EditText, otp3EditText, otp4EditText, otp5EditText, otp6EditText;
    private TextInputLayout otp2InputLayout, otp3InputLayout, otp4InputLayout, otp5InputLayout, otp6InputLayout;
    private Button verifyAndProceedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        // Find views
        otp1EditText = findViewById(R.id.otp1EditText);
        otp2EditText = findViewById(R.id.otp2EditText);
        otp3EditText = findViewById(R.id.otp3EditText);
        otp4EditText = findViewById(R.id.otp4EditText);
        otp5EditText = findViewById(R.id.otp5EditText);
        otp6EditText = findViewById(R.id.otp6EditText);

        otp2InputLayout = findViewById(R.id.otp2InputLayout);
        otp3InputLayout = findViewById(R.id.otp3InputLayout);
        otp4InputLayout = findViewById(R.id.otp4InputLayout);
        otp5InputLayout = findViewById(R.id.otp5InputLayout);
        otp6InputLayout = findViewById(R.id.otp6InputLayout);

        verifyAndProceedButton = findViewById(R.id.verifyAndProceedButton);

        // Set TextWatchers
        setOtpTextWatcher(otp1EditText, otp2EditText);
        setOtpTextWatcher(otp2EditText, otp3EditText);
        setOtpTextWatcher(otp3EditText, otp4EditText);
        setOtpTextWatcher(otp4EditText, otp5EditText);
        setOtpTextWatcher(otp5EditText, otp6EditText);

        // Set KeyListeners for backspace navigation
        setBackspaceListener(otp2EditText, otp1EditText);
        setBackspaceListener(otp3EditText, otp2EditText);
        setBackspaceListener(otp4EditText, otp3EditText);
        setBackspaceListener(otp5EditText, otp4EditText);
        setBackspaceListener(otp6EditText, otp5EditText);

        // Set OnEditorActionListener for the last EditText
        otp6EditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // Perform your action here
                return true;
            }
            return false;
        });
    }

    private void setOtpTextWatcher(final EditText currentEditText, final EditText nextEditText) {
        currentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not needed
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not needed
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 1) {
                    nextEditText.requestFocus();
                }
            }
        });
    }

    private void setBackspaceListener(final EditText currentEditText, final EditText previousEditText) {
        currentEditText.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_DEL && currentEditText.getText().length() == 0) {
                previousEditText.requestFocus();
                return true;
            }
            return false;
        });
    }
}
