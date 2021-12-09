package com.example.happynewyear.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.happynewyear.R;
import com.example.happynewyear.constant.HappyNewYearConstants;
import com.example.happynewyear.data.SecurityPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private SimpleDateFormat SIMPLE_DATA_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_HappyNewYear);

        setContentView(R.layout.activity_main);

        this.mSecurityPreferences = new SecurityPreferences(this);
        this.mViewHolder.textToday = findViewById(R.id.text_today);
        this.mViewHolder.textDaysLeft = findViewById(R.id.text_days_left);
        this.mViewHolder.textPresence = findViewById(R.id.value_presence);
        this.mViewHolder.buttonConfirm = findViewById(R.id.button_confirm);

        this.mViewHolder.buttonConfirm.setOnClickListener(this);

        this.mViewHolder.textToday.setText(SIMPLE_DATA_FORMAT.format(Calendar.getInstance().getTime()));
        String daysLeft = String.format("%s %s", String.valueOf(this.getDaysLeft()),getString(R.string.dias));
        this.mViewHolder.textDaysLeft.setText(daysLeft);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.verifyPresence();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button_confirm){
            String presence = this.mSecurityPreferences.getStoredData(HappyNewYearConstants.PRESENCE_KEY);
            Intent navigateToDetails = new Intent(this, DetailsActivity.class);

            navigateToDetails.putExtra(HappyNewYearConstants.PRESENCE_KEY, presence);
            startActivity(navigateToDetails);
        }
    }

    private void verifyPresence(){
        String presence = this.mSecurityPreferences.getStoredData(HappyNewYearConstants.PRESENCE_KEY);

        if(presence.equals("")){
            this.mViewHolder.textPresence.setText(getString(R.string.não_confirmado));
        }else if(presence.equals(HappyNewYearConstants.CONFIRM_YES)){
            this.mViewHolder.textPresence.setText(getString(R.string.sim));
        }else if(presence.equals(HappyNewYearConstants.CONFIRM_NO)){
            this.mViewHolder.textPresence.setText(getString(R.string.não));
        }else{
            this.mViewHolder.textPresence.setText(getString(R.string.talvez));
        }
    }

    private int getDaysLeft(){
        Calendar calendarToday = Calendar.getInstance();
        int today = calendarToday.get(Calendar.DAY_OF_YEAR);

        Calendar calendarLastDay = Calendar.getInstance();
        int lastDay = calendarLastDay.getActualMaximum(Calendar.DAY_OF_YEAR);

        return lastDay - today;
    }

    private static class ViewHolder {
        TextView textToday;
        TextView textDaysLeft;
        TextView textPresence;
        Button buttonConfirm;
    }
}