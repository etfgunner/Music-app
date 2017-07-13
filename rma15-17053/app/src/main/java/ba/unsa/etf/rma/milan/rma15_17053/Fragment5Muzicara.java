package ba.unsa.etf.rma.milan.rma15_17053;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment5Muzicara extends Fragment {


    public Fragment5Muzicara() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View iv= inflater.inflate(R.layout.fragment_fragment_top5_pjesama, container, false);
        Bundle bundle = this.getArguments();
        String p1 = bundle.getString("p1");
        String p2 = bundle.getString("p2");
        String p3 = bundle.getString("p3");
        String p4 = bundle.getString("p4");
        String p5 = bundle.getString("p5");
        ArrayList<String> pjesme=new ArrayList<String>();
        pjesme.add(p1);
        pjesme.add(p2);
        pjesme.add(p3);
        pjesme.add(p4);
        pjesme.add(p5);
        final ListView toppjesme = (ListView) iv.findViewById(R.id.listView3);
        final ArrayAdapter adapter= new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,pjesme);
        toppjesme.setAdapter(adapter);

        // return inflater.inflate(R.layout.fragment_fragment_top5_pjesama, container, false);
        return iv;
    }

}
