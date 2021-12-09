package com.example.happynewyear.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.example.happynewyear.R;
import com.example.happynewyear.constant.HappyNewYearConstants;
import com.example.happynewyear.data.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(R.style.Theme_HappyNewYear);
        setContentView(R.layout.activity_details);

        this.mSecurityPreferences = new SecurityPreferences(this);
        this.mViewHolder.checkConfirm = findViewById(R.id.check_confirm);
        this.mViewHolder.checkConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.check_confirm){

            if(this.mViewHolder.checkConfirm.isChecked()){
                this.mSecurityPreferences.storeData(HappyNewYearConstants.PRESENCE_KEY, HappyNewYearConstants.CONFIRM_YES);
            }else{
                this.mSecurityPreferences.storeData(HappyNewYearConstants.PRESENCE_KEY, HappyNewYearConstants.CONFIRM_NO);
            }
        }
    }

    private static class ViewHolder{
        CheckBox checkConfirm;
    }
}