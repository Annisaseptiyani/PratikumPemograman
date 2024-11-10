package model;

public class Biodata {
    private String nama,jeniskelamin,nomorhp;
    private Boolean wna = false;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJeniskelamin() {
        return jeniskelamin;
    }

    public void setJeniskelamin(String jeniskelamin) {
        this.jeniskelamin = jeniskelamin;
    }

    public String getNomorhp() {
        return nomorhp;
    }

    public void setNomorhp(String nomorhp) {
        this.nomorhp = nomorhp;
    }

    public Boolean getWna() {
        return wna;
    }

    public void setWna(Boolean wna) {
        this.wna = wna;
    }
}
