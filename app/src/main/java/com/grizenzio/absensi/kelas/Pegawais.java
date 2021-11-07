package com.grizenzio.absensi.kelas;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Pegawais {

    @SerializedName("absensi")
    private ArrayList<Pegawai> pegawaiArrayList;

    public Pegawais() {

    }

    public ArrayList<Pegawai> getPegawaiArrayList() {
        return pegawaiArrayList;
    }

    public void setPegawaiArrayList(ArrayList<Pegawai> pegawaiArrayList) {
        this.pegawaiArrayList = pegawaiArrayList;
    }

}
