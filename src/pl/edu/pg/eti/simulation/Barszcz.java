package pl.edu.pg.eti.simulation;

import java.util.Random;

public class Barszcz extends Roslina{
    public Barszcz(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
        this.sila = 10;
        this.inicjatywa = 0;
        this.gatunek = 'b';
        this.czasZycia = 0;
    }
    public Barszcz(int polozenieX, int polozenieY, int sila, int inicjatywa, char gatunek, int czasZycia, Swiat swiat) {
        super(polozenieX, polozenieY, sila, inicjatywa, gatunek, czasZycia, swiat);
    }

    @Override
    public void DodajDziecko(int x, int y) {
        Random rand = new Random();
        int szansa = rand.nextInt(7);
        if (szansa == 1) {
            System.out.println(gatunek + "(" + polozenieX + ", " + polozenieY + ") rozprzestrzenil(a) sie na (" + x + ", " + y + ").");

            swiat.DodacOrganizm(new Barszcz(x, y, swiat));
        }
    }

    @Override
    public void akcja() {
        Random rand = new Random();
        int x = polozenieX;
        int y = polozenieY;
        int kierunek = 1 + rand.nextInt(4);

        switch (kierunek) {
            case 1:
                if (y != 0)
                    y--;
                break;
            case 2:
                if (x != swiat.GetRozmiarX() - 1)
                    x++;
                break;
            case 3:
                if (y != swiat.GetRozmiarY() - 1)
                    y++;
                break;
            case 4:
                if (x != 0)
                    x--;
                break;
        }

        if (swiat.GetOrganizm(x, y) == null) {
            this.DodajDziecko(x, y);
        }

        if (swiat.CheckCoords(polozenieX, polozenieY - 1) == 1 && swiat.GetOrganizm(polozenieX, polozenieY - 1) != null) {
            if (swiat.GetOrganizm(polozenieX, polozenieY - 1).GetGatunek() != 'b')
                swiat.ZabicOrganizm(polozenieX, polozenieY - 1);
        }
        if (swiat.CheckCoords(polozenieX + 1, polozenieY) == 1 && swiat.GetOrganizm(polozenieX + 1, polozenieY) != null) {
            if (swiat.GetOrganizm(polozenieX + 1, polozenieY).GetGatunek() != 'b')
                swiat.ZabicOrganizm(polozenieX + 1, polozenieY);
        }
        if (swiat.CheckCoords(polozenieX, polozenieY + 1) == 1 && swiat.GetOrganizm(polozenieX, polozenieY + 1) != null) {
            if (swiat.GetOrganizm(polozenieX, polozenieY + 1).GetGatunek() != 'b')
                swiat.ZabicOrganizm(polozenieX, polozenieY + 1);
        }
        if (swiat.CheckCoords(polozenieX - 1, polozenieY) == 1 && swiat.GetOrganizm(polozenieX - 1, polozenieY) != null) {
            if (swiat.GetOrganizm(polozenieX - 1, polozenieY).GetGatunek() != 'b')
                swiat.ZabicOrganizm(polozenieX - 1, polozenieY);
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