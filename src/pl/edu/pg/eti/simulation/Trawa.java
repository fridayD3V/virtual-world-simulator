package pl.edu.pg.eti.simulation;

import java.util.Random;

public class Trawa extends Roslina{
    public Trawa(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
        this.sila = 0;
        this.inicjatywa = 0;
        this.gatunek = 't';
        this.czasZycia = 0;
    }
    public Trawa(int polozenieX, int polozenieY, int sila, int inicjatywa, char gatunek, int czasZycia, Swiat swiat) {
        super(polozenieX, polozenieY, sila, inicjatywa, gatunek, czasZycia, swiat);
    }

    @Override
    public void DodajDziecko(int x, int y) {
        Random rand = new Random();
        int szansa = rand.nextInt(3);
        if (szansa == 1) {
            System.out.println(gatunek + "(" + polozenieX + ", " + polozenieY + ") rozprzestrzenil(a) sie na (" + x + ", " + y + ").");

            swiat.DodacOrganizm(new Trawa(x, y, swiat));
        }
    }

    @Override
    public int czyOdbilAtak(Organizm atakujacy) {
        return 0;
    }
}