package ntu.ce2006.swensens.hdbdesirabilityapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ntu.ce2006.swensens.hdbdesirabilityapp.R;

public class SearchActivity extends AppCompatActivity implements ActivityMessages {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    @Override
    public void displayHelp() {

    }

    @Override
    public void displayErrorMessage(Exception e) {

    }
}
