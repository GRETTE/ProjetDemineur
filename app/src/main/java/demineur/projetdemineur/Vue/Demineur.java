package demineur.projetdemineur.Vue;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import demineur.projetdemineur.Controleur.CaseAdapter;
import demineur.projetdemineur.Modele.Case;
import demineur.projetdemineur.R;

/**
 * Created by yanaël on 19/03/2017.
 */

public class Demineur extends Activity {
    public static boolean PERDU;
    public final static int LIGNES = 10;
    public final static int COLONNES= 9;
    public ArrayList<Case> cases;
    private ArrayList<Case> bombes;
    private int nbBombes = 20;
    private CaseAdapter adapter;
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demineur);
        Button btnRecommencer = (Button)findViewById(R.id.buttonRecommencer);
        Button btnRetour = (Button)findViewById(R.id.buttonRetourDemineur);
        Button btnPause = (Button)findViewById(R.id.buttonPause);
        btnRecommencer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initCases();
            }
        });

        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        gridView = (GridView)findViewById(R.id.gridViewTab);
        initCases();

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info();
            }
        });
    }

    //initialise les cases
    private void initCases() {

        PERDU = false;
        //création des cases
        cases = new ArrayList<Case>();
        adapter = new CaseAdapter(this);
        gridView.setNumColumns(COLONNES);
        gridView.setAdapter(adapter);
        Case [][] tabCases = new Case[LIGNES][COLONNES];
        for(int i = 0; i < LIGNES; ++i) {
            for(int j = 0; j< COLONNES; ++j) {
                Case c = new Case(this);
                cases.add(c);
                tabCases[i][j] = c;
            }
        }
        //setup des voisons
        for(int i = 0;i< LIGNES;++i) {
            for (int j = 0; j < COLONNES; ++j) {
                for (int k = i - 1; k < i + 2; ++k) {
                    for (int l = j - 1; l < j + 2; l++) {
                        try {

                            if (!(i == k && j == l))
                                tabCases[i][j].ajouterVoisin(tabCases[k][l]);
                        } catch (ArrayIndexOutOfBoundsException e) {
                        }
                    }
                }
            }
        }
        initBombes();
    }

    private void initBombes() {
        //Initialisation setup des bombs
        bombes = new ArrayList<Case>();
        nbBombes *= ChoixDifficulte.getDifficule();
        int i = 0;
        int nbBombesPlacees = 0;
        Random r = new Random();
        while(nbBombesPlacees != nbBombes){
            if(!cases.get(i).estBombe() && r.nextInt(100)<10){
                cases.get(i).setBombe();
                nbBombesPlacees++;
                bombes.add(cases.get(i));
            }
            ++i;
            if(i >= COLONNES*LIGNES) {
                i=0;
            }
        }
    }

    //permet de choisir en random quelles cases cacheront les bombes
    public void info() {
        for(int i = 0; i< 3;i++) {
            cases.get(i).Info();
        }
    }
    public void perdu() {
        PERDU = true;
    }
}
