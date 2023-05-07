package com.rzaninelli.trainningapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


import com.rzaninelli.trainningapp.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void cancelar() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    @Override
    public void onBackPressed() {
        cancelar();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                cancelar();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}