package pl.edu.pg.eti.simulation;

import java.util.Random;

public class Jagody extends Roslina{
    public Jagody(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
        this.sila = 99;
        this.inicjatywa = 0;
        this.gatunek = 'j';
        this.czasZycia = 0;
    }
    public Jagody(int polozenieX, int polozenieY, int sila, int inicjatywa, char gatunek, int czasZycia, Swiat swiat) {
        super(polozenieX, polozenieY, sila, inicjatywa, gatunek, czasZycia, swiat);
    }

    @Override
    public void DodajDziecko(int x, int y) {
        Random rand = new Random();
        int szansa = rand.nextInt(6);
        if (szansa == 1) {
            System.out.println(gatunek + "(" + polozenieX + ", " + polozenieY + ") rozprzestrzenil(a) sie na (" + x + ", " + y + ").");

            swiat.DodacOrganizm(new Jagody(x, y, swiat));
        }
    }

    @Override
    public void kolizja(Organizm organizm) {
        if (organizm.GetGatunek() == 'c' && organizm.czyOdbilAtak(this) == 1) {}
        else {
            System.out.println(organizm.GetGatunek() + "(" + organizm.GetPolozenieX() + ", " + organizm.GetPolozenieY() + ") zjadl " + gatunek + "(" + polozenieX + ", " + polozenieY + ").");

            swiat.ZabicOrganizm(organizm.GetPolozenieX(), organizm.GetPolozenieY());
            swiat.ZabicOrganizm(polozenieX, polozenieY);
        }
    }

    @Override
    public int czyOdbilAtak(Organizm atakujacy) {
        return 0;
    }
}