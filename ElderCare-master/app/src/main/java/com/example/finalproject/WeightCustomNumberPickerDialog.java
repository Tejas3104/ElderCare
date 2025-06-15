package com.example.finalproject;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
public class WeightCustomNumberPickerDialog extends Dialog {
    private NumberPicker numberPicker;
    private Button btnOk, btnCancel;
    private OnNumberPickerClickListener listener;
    public WeightCustomNumberPickerDialog(@NonNull Context context, int initialValue, int minValue, int maxValue, OnNumberPickerClickListener listener) {
        super(context);
        setContentView(R.layout.activity_weight_custom_number_picker_dialog);
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
