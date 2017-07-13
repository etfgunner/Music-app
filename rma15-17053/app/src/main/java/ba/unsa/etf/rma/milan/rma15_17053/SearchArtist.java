package ba.unsa.etf.rma.milan.rma15_17053;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Milan on 5/6/2016.
 */
public class SearchArtist extends AsyncTask<String, Integer, Void> {
    public interface  OnMuzicarSearchDone
    {
        public void  onDone(ArrayList<Muzicar> rez);
    }
    ArrayList<Muzicar> rez;
    private OnMuzicarSearchDone pozivatelj;
    public SearchArtist(OnMuzicarSearchDone p)
    {
        pozivatelj=p;
    }
    @Override
    protected Void doInBackground(String... params)
    {
        String query = null;
        try {
            query = URLEncoder.encode(params[0], "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url1  = "https://api.spotify.com/v1/search?q=" + query + "&type=artist";
        try {
            URL url = new URL(url1);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String rezultat = convertStreamToString(in);
            JSONObject jo = new JSONObject(rezultat);
            JSONObject artists = jo.getJSONObject("artists");
            JSONArray items = artists.getJSONArray("items");
            ArrayList<Muzicar> muzicari=new ArrayList<Muzicar>();

            for (int i = 0; i < items.length(); i++) {
                JSONObject artist = items.getJSONObject(i);
                String name = artist.getString("name");
                String artist_ID = artist.getString("id");
                String webStr="https://open.spotify.com/artist/"+artist_ID;
               String zanrovi=artist.get("genres").toString();
                String p=zanrovi.substring(0,zanrovi.length()-1);
                if(p.length()>0)
                    p=p.substring(1,p.length());
                Muzicar m=new Muzicar(name,p,webStr,"");
                ArrayList<String> top5=new ArrayList<String>();
                //toppjesme
                String urlZaTopPjesme = "https://api.spotify.com/v1/artists/" + artist_ID + "/top-tracks?country=SE";
                URL urlPjesme = new URL(urlZaTopPjesme);
                HttpURLConnection urlConnectionPjesme = (HttpURLConnection) urlPjesme.openConnection();
                InputStream inPjesme = new BufferedInputStream(urlConnectionPjesme.getInputStream());
                String rezultatPjesme = convertStreamToString(inPjesme);
                JSONObject joPjesme = new JSONObject(rezultatPjesme);
                JSONArray itemsPjesme = joPjesme.getJSONArray("tracks");
                for (int j = 0; j < itemsPjesme.length(); j++)
                {
                    JSONObject album = itemsPjesme.getJSONObject(j);
                    top5.add(album.getString("name"));
                }
                m.setTop5(top5);
                //albumi
                String urlZaAlbume= "https://api.spotify.com/v1/artists/" + artist_ID + "/albums";
                URL urlAlbumi=new URL(urlZaAlbume);
                HttpURLConnection urlConnectionAlbumi=(HttpURLConnection) urlAlbumi.openConnection();
                InputStream inAlbumi=new BufferedInputStream(urlConnectionAlbumi.getInputStream());
                String rezultatAlbuma=convertStreamToString(inAlbumi);
                JSONObject joAlbumi=new JSONObject(rezultatAlbuma);
                JSONArray itemsAlbumi=joAlbumi.getJSONArray("items");
                ArrayList<String> albumi=new ArrayList<String>();
                for (int k=0;k<itemsAlbumi.length();k++)
                {
                    JSONObject album=itemsAlbumi.getJSONObject(k);
                    albumi.add(album.getString("name"));
                }
                m.albumi=albumi;
                muzicari.add(m);
               // rez=new Muzicar(name,"zanr","webpage","");
//Ovdje trebate dodati kreiranje objekta Muzičara i dodavanje u listu
//Ovo uradite na sličan način kako ste radili i kada ste hardkodirali podatke
// samo što sada koristite stvarne podatke
            }
            rez=muzicari;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }
    @Override
    protected  void onPostExecute(Void aVoid)
    {
        super.onPostExecute(aVoid);
        pozivatelj.onDone(rez);
    }
    public String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
        } finally {
            try {
                is.close();
            } catch (IOException e) {
            }
        }
        return sb.toString();
    }
}
