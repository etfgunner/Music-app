package ba.unsa.etf.rma.milan.rma15_17053;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Milan on 17.3.2016.
 */

public class Muzicar implements Parcelable {
 public String ime_prezime;
 public  String zanr;
 public String webStr;
 public  String bio;
 public ArrayList<String> top5 = new ArrayList<>();
public ArrayList<String> albumi=new ArrayList<>();
 public  Muzicar () {}
 public  Muzicar (String name, String genre, String web, String biografija)
 {
  ime_prezime = name; zanr = genre; webStr = web; bio = biografija;
 }

 protected Muzicar(Parcel in) {
  ime_prezime = in.readString();
  zanr = in.readString();
  webStr = in.readString();
  bio = in.readString();
  top5 = in.createStringArrayList();
 }

 public static final Creator<Muzicar> CREATOR = new Creator<Muzicar>() {
  @Override
  public Muzicar createFromParcel(Parcel in) {
   return new Muzicar(in);
  }

  @Override
  public Muzicar[] newArray(int size) {
   return new Muzicar[size];
  }
 };

 public void postaviImePrezime (String name) { ime_prezime = name; }
 public void postaviZanr (String genre) {zanr = genre; }
 public  void postaviWeb (String web) { webStr = web; }
 public void postavioBio (String biografija) {bio = biografija;}

 public String DajImePrezime () { return  ime_prezime; }
 public  String DajZanr () {return zanr;}
 public  String DajWeb () { return webStr; }
 public  String DajBio () { return bio; }
 public  ArrayList<String> dajTop5() { return top5; }


 public int DajSliku ()
 {
  if (zanr.contains("pop")) return R.drawable.pop;
  if (zanr.contains("rock")) return  R.drawable.rock;
  if(zanr.contains("rap"))return  R.drawable.klasik;
  if(zanr.contains("electro"))return  R.drawable.electro;
  if (zanr.contains("folk")) return  R.drawable.folk;
  else return R.drawable.slika1;
 }

 public void setTop5(ArrayList<String> pjesme){ top5 = pjesme; }

 @Override
 public int describeContents() {
  return 0;
 }

 @Override
 public void writeToParcel(Parcel dest, int flags) {
  dest.writeString(ime_prezime);
  dest.writeString(zanr);
  dest.writeString(webStr);
  dest.writeString(bio);
  dest.writeStringList(top5);
 }
}
