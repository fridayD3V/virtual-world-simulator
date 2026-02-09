package pl.edu.pg.eti.simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Random;

public class Swiat implements ActionListener, KeyListener  {
    JFrame Frame = new JFrame("Klim Kaliasniou, 201250");
    JTextArea JTextBox = new JTextArea();
    JPanel JPanelTitle = new JPanel();
    JPanel ButtonsContainer = new JPanel();
    JPanel JPanelButton = new JPanel();
    JPanel JPanelMenuButtons = new JPanel();
    JPanel JPanelForTextBox = new JPanel();
    JButton[][] JButtons;
    JButton[] MenuButtons;
    Random rand = new Random();

    Czlowiek c;

    private int rozmiarX, rozmiarY;
    private char[][] plansza;
    private Organizm[] wszystkieOrganizmy;
    private int kierunek; //0 - zostaje, 1 - up, 2 - right, 3 - down, 4 - left
    private int czlowiekX;
    private int czlowiekY;
    private int iloscOrganizmow;
    private int umiejetnosc;
    private int iloscTurow;


    public Swiat(int rX, int rY) {
        c = null;
        rozmiarX = rX;
        rozmiarY = rY;

        iloscOrganizmow = 1;
        umiejetnosc = 0;
        iloscTurow = -1;

        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setSize(rozmiarX * 120, rozmiarY * 70);
        Frame.getContentPane().setBackground(Color.DARK_GRAY);
        Frame.setLayout(new BorderLayout());
        Frame.setVisible(true);
        Frame.addKeyListener(this);

        JTextBox.setBackground(new Color(115,81,132));
        JTextBox.setForeground(new Color(224,176,255));
        JTextBox.setFont(new Font(Font.DIALOG, Font.BOLD, Frame.getWidth()/rozmiarY/7));
        JTextBox.setOpaque(true);

        JPanelTitle.setLayout(new BorderLayout());
        JPanelTitle.setBounds(0, 0, 2000, 2000);

        ButtonsContainer.setLayout(new GridLayout(1,2));
        ButtonsContainer.setBounds(0, 0, 2000, 2000);

        JPanelButton.setLayout(new GridLayout(rozmiarY, rozmiarX));
        JPanelButton.setBackground(new Color(115,81,132));

        JPanelMenuButtons.setLayout(new GridLayout(3, 1));
        JPanelMenuButtons.setBackground(new Color(115,81,132));

        JPanelForTextBox.setLayout(new BorderLayout());
        JPanelForTextBox.setBackground(new Color(38, 38, 38));

        JButtons = new JButton[rozmiarY][];
        for(int i=0;i<rozmiarY; i++) {
            JButtons[i] = new JButton[rozmiarX];
            for(int j=0; j<rozmiarX; j++) {
                JButtons[i][j] = new JButton();
                JPanelButton.add(JButtons[i][j]);
                JButtons[i][j].setFont(new Font(Font.DIALOG, Font.BOLD, Frame.getWidth()/rozmiarX/8));
                JButtons[i][j].setForeground(new Color(115,81,132));
                JButtons[i][j].setFocusable(false);
                JButtons[i][j].setBackground(new Color(224,176,255));
                JButtons[i][j].addActionListener(this);
                JButtons[i][j].setText(' ' + "");
            }
        }

        MenuButtons = new JButton[3];
        for(int i=0;i<3; i++) {
            MenuButtons[i] = new JButton();
            JPanelMenuButtons.add(MenuButtons[i]);
            MenuButtons[i].setFont(new Font(Font.DIALOG, Font.BOLD, Frame.getWidth()/rozmiarX/5));
            MenuButtons[i].setForeground(new Color(224,176,255));
            MenuButtons[i].setFocusable(false);
            MenuButtons[i].setBackground(new Color(115,81,132));
            MenuButtons[i].addActionListener(this);
        }
        JPanelMenuButtons.setBounds(0, 0, 2000, 2000);
        MenuButtons[0].setText("Moc");
        MenuButtons[1].setText("SAVE");
        MenuButtons[2].setText("LOAD");

        JPanelForTextBox.add(JTextBox);
        ButtonsContainer.add(JPanelButton);
        ButtonsContainer.add(JPanelForTextBox);
        Frame.add(ButtonsContainer, BorderLayout.CENTER);
        Frame.add(JPanelMenuButtons, BorderLayout.EAST);

        kierunek = 0;

        wszystkieOrganizmy = new Organizm [rozmiarX * rozmiarY];
        for (int i = 0; i < rozmiarX * rozmiarY; i++) {
            wszystkieOrganizmy[i] = null;
        }

        plansza = new char[rozmiarY][];
        for (int i = 0; i < rozmiarY; i++) {
            plansza[i] = new char[rozmiarX];
        }
        for (int i = 0; i < rozmiarY; i++) {
            for (int j = 0; j < rozmiarX; j++) {
                plansza[i][j] = '.';
            }
        }

        int randX = rand.nextInt(rozmiarX);
        int randY = rand.nextInt(rozmiarY);

        for (int i = 0; i < 2; i++) {
            while (GetOrganizm(randX, randY) != null) {
                randX = rand.nextInt(rozmiarX);
                randY = rand.nextInt(rozmiarY);
            }
            wszystkieOrganizmy[iloscOrganizmow] = new Owca(randX, randY, this);
            iloscOrganizmow++;
        }
        for (int i = 0; i < 2; i++) {
            while (GetOrganizm(randX, randY) != null) {
                randX = rand.nextInt(rozmiarX);
                randY = rand.nextInt(rozmiarY);
            }
            wszystkieOrganizmy[iloscOrganizmow] = new Wilk(randX, randY, this);
            iloscOrganizmow++;
        }
        for (int i = 0; i < 2; i++) {
            while (GetOrganizm(randX, randY) != null) {
                randX = rand.nextInt(rozmiarX);
                randY = rand.nextInt(rozmiarY);
            }
            wszystkieOrganizmy[iloscOrganizmow] = new Lis(randX, randY, this);
            iloscOrganizmow++;
        }
        for (int i = 0; i < 2; i++) {
            while (GetOrganizm(randX, randY) != null) {
                randX = rand.nextInt(rozmiarX);
                randY = rand.nextInt(rozmiarY);
            }
            wszystkieOrganizmy[iloscOrganizmow] = new Zolw(randX, randY, this);
            iloscOrganizmow++;
        }
        for (int i = 0; i < 2; i++) {
            while (GetOrganizm(randX, randY) != null) {
                randX = rand.nextInt(rozmiarX);
                randY = rand.nextInt(rozmiarY);
            }
            wszystkieOrganizmy[iloscOrganizmow] = new Antylopa(randX, randY, this);
            iloscOrganizmow++;
        }

        while (GetOrganizm(randX, randY) != null) {
            randX = rand.nextInt(rozmiarX);
            randY = rand.nextInt(rozmiarY);
        }
        wszystkieOrganizmy[iloscOrganizmow] = new Trawa(randX, randY, this);
        iloscOrganizmow++;

        while (GetOrganizm(randX, randY) != null) {
            randX = rand.nextInt(rozmiarX);
            randY = rand.nextInt(rozmiarY);
        }
        wszystkieOrganizmy[iloscOrganizmow] = new Mlecz(randX, randY, this);
        iloscOrganizmow++;

        while (GetOrganizm(randX, randY) != null) {
            randX = rand.nextInt(rozmiarX);
            randY = rand.nextInt(rozmiarY);
        }
        wszystkieOrganizmy[iloscOrganizmow] = new Jagody(randX, randY, this);
        iloscOrganizmow++;

        while (GetOrganizm(randX, randY) != null) {
            randX = rand.nextInt(rozmiarX);
            randY = rand.nextInt(rozmiarY);
        }
        wszystkieOrganizmy[iloscOrganizmow] = new Guarana(randX, randY, this);
        iloscOrganizmow++;

        while (GetOrganizm(randX, randY) != null) {
            randX = rand.nextInt(rozmiarX);
            randY = rand.nextInt(rozmiarY);
        }
        wszystkieOrganizmy[iloscOrganizmow] = new Barszcz(randX, randY, this);
        iloscOrganizmow++;

        int randXCzlowieka, randYCzlowieka;
        while(true) {
            randXCzlowieka = rand.nextInt(rozmiarX);
            randYCzlowieka = rand.nextInt(rozmiarY);
            if (GetOrganizm(randXCzlowieka, randYCzlowieka) == null) {
                break;
            }
        }
        czlowiekX = randXCzlowieka;
        czlowiekY = randYCzlowieka;
    }

    public void Start() {
        c = new Czlowiek(GetCzlowiekX(), GetCzlowiekY(), this);
        DodacCzlowieka(c);
        DrawMenu();
        rysujSwiat();
    }

    public Organizm GetOrganizm(int x, int y) {
        for (int i = 0; i < rozmiarX * rozmiarY; i++) {
            if (wszystkieOrganizmy[i] != null) {
                if (wszystkieOrganizmy[i].GetPolozenieX() == x && wszystkieOrganizmy[i].GetPolozenieY() == y) {
                    return wszystkieOrganizmy[i];
                }
            }
        }
        return null;
    }

   public void DodacOrganizm(Organizm organizm) {
        if (wszystkieOrganizmy[iloscOrganizmow] == null) {
            wszystkieOrganizmy[iloscOrganizmow] = organizm;
            iloscOrganizmow++;
        }
    }

    public void DodacCzlowieka(Organizm organizm) {
        wszystkieOrganizmy[0] = organizm;
        JButtons[wszystkieOrganizmy[0].GetPolozenieY()][wszystkieOrganizmy[0].GetPolozenieX()].setText(wszystkieOrganizmy[0].GetGatunek() + "");
    }

    public void DrawMenu() {
        JTextBox.setText("");
        JTextBox.append("Strzalki -> kierunek\n");
        JTextBox.append("- -> zostanie na miejscu\n\n");
        switch(kierunek) {
            case 0: {
                JTextBox.append("Kierunek: -\n\n");
                break;
            }
            case 1: {
                JTextBox.append("Kierunek: gora [ENTER]\n\n");
                break;
            }
            case 2: {
                JTextBox.append("Kierunek: prawo [ENTER]\n\n");
                break;
            }
            case 3: {
                JTextBox.append("Kierunek: dol [ENTER]\n\n");
                break;
            }
            case 4: {
                JTextBox.append("Kierunek: lewo [ENTER]\n\n");
                break;
            }
        }

        if (iloscTurow >= 0) {
            if (umiejetnosc == 1) {
                JTextBox.append("Umiejetnosc aktywna\n");
            }
            else {
                JTextBox.append("Blok umiejetnosci\n");
            }
            JTextBox.append(String.valueOf(iloscTurow));
        }
    }

    public void wykonajTure() {
        if (iloscTurow > 0) {
            iloscTurow--;
        }
        else if (iloscTurow == 0) {
            if (umiejetnosc == 1) {
                umiejetnosc = 0;
                iloscTurow = 5;
            }
            else {
                iloscTurow = -1;
            }
        }

        for (int i = 0; i < rozmiarX * rozmiarY; i++) {
            if (wszystkieOrganizmy[i] != null)
                wszystkieOrganizmy[i].PowiekszCzasZycia();
        }

        //sort
        for (int i = 0; i < (rozmiarX * rozmiarY) - 2; i++) {
            for (int j = 1; j < (rozmiarX * rozmiarY) - 1 - i; j++) {
                if (wszystkieOrganizmy[j] != null && wszystkieOrganizmy[j + 1] != null) {
                    if (wszystkieOrganizmy[j].GetInicjatywa() < wszystkieOrganizmy[j + 1].GetInicjatywa()) {
                        Organizm temp = wszystkieOrganizmy[j];
                        wszystkieOrganizmy[j] = wszystkieOrganizmy[j + 1];
                        wszystkieOrganizmy[j + 1] = temp;
                    }
                }
            }
        }
        for (int i = 0; i < (rozmiarX * rozmiarY) - 2; i++) {
            for (int j = 1; j < (rozmiarX * rozmiarY) - 1 - i; j++) {
                if (wszystkieOrganizmy[j] != null && wszystkieOrganizmy[j + 1] != null) {
                    if (wszystkieOrganizmy[j].GetInicjatywa() == wszystkieOrganizmy[j + 1].GetInicjatywa() && wszystkieOrganizmy[j].GetCzasZycia() < wszystkieOrganizmy[j + 1].GetCzasZycia()) {
                        Organizm temp = wszystkieOrganizmy[j];
                        wszystkieOrganizmy[j] = wszystkieOrganizmy[j + 1];
                        wszystkieOrganizmy[j + 1] = temp;
                    }
                }
            }
        }

        for (int i = 0; i < rozmiarX*rozmiarY; i++) {
            if (wszystkieOrganizmy[i] != null) {
                wszystkieOrganizmy[i].akcja();
            }
        }
        rysujSwiat();
    }

    public void rysujSwiat() {
        for (int i = 0; i < rozmiarY; i++) {
            for (int j = 0; j < rozmiarX; j++) {
                plansza[i][j] = '.';
            }
        }

        for (int i = 0; i < rozmiarX*rozmiarY; i++) {
            if (wszystkieOrganizmy[i] != null) {
                wszystkieOrganizmy[i].rysowanie();
            }
        }

        for(int i=0; i<rozmiarY; i++) {
            for(int j=0; j<rozmiarX; j++) {
                if(GetOrganizm(j, i) == null) {
                    JButtons[i][j].setText(' ' + "");
                }
                else {
                    JButtons[i][j].setText(plansza[i][j] + "");
                }
            }
        }
    }

    public void DodajZnak(int x, int y, char znak) {
        plansza[y][x] = znak;
    }

    public void SetKierunek(int kierunek)
    {
        this.kierunek = kierunek;
    }

    public void ZabicOrganizm(int x, int y) {
        for (int i = 0; i < rozmiarX * rozmiarY; i++) {
            if (wszystkieOrganizmy[i] != null) {
                if (wszystkieOrganizmy[i].GetPolozenieX() == x && wszystkieOrganizmy[i].GetPolozenieY() == y) {
                    if (wszystkieOrganizmy[i].GetGatunek() == 'c' && GetUmiejetnosc() == 1) { break; }
                    else if (wszystkieOrganizmy[i].GetGatunek() == 'c') {
                        System.out.println("\nThe End?");
                        System.exit(0);
                    }

                    if (i != (iloscOrganizmow - 1)) {
                        wszystkieOrganizmy[i] = wszystkieOrganizmy[iloscOrganizmow - 1];
                        wszystkieOrganizmy[iloscOrganizmow - 1] = null;
                    }
                    else {
                        wszystkieOrganizmy[i] = null;
                    }
                    iloscOrganizmow--;
                }
            }
        }
    }

    public int CheckCoords(int x, int y) {
        if (x >= 0 && x < rozmiarX) {
            if (y >= 0 && y < rozmiarY) {
                return 1;
            }
        }
        return 0;
    }

    public void SetUmiejetnosc() {
        if (iloscTurow == -1) {
            umiejetnosc = 1;
            iloscTurow = 5;
        }
    }

    public void Save() {
        try {
            File file = new File("save.txt");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);

            writer.write(rozmiarX + " " + rozmiarY + " " + iloscOrganizmow + " " + umiejetnosc + " " + iloscTurow + "\n");
            for (int i = 0; i < rozmiarX * rozmiarY; i++) {
                if (wszystkieOrganizmy[i] == null) {
                    break;
                } else {
                    writer.write(wszystkieOrganizmy[i].GetGatunek() + " " + wszystkieOrganizmy[i].GetPolozenieX() + " " +
                            wszystkieOrganizmy[i].GetPolozenieY() + " " + wszystkieOrganizmy[i].GetSila() + " " +
                            wszystkieOrganizmy[i].GetInicjatywa() + " " + wszystkieOrganizmy[i].GetCzasZycia() + "\n");
                }
            }
            writer.close();

            System.out.println("Zapisano do pliku.");
        } catch (IOException e) {
            System.out.println("Wystapil blad.");
            e.printStackTrace();
        }
    }

    public void Load() {
        try {
            File file = new File("save.txt");
            if(!file.exists()) {
                System.out.println("Plik nie istnieje.");
                return;
            }
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);

            for(int i=0; i < rozmiarX * rozmiarY; i++) {
                if(wszystkieOrganizmy[i] != null) {
                    wszystkieOrganizmy[i] = null;
                }
            }
            wszystkieOrganizmy = null;

            for(int i=0; i < rozmiarY; i++) {
                if(plansza[i] != null) {
                    plansza[i] = null;
                }
            }
            plansza = null;

            for(int i=0; i < rozmiarY; i++) {
                for(int j=0; j < rozmiarX; j++) {
                    if(JButtons[i][j] != null)
                        JButtons[i][j] = null;
                }
                JButtons[i] = null;
            }
            JButtons = null;
            JPanelButton.removeAll();

            String line = bufferedReader.readLine();
            String[] parts = line.split(" ");
            rozmiarX = Integer.parseInt(parts[0]);
            rozmiarY = Integer.parseInt(parts[1]);
            iloscOrganizmow = Integer.parseInt(parts[2]);
            umiejetnosc = Integer.parseInt(parts[3]);
            iloscTurow = Integer.parseInt(parts[4]);

            Frame.setSize(rozmiarX * 120, rozmiarY * 70);
            JPanelButton.setLayout(new GridLayout(rozmiarY, rozmiarX));

            JButtons = new JButton[rozmiarY][];
            for(int i=0;i < rozmiarY; i++) {
                JButtons[i] = new JButton[rozmiarX];
                for(int j=0; j < rozmiarX; j++) {
                    JButtons[i][j] = new JButton();
                    JPanelButton.add(JButtons[i][j]);
                    JButtons[i][j].setFont(new Font(Font.DIALOG, Font.BOLD, Frame.getWidth()/rozmiarX/8));
                    JButtons[i][j].setForeground(new Color(115,81,132));
                    JButtons[i][j].setFocusable(false);
                    JButtons[i][j].setBackground(new Color(224,176,255));
                    JButtons[i][j].addActionListener(this);
                    JButtons[i][j].setText(' ' + "");
                }
            }

            kierunek = 0;

            wszystkieOrganizmy = new Organizm [rozmiarX * rozmiarY];
            for (int i = 0; i < rozmiarX * rozmiarY; i++) {
                wszystkieOrganizmy[i] = null;
            }

            plansza = new char[rozmiarY][];
            for (int i = 0; i < rozmiarY; i++) {
                plansza[i] = new char[rozmiarX];
            }
            for (int i = 0; i < rozmiarY; i++) {
                for (int j = 0; j < rozmiarX; j++) {
                    plansza[i][j] = '.';
                }
            }

            String linia;
            int counter = 0;
            while ((linia = bufferedReader.readLine()) != null) {
                String[] dane = linia.split(" ");
                char gatunek = dane[0].charAt(0);
                int x = Integer.parseInt(dane[1]);
                int y = Integer.parseInt(dane[2]);
                int sila = Integer.parseInt(dane[3]);
                int inicjatywa = Integer.parseInt(dane[4]);
                int czaszycia = Integer.parseInt(dane[5]);

                switch (gatunek) {
                    case 'c': {
                        c = new Czlowiek(x, y, sila, inicjatywa, gatunek, czaszycia, this);
                        wszystkieOrganizmy[0] = c;
                        break;
                    }
                    case 'o': {
                        wszystkieOrganizmy[counter] = new Owca(x, y, sila, inicjatywa, gatunek, czaszycia, this);
                        break;
                    }
                    case 'w': {
                        wszystkieOrganizmy[counter] = new Wilk(x, y, sila, inicjatywa, gatunek, czaszycia, this);
                        break;
                    }
                    case 'l': {
                        wszystkieOrganizmy[counter] = new Lis(x, y, sila, inicjatywa, gatunek, czaszycia, this);
                        break;
                    }
                    case 'z': {
                        wszystkieOrganizmy[counter] = new Zolw(x, y, sila, inicjatywa, gatunek, czaszycia, this);
                        break;
                    }
                    case 'a': {
                        wszystkieOrganizmy[counter] = new Antylopa(x, y, sila, inicjatywa, gatunek, czaszycia, this);
                        break;
                    }
                    case 't': {
                        wszystkieOrganizmy[counter] = new Trawa(x, y, sila, inicjatywa, gatunek, czaszycia, this);
                        break;
                    }
                    case 'm': {
                        wszystkieOrganizmy[counter] = new Mlecz(x, y, sila, inicjatywa, gatunek, czaszycia, this);
                        break;
                    }
                    case 'g': {
                        wszystkieOrganizmy[counter] = new Guarana(x, y, sila, inicjatywa, gatunek, czaszycia, this);
                        break;
                    }
                    case 'j': {
                        wszystkieOrganizmy[counter] = new Jagody(x, y, sila, inicjatywa, gatunek, czaszycia, this);
                        break;
                    }
                    case 'b': {
                        wszystkieOrganizmy[counter] = new Barszcz(x, y, sila, inicjatywa, gatunek, czaszycia, this);
                        break;
                    }
                }
                counter++;

            }
            rysujSwiat();

            bufferedReader.close();
            reader.close();

            System.out.println("Odczytano z pliku.");
        } catch (IOException e) {
            System.out.println("Wystapil blad.");
            e.printStackTrace();
        }
    }

    public int GetUmiejetnosc() {
        return umiejetnosc;
    }

    public int GetRozmiarX() { return rozmiarX; }
    public int GetRozmiarY() { return rozmiarY; }
    public int GetCzlowiekX() { return czlowiekX; }
    public int GetCzlowiekY() { return czlowiekY; }
    public int GetKierunek() { return kierunek; }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== MenuButtons[0]) {
            SetUmiejetnosc();
            DrawMenu();
            return;
        }
        if(e.getSource()== MenuButtons[1]) {
            Save();
            return;
        }
        if(e.getSource()== MenuButtons[2]) {
            Load();
            DrawMenu();
            return;
        }
        for(int i=0; i < rozmiarY; i++) {
            for(int j=0; j < rozmiarX; j++) {
                if(e.getSource()== JButtons[i][j]) {
                    if (GetOrganizm(j, i) == null) {
                        String[] opcje = {"pl.edu.pg.eti.simulation.Wilk", "pl.edu.pg.eti.simulation.Owca", "pl.edu.pg.eti.simulation.Lis", "pl.edu.pg.eti.simulation.Zolw", "pl.edu.pg.eti.simulation.Antylopa", "pl.edu.pg.eti.simulation.Trawa", "pl.edu.pg.eti.simulation.Mlecz", "pl.edu.pg.eti.simulation.Guarana", "pl.edu.pg.eti.simulation.Jagody", "pl.edu.pg.eti.simulation.Barszcz"};
                        int opcja = JOptionPane.showOptionDialog(null, "Ktory?", "Dodac organizm", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcje, opcje[0]);
                        switch(opcja) {
                            case 0:
                                wszystkieOrganizmy[iloscOrganizmow] = new Wilk(j, i, this);
                                iloscOrganizmow++;
                                break;
                            case 1:
                                wszystkieOrganizmy[iloscOrganizmow] = new Owca(j, i, this);
                                iloscOrganizmow++;
                                break;
                            case 2:
                                wszystkieOrganizmy[iloscOrganizmow] = new Lis(j, i, this);
                                iloscOrganizmow++;
                                break;
                            case 3:
                                wszystkieOrganizmy[iloscOrganizmow] = new Zolw(j, i, this);
                                iloscOrganizmow++;
                                break;
                            case 4:
                                wszystkieOrganizmy[iloscOrganizmow] = new Antylopa(j, i, this);
                                iloscOrganizmow++;
                                break;
                            case 5:
                                wszystkieOrganizmy[iloscOrganizmow] = new Trawa(j, i, this);
                                iloscOrganizmow++;
                                break;
                            case 6:
                                wszystkieOrganizmy[iloscOrganizmow] = new Mlecz(j, i, this);
                                iloscOrganizmow++;
                                break;
                            case 7:
                                wszystkieOrganizmy[iloscOrganizmow] = new Guarana(j, i, this);
                                iloscOrganizmow++;
                                break;
                            case 8:
                                wszystkieOrganizmy[iloscOrganizmow] = new Jagody(j, i, this);
                                iloscOrganizmow++;
                                break;
                            case 9:
                                wszystkieOrganizmy[iloscOrganizmow] = new Barszcz(j, i, this);
                                iloscOrganizmow++;
                                break;
                        }
                        rysujSwiat();
                    }
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case 10: {
                if(c != null) {
                    wykonajTure();
                    DrawMenu();
                }
                break;
            }
            case 37: {
                if(c != null) {
                    SetKierunek(4);
                    DrawMenu();
                }
                break;
            }
            case 38: {
                if(c != null) {
                    SetKierunek(1);
                    DrawMenu();
                }
                break;
            }
            case 39: {
                if(c != null) {
                    SetKierunek(2);
                    DrawMenu();
                }
                break;
            }
            case 40:{
                if(c != null) {
                    SetKierunek(3);
                    DrawMenu();
                }
                break;
            }
            case 45: {
                if(c != null) {
                    SetKierunek(0);
                    DrawMenu();
                }
                break;
            }
        }
    }
}