package com.example.nurcahyo.batikapa;

import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class LihatDetail extends AppCompatActivity {

    Database db = null;
    private TextView namaBatik;
    private ImageView gambarBatik;
    private TextView deskripsiBatik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_detail);
        Bundle terima = getIntent().getExtras();
        db = new Database(this);
        int idbatik = terima.getInt("id");
        getInfoBatik(idbatik);
    }

    public void getInfoBatik (int id){
        Cursor cursor = db.getBatikInfo(id);
        cursor.moveToFirst();
        String nama = "";
        String asal = "";
        String deskripsi ="";

        while (!cursor.isAfterLast()) {
            id = cursor.getInt(0);
            nama = cursor.getString(1);
            asal = cursor.getString(2);
            deskripsi = cursor.getString(3);
            cursor.moveToNext();
        }

        namaBatik = (TextView) findViewById(R.id.txtNama);
        namaBatik.setText("Batik "+nama+"\n("+asal+")");

        String uri = "@drawable/batik"+id;
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        gambarBatik = (ImageView)findViewById(R.id.imgBatik);
        Drawable dr = getResources().getDrawable(imageResource);
        gambarBatik.setImageDrawable(dr);

        deskripsiBatik = (TextView) findViewById(R.id.txtDeskripsi);
        deskripsiBatik.setText(deskripsi);
    }
}
