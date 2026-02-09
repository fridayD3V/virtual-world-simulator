package pl.edu.pg.eti.simulation;

import java.util.Random;

public class Zolw extends Zwierze {
    public Zolw(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
        this.sila = 2;
        this.inicjatywa = 1;
        this.gatunek = 'z';
        this.czasZycia = 0;
    }

    public Zolw(int polozenieX, int polozenieY, int sila, int inicjatywa, char gatunek, int czasZycia, Swiat swiat) {
        super(polozenieX, polozenieY, sila, inicjatywa, gatunek, czasZycia, swiat);
    }

    @Override
    public void akcja() {
        Random rand = new Random();
        int x = polozenieX;
        int y = polozenieY;
        int kierunek = 1 + rand.nextInt(4);

        if (kierunek == 1) {
            kierunek = 1 + rand.nextInt(4);

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
    }

    @Override
    public void DodajDziecko(int x, int y) {
        System.out.println(gatunek + "(" + polozenieX + ", " + polozenieY + ") teraz ma dziecko(" + x + ", " + y + ").");

        swiat.DodacOrganizm(new Zolw(x, y, swiat));
    }

    @Override
    public int czyOdbilAtak(Organizm atakujacy) {
        if (atakujacy.GetSila() < 5) {
            return 1;
        }

        return 0;
    }
}