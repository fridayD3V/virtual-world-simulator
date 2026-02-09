package pl.edu.pg.eti.simulation;

public class Czlowiek extends Zwierze {
    public Czlowiek(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
        this.sila = 5;
        this.inicjatywa = 4;
        this.gatunek = 'c';
        this.czasZycia = 0;
    }
    public Czlowiek(int polozenieX, int polozenieY, int sila, int inicjatywa, char gatunek, int czasZycia, Swiat swiat) {
        super(polozenieX, polozenieY, sila, inicjatywa, gatunek, czasZycia, swiat);
    }

    @Override
    public void akcja() {
        int x = polozenieX;
        int y = polozenieY;
        int kierunek = swiat.GetKierunek();
        if (kierunek == 0) {}
        else if (kierunek == 1 && y > 0) {
            y--;
        }
        else if (kierunek == 2 && x < swiat.GetRozmiarX() - 1) {
            x++;
        }
        else if (kierunek == 3 && y < swiat.GetRozmiarY() - 1) {
            y++;
        }
        else if (kierunek == 4 && x > 0) {
            x--;
        }

        if (swiat.GetOrganizm(x, y) != null && swiat.GetKierunek() != 0) {
            swiat.GetOrganizm(x, y).kolizja(this);
        }
        else {
            polozenieX = x;
            polozenieY = y;
        }

        swiat.SetKierunek(0);
    }

    @Override
    public void DodajDziecko(int x, int y) {}

    public int czyOdbilAtak(Organizm atakujacy) {
        if (swiat.GetUmiejetnosc() == 1) {
            return 1;
        }
        return 0;
    }
}