package com.example.happynewyear.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;

import com.example.happynewyear.R;

public class DetailsActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(R.style.Theme_HappyNewYear);
        setContentView(R.layout.activity_details);

        this.mViewHolder.checkConfirm = findViewById(R.id.check_confirm);
    }

    private static class ViewHolder{
        CheckBox checkConfirm;
    }
}