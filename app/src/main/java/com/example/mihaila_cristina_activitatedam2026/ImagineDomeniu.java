package com.example.mihaila_cristina_activitatedam2026;

import android.graphics.Bitmap;

//Un element din lista: poza, textul afisat si pagina web asociata
public class ImagineDomeniu {
    private String textAfisat;
    private Bitmap imagine;
    private String link;

    public ImagineDomeniu(String textAfisat, Bitmap imagine, String link) {
        this.textAfisat = textAfisat;
        this.imagine = imagine;
        this.link = link;
    }

    public ImagineDomeniu() {
    }

    public String getTextAfisat() {
        return textAfisat;
    }

    public Bitmap getImagine() {
        return imagine;
    }

    public String getLink() {
        return link;
    }
}
