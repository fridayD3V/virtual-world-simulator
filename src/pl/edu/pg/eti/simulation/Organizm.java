package pl.edu.pg.eti.simulation;

abstract public class Organizm {
    protected int sila, inicjatywa, polozenieX, polozenieY, czasZycia;
    protected char gatunek;
    protected Swiat swiat;

    public Organizm(int polX, int polY, Swiat swiat) {
        this.polozenieX = polX;
        this.polozenieY = polY;
        this.swiat = swiat;
        czasZycia = 0;
    }

    public Organizm(int polozenieX, int polozenieY, int sila, int inicjatywa, char gatunek, int czasZycia, Swiat swiat) {
        this.polozenieX = polozenieX;
        this.polozenieY = polozenieY;
        this.sila = sila;
        this.inicjatywa = inicjatywa;
        this.gatunek = gatunek;
        this.czasZycia = czasZycia;
        this.swiat = swiat;
    }

    abstract public void akcja();
    abstract public void kolizja(Organizm organizm);

    abstract public int czyOdbilAtak(Organizm atakujacy);
    abstract public void DodajDziecko(int x, int y);

    public void rysowanie()
{
    swiat.DodajZnak(polozenieX, polozenieY, gatunek);
}

    public void SetSila(int s)
    {
        sila = s;
    }
    public void SetPolozenieX(int x)
    {
        polozenieX = x;
    }
    public void SetPolozenieY(int y)
    {
        polozenieY = y;
    }
    public void SetWiek(int w)
    {
        czasZycia = w;
    }

    public int GetPolozenieX()
    {
        return polozenieX;
    }
    public int GetPolozenieY()
    {
        return polozenieY;
    }
    public int GetSila()
    {
        return sila;
    }
    public int GetInicjatywa()
    {
        return inicjatywa;
    }
    public int GetCzasZycia()
    {
        return czasZycia;
    }
    public char GetGatunek()
    {
        return gatunek;
    }
    public void PowiekszCzasZycia()
    {
        czasZycia++;
    }
}