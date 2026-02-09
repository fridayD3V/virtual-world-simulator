package pl.edu.pg.eti.simulation;

import java.util.Random;

public class Antylopa extends Zwierze {
    public Antylopa(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
        this.sila = 4;
        this.inicjatywa = 4;
        this.gatunek = 'a';
        this.czasZycia = 0;
    }

    public Antylopa(int polozenieX, int polozenieY, int sila, int inicjatywa, char gatunek, int czasZycia, Swiat swiat) {
        super(polozenieX, polozenieY, sila, inicjatywa, gatunek, czasZycia, swiat);
    }

    @Override
    public void akcja() {
        Random rand = new Random();
        int x = polozenieX;
        int y = polozenieY;
        int kierunek = 1 + rand.nextInt(4);

        switch (kierunek) {
            case 1:
                if (y > 1)
                    y -= 2;
                break;
            case 2:
                if (x < swiat.GetRozmiarX() - 2)
                    x += 2;
                break;
            case 3:
                if (y < swiat.GetRozmiarY() - 2)
                    y += 2;
                break;
            case 4:
                if (x > 1)
                    x -= 2;
                break;
        }

        if (swiat.GetOrganizm(x, y) != null) {
            if (x == polozenieX && y == polozenieY) {}
            else {
                swiat.GetOrganizm(x, y).kolizja(this);
            }
        }
        else {
            polozenieX = x;
            polozenieY = y;
        }
    }

    @Override
    public void DodajDziecko(int x, int y) {
        System.out.println(gatunek + "(" + polozenieX + ", " + polozenieY + ") teraz ma dziecko(" + x + ", " + y + ").");

        swiat.DodacOrganizm(new Antylopa(x, y, swiat));
    }

    @Override
    public int czyOdbilAtak(Organizm atakujacy) {
        Random rand = new Random();
        int ucieczka = rand.nextInt(2);
        if (ucieczka == 1) {
            int x = polozenieX;
            int y = polozenieY;
            if (swiat.CheckCoords(polozenieX, polozenieY - 1) == 1 && swiat.GetOrganizm(polozenieX, polozenieY - 1) == null) {
                polozenieY--;
            }
            else if (swiat.CheckCoords(polozenieX + 1, polozenieY - 1) == 1 && swiat.GetOrganizm(polozenieX + 1, polozenieY - 1) == null) {
                polozenieX++;
                polozenieY--;
            }
            else if (swiat.CheckCoords(polozenieX + 1, polozenieY) == 1 && swiat.GetOrganizm(polozenieX + 1, polozenieY) == null) {
                polozenieX++;
            }
            else if (swiat.CheckCoords(polozenieX + 1, polozenieY + 1) == 1 && swiat.GetOrganizm(polozenieX + 1, polozenieY + 1) == null) {
                polozenieX++;
                polozenieY++;
            }
            else if (swiat.CheckCoords(polozenieX, polozenieY + 1) == 1 && swiat.GetOrganizm(polozenieX, polozenieY + 1) == null) {
                polozenieY++;
            }
            else if (swiat.CheckCoords(polozenieX - 1, polozenieY + 1) == 1 && swiat.GetOrganizm(polozenieX - 1, polozenieY + 1) == null) {
                polozenieX--;
                polozenieY++;
            }
            else if (swiat.CheckCoords(polozenieX - 1, polozenieY) == 1 && swiat.GetOrganizm(polozenieX - 1, polozenieY) == null) {
                polozenieX--;
            }
            else if (swiat.CheckCoords(polozenieX - 1, polozenieY - 1) == 1 && swiat.GetOrganizm(polozenieX - 1, polozenieY - 1) == null) {
                polozenieX--;
                polozenieY--;
            }

            if (x == polozenieX && y == polozenieY) {}
            else {
                atakujacy.SetPolozenieX(x);
                atakujacy.SetPolozenieY(y);
            }

            return 1;
        }

        return 0;
    }
}