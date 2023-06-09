package org.example;

import org.example.Dialog.Dialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args){
        Dialog dialogo = new Dialog();
        try {
            dialogo.menu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}