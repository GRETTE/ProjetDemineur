package demineur.projetdemineur.Controleur;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.ArrayList;

import demineur.projetdemineur.Modele.Case;
import demineur.projetdemineur.Vue.Demineur;

/**
 * Created by yanaël on 19/03/2017.
 */

public class CaseAdapter extends BaseAdapter {
    private Demineur demineur;

    public CaseAdapter(Demineur dem){
        this.demineur = dem;
    }

    @Override
    public int getCount() {
        return demineur.cases.size();
    }

    @Override
    public Object getItem(int position) {
        return demineur.cases.get(position);
    }

    @Override
    public long getItemId(int position) {
        return demineur.cases.get(position).getId();
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        Case c = demineur.cases.get(position);
        c.setLayoutParams(new GridView.LayoutParams(30, 30));
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Case c = (Case)v;
                if(!c.estMarque()&& !Demineur.PERDU) {
                    c.decouvrirCase();
                }
            }
        });

        c.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(!Demineur.PERDU) {
                    ((Case) v).marquer();
                }
                return true;
            }
        });
        return c;
    }
}
