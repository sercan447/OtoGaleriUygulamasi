package sercandevops.com.otogaleriuygulamasi.Models;

public class MesajModel {

    private String from,mesaj,to;


    public MesajModel(){

    }

    public MesajModel(String from, String mesaj, String to) {
        this.from = from;
        this.mesaj = mesaj;
        this.to = to;
    }


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
