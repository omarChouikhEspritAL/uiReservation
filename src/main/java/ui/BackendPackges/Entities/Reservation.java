package com.example.journeywiseguijaxafx.BackendPackges.Entities;

import java.util.Date;

public class Reservation {
    private int id;
    private Date dateDebut,dateFin;
    private int nombrePassages;
    private Offres offre;
   // private User user;

    public Reservation(int id, Date dateDebut, Date dateFin, int nombrePassages, Offres offre) {//,User user) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nombrePassages = nombrePassages;
        this.offre = offre;
      //  this.user = user;
    }

    public Reservation(Date dateDebut, Date dateFin, int nombrePassages, Offres offre){//, User user) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nombrePassages = nombrePassages;
        this.offre = offre;
       // this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getNombrePassages() {
        return nombrePassages;
    }

    public void setNombrePassages(int nombrePassages) {
        this.nombrePassages = nombrePassages;
    }

    public Offres getOffre() {
        return offre;
    }

    public void setOffre(Offres offre) {
        this.offre = offre;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", dateDebut='" + dateDebut + '\'' +
                ", dateFin='" + dateFin + '\'' +
                ", nombrePassages=" + nombrePassages +
                ", offre='" + offre + '\'' +
               // ", user=" + user +
                '}';
    }
}