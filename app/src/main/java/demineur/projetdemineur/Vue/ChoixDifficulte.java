package demineur.projetdemineur.Vue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import demineur.projetdemineur.R;

/**
 * Created by yanaël on 18/03/2017.
 */

public class ChoixDifficulte extends Activity {
    private static int DIFFICULTE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.difficulte);

        Button btnFacile= (Button) findViewById(R.id.buttonFacile);
        btnFacile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDifficulte(1);
                Intent intent = new Intent(ChoixDifficulte.this,Demineur.class);
                startActivity(intent);
            }
        });

        Button btnNormal= (Button) findViewById(R.id.buttonNormal);
        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDifficulte(2);
                Intent intent = new Intent(ChoixDifficulte.this,Demineur.class);
                startActivity(intent);
            }
        });

        Button btnDifficile= (Button) findViewById(R.id.buttonDifficile);
        btnDifficile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDifficulte(3);
                Intent intent = new Intent(ChoixDifficulte.this,Demineur.class);
                startActivity(intent);
            }
        });
    }

    public static int getDifficule() {
        return DIFFICULTE;
    }

    private static void setDifficulte(int i) {
        DIFFICULTE = i;
    }
}
