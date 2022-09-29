package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView questions, questionNumber;
    private Button option1Btn, option2Btn;
    RadioGroup radioGroup;
    RadioButton radioButton;
    private ArrayList<QuizModal> quizModalArrayList;
    Random random;
    CheckBox yesCheckBox, noCheckBox;

    //current pos is used to store the random position
    int currentScore = 0, questionAttempted = 1, currentPosition, nextScore = 0, radioScore, finalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questions = findViewById(R.id.quiz_questions);
        questionNumber = findViewById(R.id.Questions_attempted);
        option1Btn = findViewById(R.id.btn_option1);
        option2Btn  = findViewById(R.id.btn_option2);
        radioGroup = findViewById(R.id.radioGroup);
        yesCheckBox = (CheckBox) findViewById(R.id.first_checkbox);
        noCheckBox = (CheckBox) findViewById(R.id.second_checkbox);
        quizModalArrayList  = new ArrayList<>();
        random = new Random();
        getQuizQuestion(quizModalArrayList);
        currentPosition =  random.nextInt(quizModalArrayList.size());
        setDataToViews(currentPosition);



    }

    public void firstCheckBox(View v){
        boolean hasCheckbox1 = yesCheckBox.isChecked();
        if(hasCheckbox1){
            nextScore = nextScore + 1;
        }

    }

    public void secondCheckBox(View v){
        boolean hasCheckbox2 = noCheckBox.isChecked();
        if(hasCheckbox2){
            nextScore = 0;
        }

    }

    public void onRadioButtonClicked1(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();

          radioButton = findViewById(radioId);
        boolean hasCheck = radioButton.isChecked();
        if(hasCheck){
            radioScore = radioScore +1;
        }



    }


    public void onRadioButtonClicked2(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);
        boolean hasCheck = radioButton.isChecked();
        if(hasCheck){
            radioScore = 0;
        }
    }


    public void CheckOption1(View v) {

        // we check if the option is correct or not
        if(quizModalArrayList.get(currentPosition).getAnswer().trim().toLowerCase().equals(option1Btn.getText().toString().trim().toLowerCase())){
            currentScore++;
        }
        questionAttempted++;
        currentPosition = random.nextInt(quizModalArrayList.size());
        //we use this to change the position
        setDataToViews(currentPosition);

    }

    public void CheckOption2(View v){

        // we check if the option is correct or not
        if(quizModalArrayList.get(currentPosition).getAnswer().trim().toLowerCase().equals(option2Btn.getText().toString().trim().toLowerCase())){
            currentScore++;
        }
        questionAttempted++;
        currentPosition = random.nextInt(quizModalArrayList.size());
        //we use this to change the position
        setDataToViews(currentPosition);

    }




private void showBottomSheet(){
    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
    View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.score_bottom_sheet, (LinearLayout)findViewById(R.id.IDScore));
    TextView ScoreTextView=  bottomSheetView.findViewById(R.id.Score_id);
    Button restartQuizBtn = bottomSheetView.findViewById(R.id.Restart_Btn);
    EditText nameField = (EditText) findViewById(R.id.name_field);
    String name = nameField.getText().toString();
    finalScore = nextScore + radioScore + currentScore;
    ScoreTextView.setText("Hi " + name +"\n Your Score is \n" + finalScore + "/10");
    restartQuizBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            currentPosition = random.nextInt(quizModalArrayList.size());
            setDataToViews(currentPosition);
            questionAttempted = 1;
            currentScore = 0;
            bottomSheetDialog.dismiss();

        }
    });
    bottomSheetDialog.setCancelable(false);
    bottomSheetDialog.setContentView(bottomSheetView);
    bottomSheetDialog.show();

}



    private void setDataToViews(int currentPosition) {
        questionNumber.setText(" More questions attempted : "+ questionAttempted +"/8");
        if(questionAttempted == 8){
            showBottomSheet();
        }else{
            questions.setText(quizModalArrayList.get(currentPosition).getQuestion());
            option1Btn.setText(quizModalArrayList.get(currentPosition).getOption1());
            option2Btn.setText(quizModalArrayList.get(currentPosition).getOption2());
        }

    }


    private void getQuizQuestion(ArrayList<QuizModal> quizModalArrayList) {
        quizModalArrayList.add(new QuizModal("Which Platform is in partnership with Access Bank for the Advanced African Scholarship?", "Udacity","Udemy", "Udacity"));
        quizModalArrayList.add(new QuizModal("Who is the CEO of Udacity?", "Gabriel Dalporto","Bill Gates", "Gabriel Dalporto"));
        quizModalArrayList.add(new QuizModal("Which of these is a domestic animal?", "Dog","Bear", "Dog"));
        quizModalArrayList.add(new QuizModal("What is the meaning of DRY in programmming?", "Dry your Code on the editor ","Do not repeat Yourself", "Do not repeat Yourself"));
        quizModalArrayList.add(new QuizModal("Where is Access Bank Located?", "Europe","Africa", "Africa"));
        quizModalArrayList.add(new QuizModal("How many number makes a dozen?", "12","24", "12"));
        quizModalArrayList.add(new QuizModal("How Many number makes a Score?", "30","20", "20"));

    }
}









