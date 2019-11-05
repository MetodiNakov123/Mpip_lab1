package nakov.metodi.lab_intents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnExplicitActivity;
    private Button btnImplicitActivity;
    private Button btnSend;
    private Button btnPicture;
    private TextView textView;

    public static int REQ_CODE_EXPLICIT = 1;
    public static int REQ_CODE_PICTURE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListeners();

    }

    private void initView(){
        btnExplicitActivity = findViewById(R.id.btnExplicitActivity);
        btnImplicitActivity = findViewById(R.id.btnImplicitActivity);
        btnSend = findViewById(R.id.btnSend);
        btnPicture = findViewById(R.id.btnPicture);
        textView = findViewById(R.id.textView);
    }

    private void initListeners(){
        btnExplicitActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent explicitIntent = new Intent(MainActivity.this, ExplicitActivity.class);
                startActivityForResult(explicitIntent, REQ_CODE_EXPLICIT );
            }
        });


        btnImplicitActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent implicitActivity = new Intent();
                implicitActivity.setAction(ImplicitActivity.INTENT_FILTER_ACTION);
                if (implicitActivity.resolveActivity(getPackageManager()) != null){
                    startActivity(implicitActivity);
                }
            }
        });


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TITLE,"MPiP Send Title");
                intent.putExtra(Intent.EXTRA_SUBJECT, "MPiP Send Title");
                intent.putExtra(Intent.EXTRA_TEXT, "Content send from MainActivity");
                intent.setType("text/plain");
                Intent chooser = Intent.createChooser(intent, "Isprati poraka preku: ");
                if (intent.resolveActivity(getPackageManager()) != null){
                    startActivity(chooser);
                }

            }
        });


        btnPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                if (intent.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(intent, REQ_CODE_PICTURE);
                }

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String result = null;

        if (requestCode == REQ_CODE_EXPLICIT){
            if(resultCode == Activity.RESULT_OK){
                if (data != null){
                    result = data.getExtras().getString(ExplicitActivity.RESULT);
                }

                if (result != null){
                    textView.setText(result);
                }
            } else if(resultCode == Activity.RESULT_CANCELED){
                // ne pravi nishto
            }
        }

        else

            if (requestCode == REQ_CODE_PICTURE) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            if (data != null && data.getData() != null) {
                intent.setDataAndType(Uri.parse(data.getData().toString()), "image/*");
            }
            String title = "Image: ";
            Intent chooser = Intent.createChooser(intent, title);
            if (intent.resolveActivity(getPackageManager()) != null){
                startActivity(chooser);
            }
        }

    }





}
