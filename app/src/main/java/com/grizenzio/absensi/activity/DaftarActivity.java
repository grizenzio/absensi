package com.grizenzio.absensi.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.grizenzio.absensi.R;

import com.grizenzio.absensi.adapter.DaftarAdapter;
import com.grizenzio.absensi.kelas.Database;
import com.grizenzio.absensi.kelas.Pegawai;

import android.database.Cursor;

import java.util.ArrayList;

public class DaftarActivity extends AppCompatActivity {

    private Database db;

    private RecyclerView recyclerView;

    private ArrayList<Pegawai> pegawaiArrayList;

    private DaftarAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_daftar);

        setTitle("Daftar Hadir");

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Tunggu sebentar...");
        progressDialog.show();

        db = new Database(this);

        recyclerView = findViewById(R.id.rv_daftar);

        pegawaiArrayList = new ArrayList<>();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);

        recyclerView.setNestedScrollingEnabled(false);

        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        mAdapter = new DaftarAdapter(this, pegawaiArrayList);

        tampilData();

        progressDialog.dismiss();

    }

    private void tampilData() {

        //Untuk clear data list
        pegawaiArrayList.clear();

        Cursor c = db.TampilDataAbsen();

        while (c.moveToNext())
        {
            String id = c.getString(0);

            String nidn = c.getString(1);

            String nama = c.getString(2);

            String tanggal = c.getString(3);

            String jam = c.getString(4);

            String status = c.getString(5);

            String telat = c.getString(6);

            Pegawai p = new Pegawai(id, nidn, nama, tanggal, jam, status, telat);

            pegawaiArrayList.add(p);
        }

        if(!(pegawaiArrayList.size() < 1))
        {
            recyclerView.setAdapter(mAdapter);
        }


    }
}