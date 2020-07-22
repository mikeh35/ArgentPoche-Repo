package com.mikeh35.argentpochev1;

import androidx.annotation.NonNull;

import java.util.Date;

public class MontantData {

    private String name;
    private int montant;
    private Date time;

    public MontantData(String name, int montant, Date time) {
        this.name = name;
        this.montant = montant;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public int getMontant() {
        return montant;
    }

    public Date getTime() {
        return time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @NonNull
    @Override
    public String toString() {
        return name + " à " + montant + " " + time;
    }
}
