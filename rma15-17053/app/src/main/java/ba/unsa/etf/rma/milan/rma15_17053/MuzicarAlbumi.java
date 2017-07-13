package ba.unsa.etf.rma.milan.rma15_17053;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MuzicarAlbumi extends Fragment  {

    private ListView lv;
    private ArrayList<String> albumi;
    public MuzicarAlbumi() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vi= inflater.inflate(R.layout.fragment_muzicar_albumi, container, false);
        lv = (ListView)vi.findViewById(R.id.listView2);
        Bundle bundle = this.getArguments();
        Muzicar p1 = bundle.getParcelable("p1");
        ArrayList<String> albumi=new ArrayList<>();
        albumi=p1.albumi;
        final ArrayAdapter adapter= new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,albumi);
        lv.setAdapter(adapter);



        return vi;
    }


}
