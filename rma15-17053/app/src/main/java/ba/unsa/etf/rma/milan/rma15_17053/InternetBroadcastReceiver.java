package ba.unsa.etf.rma.milan.rma15_17053;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by Milan on 31.3.2016.
 */
public class InternetBroadcastReceiver extends BroadcastReceiver {
    public InternetBroadcastReceiver() {
    }



    @Override
    public void onReceive(Context context, Intent intent) {


        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if(activeNetworkInfo != null && activeNetworkInfo.isConnected()){
            Toast.makeText(context, "Konekcija uspostavljena!", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "Konekcija prekinuta!", Toast.LENGTH_SHORT).show();
    }
}
