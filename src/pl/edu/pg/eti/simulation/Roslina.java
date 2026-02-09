package pl.edu.pg.eti.simulation;

import java.util.Random;

abstract public class Roslina extends Organizm{
    public Roslina(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
    }

    public Roslina(int polozenieX, int polozenieY, int sila, int inicjatywa, char gatunek, int czasZycia, Swiat swiat) {
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

        if (swiat.GetOrganizm(x, y) == null) {
            this.DodajDziecko(x, y);
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
    }
}