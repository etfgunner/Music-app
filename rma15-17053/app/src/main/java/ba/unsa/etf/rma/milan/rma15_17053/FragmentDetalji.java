package ba.unsa.etf.rma.milan.rma15_17053;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Milan on 4/18/2016.
 */
public class FragmentDetalji extends Fragment {
    private Muzicar muzicar;
    private ArrayList<Muzicar> muzicari;
    boolean prvi=true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View iv= inflater.inflate(R.layout.detalji_fragment, container, false);
        if(getArguments()!=null&&getArguments().containsKey("muzicar")){
            muzicar=getArguments().getParcelable("muzicar");
          /*  if(getArguments().containsKey("Alista")) {
//Dohvatamo proslijeđeni niz muzicara iz argumenata fragmenta
                Toast.makeText(getContext(), "wwww", Toast.LENGTH_SHORT).show();
                muzicari = getArguments().getParcelableArrayList("Alista");
            }
            */
            ListaMuzicara lm=new ListaMuzicara();
            muzicari=lm.muzicari;
            final TextView ime = (TextView) iv.findViewById(R.id.ime);
            final TextView web= (TextView) iv.findViewById(R.id.WEB_STR);
            final TextView zanr= (TextView) iv.findViewById(R.id.ZANR);
            final TextView bio= (TextView) iv.findViewById(R.id._BIO);
           // final ListView toppjesme = (ListView) iv.findViewById(R.id.listView3);
            final Button shareButton =(Button) iv.findViewById(R.id.share);
            final Button prikazi=(Button) iv.findViewById(R.id.prikazDugme);

            //Postavljanje i ostalih vrijednosti na isti način
            ime.setText(muzicar.DajImePrezime());
            web.setText(muzicar.DajWeb());
            bio.setText(muzicar.DajBio());
            zanr.setText(muzicar.DajZanr());

           // final ArrayAdapter adapter = new ArrayAdapter (getActivity(), android.R.layout.simple_list_item_1, muzicar.dajTop5());

           // toppjesme.setAdapter(adapter);
            web.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(web.getText().toString()));
                    startActivity(i);
                }
            });
           /* toppjesme.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String pjevac = ime.getText().toString();
                    String pjesma = parent.getItemAtPosition(position).toString();
                    String typein = pjesma + ' ' + pjevac;
                    Intent intent = new Intent(Intent.ACTION_SEARCH);
                    intent.setPackage("com.google.android.youtube");
                    intent.putExtra("query", typein);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
            */
           /*  ArrayList<String> vratiMuzicare()
             {
                    ArrayList<String> x;
                String trazi= muzicar.DajZanr();
                 for(int i=0;i<muzicari.size();i++)
                 {
                     if(trazi==muzicari.get(i).DajZanr())
                         x.add(muzicari.get(i).DajImePrezime());
                 }
                 return  x;
             }
             */
            prikazi.setOnClickListener(new View.OnClickListener()
            {
                public  void onClick(View view)
                {
                    android.app.FragmentManager fm = getFragmentManager();
                    android.app.FragmentTransaction ft=fm.beginTransaction();
                    if(prvi)
                    {

                        ArrayList<String> top1=muzicar.dajTop5();
                        Bundle bundle = new Bundle();
                        String p1 = top1.get(0);
                        String p2 = top1.get(1);
                        String p3 = top1.get(2);
                        String p4 = top1.get(3);
                        String p5 = top1.get(4);
                        String p6=muzicar.DajImePrezime();
                        bundle.putString("p1", p1 );
                        bundle.putString("p2", p2 );
                        bundle.putString("p3", p3 );
                        bundle.putString("p4", p4 );
                        bundle.putString("p5", p5 );
                        bundle.putString("p6",p6);
                        FragmentTop5Pjesama fj=new FragmentTop5Pjesama();

                        fj.setArguments(bundle);
                        ft.replace(R.id.fragment,fj);
                        //  ft.addToBackStack(null);
                        ft.commit();
                        prvi=false;
                        prikazi.setText("Prikazi albume");
                    }
                    else
                    {
                        Bundle bundle = new Bundle();
                        String p1 = muzicar.DajImePrezime();
                        bundle.putParcelable("p1",muzicar);
                        MuzicarAlbumi fj=new MuzicarAlbumi();
                        fj.setArguments(bundle);
                        ft.replace(R.id.fragment,fj);
                        //  ft.addToBackStack(null);
                        ft.commit();

                        /*

                        ArrayList<String> petMuzicara=new ArrayList<String>();
                        String trazi= muzicar.DajZanr();
                        int i=0;
                        for( i=0;i<muzicari.size();i++)
                        {

                           if(trazi.equals(muzicari.get(i).DajZanr()))
                           {
                               petMuzicara.add(muzicari.get(i).DajImePrezime());
                           }
                        }


                      if(petMuzicara.size()<5)
                        {
                                for( i=petMuzicara.size();i<5;i++)
                                {
                                    petMuzicara.add("-");
                                }
                        }
                        Bundle bundle = new Bundle();
                        String p1 = petMuzicara.get(0);
                        String p2 = petMuzicara.get(1);
                        String p3 = petMuzicara.get(2);
                        String p4 = petMuzicara.get(3);
                        String p5 = petMuzicara.get(4);

                        bundle.putString("p1", p1 );
                        bundle.putString("p2", p2 );
                        bundle.putString("p3", p3 );
                        bundle.putString("p4", p4 );
                        bundle.putString("p5", p5 );
                        Fragment5Muzicara fj=new Fragment5Muzicara();

                        fj.setArguments(bundle);
                         ft.replace(R.id.fragment,fj);
                        //  ft.addToBackStack(null);
                        ft.commit();
                        */
                        prvi=true;
                        prikazi.setText("Prikazi Top5 Pjesama");

                    }
                }
            });
            shareButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
/*
                   android.app.FragmentManager fm = getFragmentManager();
                    android.app.FragmentTransaction ft=fm.beginTransaction();

                    ArrayList<String> top1=muzicar.dajTop5();
                    Bundle bundle = new Bundle();
                    String p1 = top1.get(0);
                    String p2 = top1.get(1);
                    String p3 = top1.get(2);
                    String p4 = top1.get(3);
                    String p5 = top1.get(4);

                    bundle.putString("p1", p1 );
                    bundle.putString("p2", p2 );
                    bundle.putString("p3", p3 );
                    bundle.putString("p4", p4 );
                    bundle.putString("p5", p5 );
                    FragmentTop5Pjesama fj=new FragmentTop5Pjesama();

                    fj.setArguments(bundle);
                    ft.replace(R.id.fragment,fj);
                    //  ft.addToBackStack(null);
                    ft.commit();


*/

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra(Intent.EXTRA_TEXT, bio.getText().toString());
                    startActivity(intent);
                }
            });
        }
        return iv;
    }

}