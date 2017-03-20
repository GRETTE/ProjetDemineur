package demineur.projetdemineur.Modele;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import demineur.projetdemineur.Vue.Demineur;

/**
 * Created by yanaël on 19/03/2017.
 */

public class Case extends Button {
    private Demineur demineur;
    //est marqué d'un "?"
    private boolean estMarque = false;
    private boolean estBombe= false;
    private boolean estDevoile = false;
    private ArrayList<Case> voisins;


    public Case(Context context) {
        super(context);
        demineur = (Demineur) context;
        voisins = new ArrayList<Case>();
        this.setTextSize(10);
        //pour la couleur du "?"
        this.setTextColor(Color.RED);
    }

    public void decouvrirCase() {
        if(!estDevoile) {
            estDevoile = true;
            this.setEnabled(false);
            if(estBombe) {
                this.setText("X");
                this.setTextColor(Color.RED);
                demineur.perdu();
            }
            else {
                int nbBombesVoisins = getNBBombes();
                switch (nbBombesVoisins) {
                    case 0:
                        for(Case c:voisins){
                            c.decouvrirCase();
                        }
                        break;
                    case 1:
                        this.setText(nbBombesVoisins+"");
                        this.setTextColor(Color.CYAN);
                        break;
                    case 2:
                        this.setText(nbBombesVoisins+"");
                        this.setTextColor(Color.GREEN);
                        break;
                    case 3:
                        this.setText(nbBombesVoisins+"");
                        this.setTextColor(Color.RED);
                        break;
                    case 4:
                        this.setText(nbBombesVoisins+"");
                        this.setTextColor(Color.YELLOW);
                        break;
                    case 5:
                        this.setText(nbBombesVoisins+"");
                        this.setTextColor(Color.BLUE);
                        break;
                    case 6:
                        this.setText(nbBombesVoisins+"");
                        this.setTextColor(Color.GRAY);
                        break;
                    case 7:
                        this.setText(nbBombesVoisins+"");
                        this.setTextColor(Color.DKGRAY);
                        break;
                    case 8:
                        this.setText(nbBombesVoisins+"");
                        this.setTextColor(Color.BLACK);
                        break;
                }
            }
        }
    }

    public void setBombe() {
        estBombe = true;
    }

    public boolean estBombe() {
        return estBombe;
    }



    public void ajouterVoisin(Case c) {
        voisins.add(c);
    }

    public void Info() {
        Toast.makeText(demineur,"estMarque "+estMarque(),Toast.LENGTH_SHORT).show();
        Toast.makeText(demineur,"estBombe "+ estBombe(),Toast.LENGTH_SHORT).show();
        Toast.makeText(demineur,"estDevoile "+estDevoile,Toast.LENGTH_SHORT).show();
        Toast.makeText(demineur,"estMarque "+estMarque(),Toast.LENGTH_SHORT).show();
        Toast.makeText(demineur,"nbVoisin "+voisins.size(),Toast.LENGTH_SHORT).show();
        Toast.makeText(demineur,"nbBombesVoisins "+getNBBombes(),Toast.LENGTH_SHORT).show();
     /*private boolean estMarque = false;
    private boolean estBombe= false;
    private boolean estDevoile = false;
    private ArrayList<Case> voisins;
    private int nbBombesVoisins=0;*/

    }

    public boolean estMarque() {
        return estMarque;
    }

    public void marquer() {
        if(!estDevoile) {
            estMarque = !estMarque;
            if (estMarque) {
                this.setText("?");
            }
            else {
                this.setText("");
            }
        }
    }

    private int getNBBombes() {
        int returnNB =0;
        for(Case c:voisins) {
            if(c.estBombe()) {
                returnNB++;
            }
        }
        return returnNB;
    }
}
