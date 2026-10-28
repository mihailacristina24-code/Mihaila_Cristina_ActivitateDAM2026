package com.example.mihaila_cristina_activitatedam2026;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Obiect din realitate: Computer (contine literele M si C din Mihaila Cristina).
 */
public class Computer implements Parcelable {

    private String marca;
    private String procesor;
    private double pret;
    private String tip;          // Laptop / Desktop
    private boolean areGarantie;
    private float rating;        // 0 - 5
    private boolean areSsd;

    public Computer() {
    }

    public Computer(String marca, String procesor, double pret, String tip,
                    boolean areGarantie, float rating, boolean areSsd) {
        this.marca = marca;
        this.procesor = procesor;
        this.pret = pret;
        this.tip = tip;
        this.areGarantie = areGarantie;
        this.rating = rating;
        this.areSsd = areSsd;
    }

    protected Computer(Parcel in) {
        marca = in.readString();
        procesor = in.readString();
        pret = in.readDouble();
        tip = in.readString();
        areGarantie = in.readByte() != 0;
        rating = in.readFloat();
        areSsd = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(marca);
        dest.writeString(procesor);
        dest.writeDouble(pret);
        dest.writeString(tip);
        dest.writeByte((byte) (areGarantie ? 1 : 0));
        dest.writeFloat(rating);
        dest.writeByte((byte) (areSsd ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Computer> CREATOR = new Creator<Computer>() {
        @Override
        public Computer createFromParcel(Parcel in) {
            return new Computer(in);
        }

        @Override
        public Computer[] newArray(int size) {
            return new Computer[size];
        }
    };

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getProcesor() { return procesor; }
    public void setProcesor(String procesor) { this.procesor = procesor; }

    public double getPret() { return pret; }
    public void setPret(double pret) { this.pret = pret; }

    public String getTip() { return tip; }
    public void setTip(String tip) { this.tip = tip; }

    public boolean isAreGarantie() { return areGarantie; }
    public void setAreGarantie(boolean areGarantie) { this.areGarantie = areGarantie; }

    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }

    public boolean isAreSsd() { return areSsd; }
    public void setAreSsd(boolean areSsd) { this.areSsd = areSsd; }

    @Override
    public String toString() {
        return "Marca: " + marca
                + "\nProcesor: " + procesor
                + "\nPret: " + pret + " RON"
                + "\nTip: " + tip
                + "\nGarantie: " + (areGarantie ? "Da" : "Nu")
                + "\nRating: " + rating + " / 5"
                + "\nSSD: " + (areSsd ? "Da" : "Nu");
    }
}
