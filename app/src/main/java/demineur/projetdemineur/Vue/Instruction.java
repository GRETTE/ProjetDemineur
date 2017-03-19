package demineur.projetdemineur.Vue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import demineur.projetdemineur.R;

/**
 * Created by yanaël on 18/03/2017.
 */

public class Instruction extends Activity {
    //instruction du jeu
    private String [] instruction = new String[4];
    private int numPage = 0;
    private int tailleInstruc =4;
    private TextView textViewinstructions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instruction);
        creerInstuction();
        textViewinstructions = (TextView) findViewById(R.id.instruction);

        final Button btnRetour = (Button)findViewById(R.id.buttonRetourInstruction);
        final Button btnSuite= (Button)findViewById(R.id.buttonSuite);


        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numPage+1 ==tailleInstruc) {
                    btnSuite.setText("Suivant");
                    prevPage();
                }
                else if(numPage ==0) {
                    finish();
                }
                else{
                    prevPage();
                    if(numPage == 0) {
                        btnRetour.setText("Retour");
                    }
                }
            }
        });



        btnSuite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numPage +1 == tailleInstruc) {
                    Intent intent = new Intent(Instruction.this,ChoixDifficulte.class);
                    startActivity(intent);
                    finish();
                }
                else if(numPage == 0) {
                    btnRetour.setText("Précédent");
                    nextPage();
                }
                else {
                    nextPage();
                    if(numPage +1 == tailleInstruc) {
                        btnSuite.setText("Jouer");
                    }
                }
            }
        });
        changePage();
    }

    @Override
    protected void onResume(){
        super.onResume();
        changePage();
    }

    private void creerInstuction() {
        //page1 les règles du jeu
        instruction[0] = "Règles:\n"
                +"\tLe but du jeu est de découvrir toutes les cases de la grille le plus rapidement que possible "
                +"tout en évitant les bombes. Déclencher une bombe fait automatiquement perdre la partie. ";

        //page2 les niveaux de difficultés
        instruction[1]= "Niveaux de difficultés:\n"
                +"\tTrois niveaux de difficultés sont disponibles et font varier le nombre de bombes sur la grille.\n"
                +"\nNiveau FACILE : 20 bombes sont présentes dans la grille.\n"
                +"\nNiveau NORMAL : 40 bombes sont présentes dans la grille.\n"
                +"\nNiveau DIFFICILE : 60 bombes sont présentes dans la grille.";


        //page3 les cases du jeu
        instruction[2] = "Cases\n"
                +"\tPour découvrir ce qui se cache derrière une case, il faut appuyer sur la case en question. "
                +"Si il s'agit d'une case vide, celle-ci dévoilera toutes les cases vides alentours. "
                +"Si il s'agit d'une bombe, la partie est perdu et toutes les autres bombes sont dévoilées. "
                +"Si une case vide est dévoilée et qu'elle possède des bombes parmis ses voisins, la case affichera "
                +"le nombre de bombe autour.";
        instruction[3] = "Cases\n"
                +"\tLes bombes sont représentées par un \"x\". "
                +"Appuyer longuement sur une case permet marquer la case d'un \"?\" et d'empêcher de dévoiler cette case. "
                +"Réappuyer longuement sur cette case permet d'enlever le \"?\".";
    }

    private void changePage() {
        textViewinstructions.setText(instruction[numPage]);
    }
    private void prevPage() {
        numPage--;
        changePage();
    }

    private void nextPage() {
        numPage++;
        changePage();
    }



}
