package pl.edu.pg.eti.simulation;

import java.util.Random;

public class Guarana extends Roslina{
    public Guarana(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
        this.sila = 0;
        this.inicjatywa = 0;
        this.gatunek = 'g';
        this.czasZycia = 0;
    }
    public Guarana(int polozenieX, int polozenieY, int sila, int inicjatywa, char gatunek, int czasZycia, Swiat swiat) {
        super(polozenieX, polozenieY, sila, inicjatywa, gatunek, czasZycia, swiat);
    }

    @Override
    public void DodajDziecko(int x, int y) {
        Random rand = new Random();
        int szansa = rand.nextInt(5);
        if (szansa == 1) {
            System.out.println(gatunek + "(" + polozenieX + ", " + polozenieY + ") rozprzestrzenil(a) sie na (" + x + ", " + y + ").");

            swiat.DodacOrganizm(new Guarana(x, y, swiat));
        }
    }

    @Override
    public void kolizja(Organizm organizm) {
        System.out.println(organizm.GetGatunek() + "(" + organizm.GetPolozenieX() + ", " + organizm.GetPolozenieY() + ") zjadl " + gatunek + "(" + polozenieX + ", " + polozenieY + ").");

        int x = polozenieX;
        int y = polozenieY;
        swiat.ZabicOrganizm(polozenieX, polozenieY);
        organizm.SetPolozenieX(x);
        organizm.SetPolozenieY(y);
        organizm.SetSila(organizm.GetSila() + 3);
    }

    @Override
    public int czyOdbilAtak(Organizm atakujacy) {
        return 0;
    }
}