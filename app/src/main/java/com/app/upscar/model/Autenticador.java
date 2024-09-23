package com.app.upscar.model;


import com.google.firebase.auth.FirebaseAuth;

public class Autenticador {

    private static FirebaseAuth firebaseAuth;

    public static FirebaseAuth FirebaseAutenticar(){
        if(firebaseAuth == null){
            firebaseAuth = FirebaseAuth.getInstance();
        }

        return firebaseAuth;
    }
}
