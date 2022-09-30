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



public class MainActivity extends AppCompatActivity {

    private int correctAnswers = 0;
    private CheckBox qOneAnswerOne;
    private CheckBox qOneAnswerTwo;
    private CheckBox qOneAnswerThree;
    private CheckBox qFourAnswerOne;
    private CheckBox qFourAnswerTwo;
    private CheckBox qFourAnswerThree;
    EditText userInputAnswer1;
    EditText userInputAnswer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button submit = (Button) findViewById(R.id.submit_button);
        submit.setOnClickListener(submitButtonOnClickListener);

        Button restart = (Button) findViewById(R.id.reset_button);
        restart.setOnClickListener(resetButtonOnClickListener);
    }

    private void checkQuestionOneAnswer() {
        qOneAnswerOne = (CheckBox) findViewById(R.id.first_q_answer_one);
        qOneAnswerTwo = (CheckBox) findViewById(R.id.first_q_answer_two);
        qOneAnswerThree = (CheckBox) findViewById(R.id.first_q_answer_three);
        boolean isQOneAnswerOneChecked = qOneAnswerOne.isChecked();
        boolean isQOneAnswerTwoChecked = qOneAnswerTwo.isChecked();
        boolean isQOneAnswerThreeChecked = qOneAnswerThree.isChecked();
        if (isQOneAnswerOneChecked && !isQOneAnswerTwoChecked && isQOneAnswerThreeChecked) {
            correctAnswers++;
        }
    }

    private void checkQuestionTwoAnswer() {
        RadioButton radioButtonAnswerTwo = (RadioButton) findViewById(R.id.second_q_answer_two);
        boolean isQuestionTwoAnswerTwoChecked = radioButtonAnswerTwo.isChecked();
        if (isQuestionTwoAnswerTwoChecked) {
            correctAnswers++;
        }
    }

    private String getQuestionThreeUserInput() {
        userInputAnswer1 = (EditText) findViewById(R.id.third_q_answer);
        return userInputAnswer1.getText().toString();
    }

    private void checkQuestionThreeAnswer() {
        String answer = getQuestionThreeUserInput();
        if (answer.trim().equalsIgnoreCase(getString(R.string.third_question_answer))) {
            correctAnswers++;
        }
    }

    private void checkQuestionFourAnswer() {
        qFourAnswerOne = (CheckBox) findViewById(R.id.fourth_q_answer_one);
        qFourAnswerTwo = (CheckBox) findViewById(R.id.fourth_q_answer_two);
        qFourAnswerThree = (CheckBox) findViewById(R.id.fourth_q_answer_three);
        boolean isQFourAnswerOneChecked = qFourAnswerOne.isChecked();
        boolean isQFourAnswerTwoChecked = qFourAnswerTwo.isChecked();
        boolean isQFourAnswerThreeChecked = qFourAnswerThree.isChecked();
        if (isQFourAnswerOneChecked && isQFourAnswerTwoChecked && isQFourAnswerThreeChecked) {
            correctAnswers++;
        }
    }

    private void checkQuestionFiveAnswers() {
        RadioButton radioButtonAnswerThree = (RadioButton) findViewById(R.id.fifth_q_answer_three);
        boolean isQuestionFiveAnswerThreeChecked = radioButtonAnswerThree.isChecked();
        if (isQuestionFiveAnswerThreeChecked) {
            correctAnswers++;
        }
    }

    private String getQuestionSixUserInput() {
        userInputAnswer2 = (EditText) findViewById(R.id.sixth_q_answer);
        return userInputAnswer2.getText().toString();
    }

    private void checkQuestionSixAnswer() {
        String answer2 = getQuestionSixUserInput();
        if (answer2.trim().equalsIgnoreCase(getString(R.string.sixth_question_answer))) {
            correctAnswers++;
        }
    }

    private void checkAllQuestions() {
        checkQuestionOneAnswer();
        checkQuestionTwoAnswer();
        checkQuestionThreeAnswer();
        checkQuestionFourAnswer();
        checkQuestionFiveAnswers();
        checkQuestionSixAnswer();
    }

    private void resetCorrectAnswersCounter() {
        correctAnswers = 0;
    }

    private void resetQuiz() {
        RadioGroup one = (RadioGroup) findViewById(R.id.radio_group2);
        one.clearCheck();

        RadioGroup three = (RadioGroup) findViewById(R.id.radio_group5);
        three.clearCheck();

        if (qOneAnswerOne.isChecked()) {
            qOneAnswerOne.toggle();
        }
        if (qOneAnswerTwo.isChecked()) {
            qOneAnswerTwo.toggle();
        }
        if (qOneAnswerThree.isChecked()) {
            qOneAnswerThree.toggle();
        }
        if (qFourAnswerOne.isChecked()) {
            qFourAnswerOne.toggle();
        }
        if (qFourAnswerTwo.isChecked()) {
            qFourAnswerTwo.toggle();
        }
        if (qFourAnswerThree.isChecked()) {
            qFourAnswerThree.toggle();
        }

        userInputAnswer1.getText().clear();
        userInputAnswer2.getText().clear();
    }

    final View.OnClickListener resetButtonOnClickListener = new View.OnClickListener() {
        public void onClick(final View v) {
            resetQuiz();
        }
    };

    final View.OnClickListener submitButtonOnClickListener = new View.OnClickListener() {
        public void onClick(final View v) {
            checkAllQuestions();
            String stringCorrect;
            String stringToastOne;
            String stringToastTwo;
            String stringToastThree;
            if (correctAnswers == 6) {
                Toast.makeText(MainActivity.this, getString(R.string.correct_answers) + correctAnswers + getString(R.string.toast_perfect_score),
                        Toast.LENGTH_LONG).show();
            } else if (correctAnswers < 6 && correctAnswers >= 4) {
                Toast.makeText(MainActivity.this, getString(R.string.correct_answers) + correctAnswers + getString(R.string.toast_almost_there),
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, getString(R.string.correct_answers) + correctAnswers + getString(R.string.toast_you_can_do_better),
                        Toast.LENGTH_LONG).show();
            }
            resetCorrectAnswersCounter();
        }
    };
}
