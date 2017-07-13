package ba.unsa.etf.rma.milan.rma15_17053;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Milan on 4/18/2016.
 */
public class FragmentLista extends Fragment implements  SearchArtist.OnMuzicarSearchDone{
    private ArrayList<Muzicar> muzicari;
    private Muzicar muzicar;
    private OnItemClick oic;
    private ListView lv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//Povezujemo layout fragmenta sa samim objektom fragmenta koji je kreiranje
// XML layouta –- prelazi --> View objekat
// rview će da sadrži View objekat fragmenta, koji predstavlja layout sadržaja fragmenta
        View rview =inflater.inflate(R.layout.lista_fragment, container, false);
//Ukoliko je kao argument prosljeđena vrijednost koja ima ključ Alista
// možemo dohvatiti niz i povezati ga na adapter
        if(getArguments().containsKey("Alista")){
//Dohvatamo proslijeđeni niz muzicara iz argumenata fragmenta
        //3    muzicari = getArguments().getParcelableArrayList("Alista");
            muzicari=new ArrayList<Muzicar>();
//Dohvatamo referencu na listview u fragmentu
//koristimo rview jer on sadrži sve ui elemente fragmenta
            lv = (ListView)rview.findViewById(R.id.listView);
//Kreiramo novi adapter koji sadrži niz muzičara

      //1    final  MuzicarAdapter aa = new MuzicarAdapter(getActivity(), R.layout.element_liste, muzicari);
//Povezujemo adapter na listview
         //2   lv.setAdapter(aa);
        }

        try {
//oic definišite kao privatni atribut klase FragmentLista
//u sljedećoj liniji dohvatamo referencu na roditeljsku aktivnost
//kako ona implementira interfejs OnItemClick moguće ju je castati u navedeni interfejs
            oic = (OnItemClick)getActivity();
        } catch (ClassCastException e) {
//u slučaju da se u roditeljskoj aktivnosti nije implementirao interfejs OnItemClick
//baca se izuzetak
            throw new ClassCastException(getActivity().toString() + "Treba implementirati OnItemClick");
        }
//ukoliko je aktivnos uspješno cast-ana u interfejs tada njoj prosljeđujemo event
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//poziva se implementirana metoda početne aktivnosti iz interfejsa OnItemClick
//kao parametar se prosljeđuje pozicija u ListView-u na koju je korisnik kliknuo
                oic.onItemClicked(position);
            }
        });
        final Button dugme = (Button) rview.findViewById(R.id.button);
        final EditText tekst = (EditText) rview.findViewById(R.id.textView);
        dugme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String upit=tekst.getText().toString();
                 new SearchArtist((SearchArtist.OnMuzicarSearchDone)FragmentLista.this).execute(upit);
            }
        });
        return rview;

    }

    public interface OnItemClick {
        public void onItemClicked(int pos);
    }
@Override
    public void onDone(ArrayList<Muzicar> m)
{
  //  muzicar=m;
    // String pe=muzicar.DajImePrezime();
   //  muzicari.add(muzicar);
    muzicari=m;
    ListaMuzicara lm=new ListaMuzicara();
    lm.muzicari=muzicari;
    MuzicarAdapter aa = new MuzicarAdapter(getActivity(), R.layout.element_liste, muzicari);
    lv.setAdapter(aa);
  //  Toast.makeText(getContext(),pe,Toast.LENGTH_SHORT).show();
    // muzicar.DajImePrezime()
    //final  TextView ime1=(TextView) View.this.findViewById(R.id.ime);
   // ime1.setText(muzicar.DajImePrezime());
}
}

