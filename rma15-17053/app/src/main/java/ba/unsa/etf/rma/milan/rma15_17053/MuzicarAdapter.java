package ba.unsa.etf.rma.milan.rma15_17053;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Milan on 17.3.2016.
 */
public class MuzicarAdapter  extends ArrayAdapter<Muzicar> {

    private static class MuzicarHolder {
        public TextView naziv_muzicara;
        public TextView zanr_muzicara;
        public ImageView slika_z;
    }

    List<Muzicar> listaMuzicara;
    int resource;
    public MuzicarAdapter(Context context, int _resource, List<Muzicar> items) {
        super(context, _resource, items);
        resource = _resource;
        this.listaMuzicara = items;
    }

//resource je id layout-a list item-a

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
// Kreiranje i inflate-anje view klase
        View newView = convertView;;
        Muzicar m = listaMuzicara.get(position);
        MuzicarHolder holder = new MuzicarHolder();

        if (convertView == null) {
// Ukoliko je ovo prvi put da se pristupa klasi convertView, odnosno nije upadate
// Potrebno je kreirati novi objekat i inflate-at ga
            newView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater li;
            li = (LayoutInflater)getContext().getSystemService(inflater);
            newView = li.inflate(R.layout.muzicar, null);
            newView.setTag(m);

            TextView tv = (TextView) newView.findViewById(R.id.ImePrezime);
            TextView genreView = (TextView) newView.findViewById(R.id.zanr);
            ImageView img = (ImageView) newView.findViewById(R.id.icon);

            holder.naziv_muzicara = tv;
            holder.zanr_muzicara = genreView;
            holder.slika_z = img;

            newView.setTag(holder);
        }
        else {
// Ukoliko je update potrebno je samo izmjenit vrijednosti polja
            holder = (MuzicarHolder) newView.getTag();
        }
        System.out.println("Position [" + position + "]");
        Muzicar p = listaMuzicara.get(position);
        holder.naziv_muzicara.setText(p.ime_prezime);
        holder.zanr_muzicara.setText("" + p.zanr);

        holder.slika_z.setImageResource(p.DajSliku());

        TextView tv = (TextView) newView.findViewById(R.id.ImePrezime);
        TextView genreView = (TextView) newView.findViewById(R.id.zanr);
        ImageView img = (ImageView) newView.findViewById(R.id.icon);

        tv.setText(m.ime_prezime);
        genreView.setText("" + m.zanr);
        img.setImageResource(m.DajSliku());
        return newView;
    }
}

