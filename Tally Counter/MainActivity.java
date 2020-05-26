package com.example.tallycounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button countButton;//The countButton is indicated as a Button
    Button resetButton;//The resetButton is indicated as a Button
    TextView countTextView;//The countTextView is indicated as a TextView
    int count = 0;//Initializing the count

    //Executing the onClickCountButton
    private View.OnClickListener onClickCountButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            count++;//Counting with each click
            countTextView.setText(Integer.toString(count));//Convert using Integer.toString(int)
        }
    };

    //Executing the onClickResetButton
    private View.OnClickListener onClickResetButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            count = 0;//Equalling the count to zero
            countTextView.setText(Integer.toString(count));//Convert using Integer.toString(int)
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countButton = findViewById(R.id.countButton);//Connecting the countButton to xml file
        resetButton = findViewById(R.id.resetButton);//Connecting the resetButton to xml file
        countTextView = findViewById(R.id.countTextView);//Connecting the countTextView to xml file

        //Calling the onClickCountButton
        countButton.setOnClickListener(onClickCountButton);
        //Calling the onClickResetButton
        resetButton.setOnClickListener(onClickResetButton);
    }
}
