package pl.edu.pg.eti.simulation;

public class Wilk extends Zwierze {
    public Wilk(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
        this.sila = 9;
        this.inicjatywa = 5;
        this.gatunek = 'w';
        this.czasZycia = 0;
    }

    public Wilk(int polozenieX, int polozenieY, int sila, int inicjatywa, char gatunek, int czasZycia, Swiat swiat) {
        super(polozenieX, polozenieY, sila, inicjatywa, gatunek, czasZycia, swiat);
    }

    @Override
    public void DodajDziecko(int x, int y) {
        System.out.println(gatunek + "(" + polozenieX + ", " + polozenieY + ") teraz ma dziecko(" + x + ", " + y + ").");

        swiat.DodacOrganizm(new Wilk(x, y, swiat));
    }

    @Override
    public int czyOdbilAtak(Organizm atakujacy) {
        return 0;
    }
}