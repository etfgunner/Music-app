package ba.unsa.etf.rma.milan.rma15_17053;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetaljniAktiviti extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muzicar);


        final TextView imetv = (TextView) findViewById(R.id.ime);
        imetv.setText(getIntent().getStringExtra("imeAutoraI"));

        TextView zanrtv = (TextView) findViewById(R.id.ZANR);
        zanrtv.setText(getIntent().getStringExtra("zanrI"));

        final TextView biotv = (TextView) findViewById(R.id._BIO);
        biotv.setText(getIntent().getStringExtra("bioI"));

        TextView webtv = (TextView) findViewById(R.id.WEB_STR);
        webtv.setText(getIntent().getStringExtra("webI"));

        ListView pjesmelv = (ListView) findViewById(R.id.listView3);
        final ArrayList<String> songs = getIntent().getStringArrayListExtra("listI");
        final ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songs);
        pjesmelv.setAdapter(adapter);

        Button shareButton = (Button) findViewById(R.id.share);

        webtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(getIntent().getStringExtra("webI")));
                startActivity(i);
            }
        });


        pjesmelv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pjevac = imetv.getText().toString();
                String pjesma = parent.getItemAtPosition(position).toString();
                String typein = pjesma + ' ' + pjevac;
                Intent intent = new Intent(Intent.ACTION_SEARCH);
                intent.setPackage("com.google.android.youtube");
                intent.putExtra("query", typein);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra(Intent.EXTRA_TEXT, biotv.getText().toString());
                startActivity(intent);
            }
        });


    }
}
