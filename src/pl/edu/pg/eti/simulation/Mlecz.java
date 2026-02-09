package pl.edu.pg.eti.simulation;

import java.util.Random;

public class Mlecz extends Roslina{
    public Mlecz(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
        this.sila = 0;
        this.inicjatywa = 0;
        this.gatunek = 'm';
        this.czasZycia = 0;
    }
    public Mlecz(int polozenieX, int polozenieY, int sila, int inicjatywa, char gatunek, int czasZycia, Swiat swiat) {
        super(polozenieX, polozenieY, sila, inicjatywa, gatunek, czasZycia, swiat);
    }

    @Override
    public void akcja() {
        Random rand = new Random();
        int x = polozenieX;
        int y = polozenieY;

        for (int i = 0; i < 3; i++) {
            x = polozenieX;
            y = polozenieY;
            int kierunek = 1 + rand.nextInt(6);

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

            if (swiat.GetOrganizm(x, y) == null && (rand.nextInt(2)) == 1) {
                this.DodajDziecko(x, y);
                break;
            }
        }
    }

    @Override
    public void DodajDziecko(int x, int y) {
        System.out.println(gatunek + "(" + polozenieX + ", " + polozenieY + ") rozprzestrzenil(a) sie na (" + x + ", " + y + ").");

        swiat.DodacOrganizm(new Mlecz(x, y, swiat));
    }

    @Override
    public int czyOdbilAtak(Organizm atakujacy) {
        return 0;
    }
}