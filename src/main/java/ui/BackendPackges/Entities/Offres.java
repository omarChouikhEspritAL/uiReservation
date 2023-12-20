package com.example.journeywiseguijaxafx.BackendPackges.Entities;



public class Offres {
    private int id_offre;
    public String pays;
    public String cite;
    public String lieu;
    public String date;
    public float prix;

    public Offres(){}


    public Offres(int id_offre, String pays, String cite, String lieu, String date, float prix) {
        this.id_offre= id_offre;
        this.pays = pays;
        this.cite = cite;
        this.lieu = lieu;
        this.date = date;
        this.prix = prix;
    }
    public int getId_offre() {
        return id_offre;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getCite() {
        return cite;
    }

    public void setCite(String cite) {
        this.cite = cite;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(  String  date) {
        this.date = date;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "offre{" +
                "id_offre='" + id_offre + '\'' +
                "pays='" + pays + '\'' +
                ", cite='" + cite + '\'' +
                ", lieu='" + lieu + '\'' +
                ", date=" + date +
                ", prix=" + prix +
                '}';
    }
}
