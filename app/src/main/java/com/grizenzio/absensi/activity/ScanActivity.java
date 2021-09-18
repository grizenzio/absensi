package com.grizenzio.absensi.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.grizenzio.absensi.kelas.Database;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.UUID;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    //Inisialisasi ZXingScannerView
    private ZXingScannerView mScannerView;

    //Inisialisasi kelas database
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);

        //Inisialisasi kelas database
        db = new Database(this);

        //Star Kamera
        mScannerView.startCamera(1); // 0 Kamera Belakang dan 1 itu kemera depan
    }

    @Override
    public void onStart() {
        super.onStart();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera(1); // 0 Kamera Belakang dan 1 itu kemera depan
        doRequestPermission();
    }

    private void doRequestPermission() {
        int permissionCheckStorage = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionCheckStorage == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera(1); // 0 Kamera Belakang dan 1 itu kemera depan
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(com.google.zxing.Result rawResult) {

//        Log.v("TAG 1 ", rawResult.getText());
//        Log.v("TAG 2", rawResult.getBarcodeFormat().toString());

        //9498375sdf-fdd-dfgdfg987479-dfgdfg
        String uid = UUID.randomUUID().toString();

        String hasil = rawResult.getText(); //12345#NAMA

        StringTokenizer tokens = new StringTokenizer(hasil, "#"); //Untuk memecahkan sebelum pagar

        //Mengambil NOMOR INDUK
        String npak = tokens.nextToken(); //12345
        String nama = tokens.nextToken(); //NAMA

        //Menghasilkan tanggal dengan format 17-09-2021
        String tanggal = new SimpleDateFormat("d-MM-yyyy", Locale.getDefault()).format(new Date());

        //Menghasilkan tanggal dengan format 15:47:00
        String jam = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

        //Tujuan untuk menampilkan hasil Log
        Log.v("SIMPAN INI", uid+ npak+nama+tanggal+jam);

        //Data absensi akan di cek terlebih dahulu
        boolean cekSimpan = db.TambahData(uid, npak, nama, tanggal, jam, "Hadir", "Tidak");

        if (cekSimpan) {

            //untuk memunculkan notifikasi bawha simpan berhasil
            Toast.makeText(ScanActivity.this, "Absen berhasil." , Toast.LENGTH_LONG).show();

            onResume();

            //Prosedure simpan data di cloud
            //absen(uid,npak,tanggal,jam);


        } else {

            //untuk memunculkan notifikasi bawha gagal disimpan
            Toast.makeText(ScanActivity.this, "Anda sudah absen!", Toast.LENGTH_LONG).show();
            onResume();

        }

        onResume();

    }

    //Prosedure simpan data di cloud
//    private void absen(String uid, String nidn, String tanggal, String jam) {
//        //defining a progress dialog to show while signing up
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Tunggu sebentar...");
//        progressDialog.show();
//
//        //building retrofit object
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Apiurl.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        //Defining retrofit api service
//        Apiservice service = retrofit.create(Apiservice.class);
//
//        //defining the call
//        Call<Result> call = service.hadir(uid,nidn,tanggal,jam,"1");
//
//        //calling the api
//        call.enqueue(new Callback<Result>() {
//            @Override
//            public void onResponse(Call<Result> call, Response<Result> response) {
//                //hiding progress dialog
//                progressDialog.dismiss();
//
//                //displaying the message from the response as toast
//                Toast.makeText(getApplicationContext(), "Terima Kasih!", Toast.LENGTH_LONG).show();
//
//                onResume();
//
//            }
//
//            @Override
//            public void onFailure(Call<Result> call, Throwable t) {
//                progressDialog.dismiss();
//                Toast.makeText(getApplicationContext(), "Koneksi internet bermasalah.", Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }
}