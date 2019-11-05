package nakov.metodi.lab_intents;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ExplicitActivity extends AppCompatActivity {

    private Button btnOK;
    private Button btnOtkazi;
    private EditText textInput;

    public static String RESULT = "RESULT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit);

        initViews();
        initListeners();
    }

    private void initViews(){
        btnOK = findViewById(R.id.btnOk);
        btnOtkazi = findViewById(R.id.btnOtkazi);
        textInput = findViewById(R.id.textInput);
    }


    private void initListeners(){
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(RESULT, textInput.getText().toString());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });


        btnOtkazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

    }

}
