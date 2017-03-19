package demineur.projetdemineur.Vue;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import demineur.projetdemineur.R;

/**
 * Created by yanaël on 18/03/2017.
 */

public class Instruction extends Activity {
    //instruction du jeu
    private String [] instruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instruction);
    }
}
