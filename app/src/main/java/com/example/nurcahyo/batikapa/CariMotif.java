package com.example.nurcahyo.batikapa;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Random;

public class CariMotif extends AppCompatActivity {

    Database db = null;
    ListView daftarBatik;

    int limit = 5;
    String[] namaBatik = new String[limit];
    int[] idBatik = new int[limit];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_motif);

        db = new Database(this);

        daftarBatik = (ListView) findViewById(R.id.listBatik);
        getListBatik();
        try {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, namaBatik);
            daftarBatik.setAdapter(adapter);
        }catch(Exception e){
            System.out.println(e);
        }

        daftarBatik.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), LihatDetail.class);
                Bundle kirim = new Bundle();
                kirim.putInt("id",idBatik[position]);
                intent.putExtras(kirim);
                startActivity(intent);
            }
        });
    }

    public void getListBatik(){
        Cursor cursor;
        Random rn = new Random();

        for (int i=0; i<limit; i++){
            int id = rn.nextInt(62)+1;
            cursor = db.getBatikInfo(id);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                idBatik[i] = cursor.getInt(0);
                namaBatik[i] = cursor.getString(1);
                cursor.moveToNext();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
