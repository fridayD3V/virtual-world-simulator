package pl.edu.pg.eti.simulation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int rX = 0;
        int rY = 0;

        while (rX <= 0 || rY <= 0) {
            System.out.println("Rozmiar swiata x:");
            rX = input.nextInt();
            System.out.println("Rozmiar swiata y:");
            rY = input.nextInt();
        }

        Swiat swiat = new Swiat(rX, rY);
        swiat.Start();
    }
}