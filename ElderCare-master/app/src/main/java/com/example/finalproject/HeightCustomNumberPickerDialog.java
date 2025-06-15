package com.example.finalproject;

import androidx.annotation.NonNull;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.NumberPicker;

public class HeightCustomNumberPickerDialog extends Dialog {

    private NumberPicker numberPicker;
    private Button btnOk, btnCancel;
    private OnNumberPickerClickListener listener;

    public HeightCustomNumberPickerDialog(@NonNull Context context, int initialValue, int minValue, int maxValue, OnNumberPickerClickListener listener) {
        super(context);
        setContentView(R.layout.activity_height_custom_number_picker_dialog);
        setTitle("Select a number");

        numberPicker = findViewById(R.id.numberPicker);
        btnOk = findViewById(R.id.btnOk);
        btnCancel = findViewById(R.id.btnCancel);

        numberPicker.setMinValue(minValue);
        numberPicker.setMaxValue(maxValue);
        numberPicker.setValue(initialValue);

        btnOk.setOnClickListener(v -> {
            if (listener != null) {
                listener.onNumberPickerClick(numberPicker.getValue());
            }
            dismiss();
        });

        btnCancel.setOnClickListener(v -> dismiss());
    }

    public interface OnNumberPickerClickListener {
        void onNumberPickerClick(int value);
    }
}
