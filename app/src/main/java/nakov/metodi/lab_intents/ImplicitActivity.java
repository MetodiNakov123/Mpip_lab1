package nakov.metodi.lab_intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ImplicitActivity extends AppCompatActivity {

    public static final String INTENT_FILTER_ACTION = "mk.ukim.finki.mpip.IMPLICIT_ACTION";

    private TextView prikazi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);

        prikazi = findViewById(R.id.prikazi);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> lista = getApplicationContext().getPackageManager().queryIntentActivities(intent, 0);

        ArrayList<String> listaAktivnosti = new ArrayList<>();

        for(ResolveInfo rs: lista){
            String []parts = rs.activityInfo.toString().split("\\.");  // se odvojuvat vo niza spored "."
            listaAktivnosti.add(parts[parts.length-1].substring(0,parts[parts.length-1].length()-1)); // posledniot element
        }

        StringBuilder sb = new StringBuilder();
        for (String s: listaAktivnosti){
            sb.append(s);
            sb.append("\n");
        }

        prikazi.setText(sb.toString());

    }
}
