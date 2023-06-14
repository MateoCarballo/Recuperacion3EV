package org.example;

import org.example.Dialog.Dialogg;

import java.io.IOException;

public class Main {
    public static void main(String[] args){
        Dialogg dialogo = new Dialogg();
        try {
            dialogo.menu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}