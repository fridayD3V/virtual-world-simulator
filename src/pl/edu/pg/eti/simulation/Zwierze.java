package pl.edu.pg.eti.simulation;

import java.util.Random;

abstract public class Zwierze extends Organizm {

    public Zwierze(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
    }

    public Zwierze(int polozenieX, int polozenieY, int sila, int inicjatywa, char gatunek, int czasZycia, Swiat swiat) {
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
    public void kolizja(Organizm organizm) {
        int x = 0;
        int y = 0;
        if (organizm.GetGatunek() == gatunek) {

            if (swiat.CheckCoords(polozenieX, polozenieY - 1) == 1 && swiat.GetOrganizm(polozenieX, polozenieY - 1) == null) {
                this.DodajDziecko(polozenieX, polozenieY - 1);
            }
            else if (swiat.CheckCoords(polozenieX + 1, polozenieY - 1) == 1 && swiat.GetOrganizm(polozenieX + 1, polozenieY - 1) == null) {
                this.DodajDziecko(polozenieX + 1, polozenieY - 1);
            }
            else if (swiat.CheckCoords(polozenieX + 1, polozenieY) == 1 && swiat.GetOrganizm(polozenieX + 1, polozenieY) == null) {
                this.DodajDziecko(polozenieX + 1, polozenieY);
            }
            else if (swiat.CheckCoords(polozenieX + 1, polozenieY + 1) == 1 && swiat.GetOrganizm(polozenieX + 1, polozenieY + 1) == null) {
                this.DodajDziecko(polozenieX + 1, polozenieY + 1);
            }
            else if (swiat.CheckCoords(polozenieX, polozenieY + 1) == 1 && swiat.GetOrganizm(polozenieX, polozenieY + 1) == null) {
                this.DodajDziecko(polozenieX, polozenieY + 1);
            }
            else if (swiat.CheckCoords(polozenieX - 1, polozenieY + 1) == 1 && swiat.GetOrganizm(polozenieX - 1, polozenieY + 1) == null) {
                this.DodajDziecko(polozenieX - 1, polozenieY + 1);
            }
            else if (swiat.CheckCoords(polozenieX - 1, polozenieY) == 1 && swiat.GetOrganizm(polozenieX - 1, polozenieY) == null) {
                this.DodajDziecko(polozenieX - 1, polozenieY);
            }
            else if (swiat.CheckCoords(polozenieX - 1, polozenieY - 1) == 1 && swiat.GetOrganizm(polozenieX - 1, polozenieY - 1) == null) {
                this.DodajDziecko(polozenieX - 1, polozenieY - 1);
            }
        }
        else {
            if (organizm.GetSila() >= sila) {
                if (this.czyOdbilAtak(organizm) == 0) {
                    System.out.println(organizm.GetGatunek() + "(" + organizm.GetPolozenieX() + ", " + organizm.GetPolozenieY() + ") zabil " + gatunek + "(" + polozenieX + ", " + polozenieY + ").");

                    x = polozenieX;
                    y = polozenieY;
                    swiat.ZabicOrganizm(x, y);
                    organizm.SetPolozenieX(x);
                    organizm.SetPolozenieY(y);
                }
		        else {
                    System.out.println(gatunek + "(" + polozenieX + ", " + polozenieY + ") uratowal sie od " + organizm.GetGatunek() + "(" + organizm.GetPolozenieX() + ", " + organizm.GetPolozenieY() + ").");
                }
            }
            else {
                if (organizm.czyOdbilAtak(this) == 0) {
                    System.out.println(gatunek + "(" + polozenieX + ", " + polozenieY + ") zabil " + organizm.GetGatunek() + "(" + organizm.GetPolozenieX() + ", " + organizm.GetPolozenieY() + ").");

                    x = organizm.GetPolozenieX();
                    y = organizm.GetPolozenieY();
                    swiat.ZabicOrganizm(x, y);
                }
                else {
                    System.out.println(organizm.GetGatunek() + "(" + organizm.GetPolozenieX() + ", " + organizm.GetPolozenieY() + ") uratowal sie od " + gatunek + "(" + polozenieX + ", " + polozenieY + ").");
                }
            }
        }
    }
}