package com.grizenzio.absensi.kelas;

public class Pegawai {

    private String id;
    private String nidn;
    private String nama;
    private String tanggal;
    private String jam;
    private String status;
    private String telat;

    public Pegawai(String id, String nidn, String nama, String tanggal, String jam, String status, String telat) {
        this.id = id;
        this.nidn = nidn;
        this.nama = nama;
        this.tanggal = tanggal;
        this.jam = jam;
        this.status = status;
        this.telat = telat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNidn() {
        return nidn;
    }

    public void setNidn(String nidn) {
        this.nidn = nidn;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTelat() {
        return telat;
    }

    public void setTelat(String telat) {
        this.telat = telat;
    }

}
