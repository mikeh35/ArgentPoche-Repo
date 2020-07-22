package com.mikeh35.argentpochev1;

import androidx.annotation.NonNull;

public class Prenoms {

    private String name;

    public Prenoms(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @NonNull
    @Override
    public String toString() {
        return name ;
    }

}
