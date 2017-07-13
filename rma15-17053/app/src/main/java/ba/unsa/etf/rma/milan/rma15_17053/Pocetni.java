package ba.unsa.etf.rma.milan.rma15_17053;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class Pocetni extends Activity implements FragmentLista.OnItemClick {

     ArrayList<Muzicar> muzicari = new ArrayList<Muzicar>();
    Boolean siriL = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetni);


        ListView lista = (ListView) findViewById(R.id.listView);
         ArrayList<String> unosi = new ArrayList<String>();
        ArrayList<String> unosi1 = new ArrayList<String>();
        ArrayList<String> unosi2 = new ArrayList<String>();
        ArrayList<String> unosi3 = new ArrayList<String>();
        final Muzicar m1 = new Muzicar("Amel Curic","rock","http://www.sport.ba","xfaktor");
        muzicari.add(m1);
        unosi.add("Pitaju me za tebe");
        unosi.add("Te zime");
        unosi.add("Stara ljubav");
        unosi.add("Neizdrzivo");
        unosi.add("It's my life");
        m1.setTop5(unosi);
        Muzicar m2 = new Muzicar("Severina ex.Vuckovic","folk","http://www.rezultati.com","tarcinski x faktor");
        muzicari.add(m2);
        unosi1.add("Uno momento");
        unosi1.add("Tarapana");
        unosi1.add("Italiana");
        unosi1.add("Sta je svit");
        unosi1.add("Gas gas");
        m2.setTop5(unosi1);
        Muzicar m3 =new Muzicar("Milan Zuza","klasik","http://www.flashscore.com","America supertalent");
        muzicari.add(m3);
        unosi2.add("Prva Milanova simfonija");
        unosi2.add("Etf balada");
        unosi2.add("IM2 song");
        unosi2.add("Osma simfonija");
        unosi2.add("Poem");
        m3.setTop5(unosi2);
        Muzicar m4 = new Muzicar("Bruno Mars","folk","http://www.livescore.com","songwriter show");
        muzicari.add(m4);
        unosi3.add("Grenade");
        unosi3.add("Uptown funk");
        unosi3.add("Just the way you are");
        unosi3.add("Valerie");
        unosi3.add("Lazy song");
        m4.setTop5(unosi3);
        // ListaMuzicara lm=new ListaMuzicara();
       // lm.muzicari=muzicari;


        //siriL je privatni atribut klase Pocetni koji je tipa Boolean
//ovu variablu ćemo koristiti da znamo o kojem layoutu se radi
// ako je siriL true tada se radi o širem layoutu (dva fragmenta)
// ako je siriL false tada se radi o početnom layoutu (jedan fragment)

        FragmentManager fm = getFragmentManager(); //dohvatanje FragmentManager-a
        FrameLayout ldetalji = (FrameLayout) findViewById(R.id.detalji_muzicar);
        if (ldetalji != null) {//slucaj layouta za široke ekrane
            siriL = true;
            FragmentDetalji fd;
            fd = (FragmentDetalji) fm.findFragmentById(R.id.detalji_muzicar);
            //provjerimo da li je fragment detalji već kreiran
            if (fd == null) {
                //kreiramo novi fragment FragmentDetalji ukoliko već nije kreiran
                fd = new FragmentDetalji();
                Bundle argumenti = new Bundle();
//unosi su ranije incializirana lista muzičara
//na način kako ste radili na projektnom zadatku 1
              //z4  argumenti.putParcelableArrayList("Alista", muzicari);
              //z4  fd.setArguments(argumenti);
                fm.beginTransaction().replace(R.id.detalji_muzicar, fd).commit();
            }
        }
//Dodjeljivanje fragmenta FragmentLista
        FragmentLista fl = (FragmentLista) fm.findFragmentByTag("Lista");
//provjerimo da li je već kreiran navedeni fragment
        if (fl == null) {
            //ukoliko nije, kreiramo
            fl = new FragmentLista();
            Bundle argumenti = new Bundle();
//unosi su ranije incializirana lista muzičara
//na način kako ste radili na projektnom zadatku 1
            argumenti.putParcelableArrayList("Alista", muzicari);
            fl.setArguments(argumenti);

            fm.beginTransaction().replace(R.id.muzicari_lista, fl, "Lista").commit();
        } else {
            //slučaj kada mjenjamo orjentaciju uređaja
            //iz portrait (uspravna) u landscape (vodoravna)
            //a u aktivnosti je bio otvoren fragment FragmentDetalji
            //tada je potrebno skinuti FragmentDetalji sa steka
            //kako ne bi bio dodan na mjesto fragmenta FragmentLista
            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }


    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onItemClicked(int pos){
        //Priprema novog fragmenta FragmentDetalji
        Bundle arguments = new Bundle();
        ListaMuzicara lm=new ListaMuzicara();
        muzicari=lm.muzicari;
        arguments.putParcelable("muzicar", muzicari.get(pos));
        FragmentDetalji fd = new FragmentDetalji();
        fd.setArguments(arguments);
        if (siriL) {

            //Slučaj za ekrane sa širom dijagonalom
            getFragmentManager().beginTransaction().replace(R.id.detalji_muzicar, fd).commit();
        } else {

            //Slučaj za ekrane sa početno zadanom širinom
            getFragmentManager().beginTransaction().replace(R.id.muzicari_lista, fd).addToBackStack(null).commit();
            //Primjetite liniju .addToBackStack(null)
        }
    }

}