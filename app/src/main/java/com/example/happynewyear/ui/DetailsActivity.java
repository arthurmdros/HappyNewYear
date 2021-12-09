package com.example.happynewyear.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

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
        this.mViewHolder.checkYes = findViewById(R.id.check_confirm_yes);
        this.mViewHolder.checkNo = findViewById(R.id.check_confirm_no);
        this.mViewHolder.checkMaybe = findViewById(R.id.check_confirm_maybe);
        this.mViewHolder.buttonSave = findViewById(R.id.button_save);
        this.mViewHolder.buttonSave.setOnClickListener(this);

        this.loadData();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button_save){

            if(this.mViewHolder.checkYes.isChecked()){
                this.mSecurityPreferences.storeData(HappyNewYearConstants.PRESENCE_KEY, HappyNewYearConstants.CONFIRM_YES);
                Intent navigateToMain = new Intent(this, MainActivity.class);
                startActivity(navigateToMain);
            }else if (this.mViewHolder.checkNo.isChecked()){
                this.mSecurityPreferences.storeData(HappyNewYearConstants.PRESENCE_KEY, HappyNewYearConstants.CONFIRM_NO);
                Intent navigateToMain = new Intent(this, MainActivity.class);
                startActivity(navigateToMain);
            }else if (this.mViewHolder.checkMaybe.isChecked()){
                this.mSecurityPreferences.storeData(HappyNewYearConstants.PRESENCE_KEY, HappyNewYearConstants.CONFIRM_MAYBE);
                Intent navigateToMain = new Intent(this, MainActivity.class);
                startActivity(navigateToMain);
            }else{
                Toast.makeText(this, this.getString(R.string.alerta), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void loadData(){
        Bundle extras = getIntent().getExtras();

        if(extras != null){
            String presence = extras.getString(HappyNewYearConstants.PRESENCE_KEY);

            if(presence != null && presence.equals(HappyNewYearConstants.CONFIRM_YES)){
                this.mViewHolder.checkYes.setChecked(true);
            }else if(presence != null && presence.equals(HappyNewYearConstants.CONFIRM_NO)){
                this.mViewHolder.checkNo.setChecked(true);
            }
            else if(presence != null && presence.equals(HappyNewYearConstants.CONFIRM_MAYBE)){
                this.mViewHolder.checkMaybe.setChecked(true);
            }else{
                this.mViewHolder.checkYes.setChecked(false);
                this.mViewHolder.checkNo.setChecked(false);
                this.mViewHolder.checkMaybe.setChecked(false);
            }
        }
    }

    private static class ViewHolder{
        CheckBox checkMaybe;
        CheckBox checkYes;
        CheckBox checkNo;
        Button buttonSave;
    }
}