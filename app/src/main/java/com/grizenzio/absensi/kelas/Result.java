package com.grizenzio.absensi.kelas;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("error")
    private final Boolean error;
    @SerializedName("pesan")
    private final String pesan;

    public Result(Boolean error, String pesan) {
        this.error = error;
        this.pesan = pesan;
    }

    public Boolean getError() {
        return error;
    }

    public String getPesan() {
        return pesan;
    }
}
