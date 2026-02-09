package pl.edu.pg.eti.simulation;

public class Owca extends Zwierze {
    public Owca(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
        this.sila = 4;
        this.inicjatywa = 4;
        this.gatunek = 'o';
        this.czasZycia = 0;
    }

    public Owca(int polozenieX, int polozenieY, int sila, int inicjatywa, char gatunek, int czasZycia, Swiat swiat) {
        super(polozenieX, polozenieY, sila, inicjatywa, gatunek, czasZycia, swiat);
    }

    @Override
    public void DodajDziecko(int x, int y) {
        System.out.println(gatunek + "(" + polozenieX + ", " + polozenieY + ") teraz ma dziecko(" + x + ", " + y + ").");

        swiat.DodacOrganizm(new Owca(x, y, swiat));
    }

    @Override
    public int czyOdbilAtak(Organizm atakujacy) {
        return 0;
    }
}
