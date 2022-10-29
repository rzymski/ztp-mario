package pl.wipb.ztp.ps3;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


class Game extends JPanel {
    private final int TILESIZE = 32;
    private final List<Segment> plansza;
    private final Sprite sprite;
//    private List<Segment> stworzPlansze(String plik) {
//        BufferedReader br = null;
//        try {
//            InputStream pFile = getClass().getResourceAsStream(plik);
//            br = new BufferedReader(new InputStreamReader(pFile));
//            List<Segment> plansza = new ArrayList<>();
//            String linia;
//            int x;
//            int y = 4;
//            int liczba;
//            int znaki;
//            char znak;
//            char cyfra1;
//            char cyfra2;
//            while ((linia = br.readLine()) != null) {
//                x = 4;
//                znaki = 0;
//                while ((linia.length() - znaki) >= 3) {
//                    znak = linia.charAt(znaki++);
//                    cyfra1 = linia.charAt(znaki++);
//                    cyfra2 = linia.charAt(znaki++);
//                    liczba = (cyfra1 - '0') * 10 + (cyfra2 - '0');
//                    switch (znak) {
//                        case 'X':
//                            x += liczba * TILESIZE;
//                            break;
//                        case 'A':
//                            for (int i = 0; i < liczba; ++i) {
//                                Segment s = new SegmentBlock(x, y, "block1.png");
//                                plansza.add(s);
//                                x += TILESIZE;
//                            }
//                            break;
//                        case 'B':
//                            for (int i = 0; i < liczba; ++i) {
//                                Segment s = new SegmentBlockV(x, y, "block2.png");
//                                plansza.add(s);
//                                x += TILESIZE;
//                            }
//                            break;
//                        case 'C':
//                            for (int i = 0; i < liczba; ++i) {
//                                Segment s = new Segment(x, y, "block3.png");
//                                plansza.add(s);
//                                x += TILESIZE;
//                            }
//                            break;
//                        case 'G':
//                            for (int i = 0; i < liczba; ++i) {
//                                Segment s = new SegmentAnim(x, y, "bonus.png", new int[]{0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 2, 2, 1, 1, 1, 0, 0});
//                                plansza.add(s);
//                                x += TILESIZE;
//                            }
//                            break;
//                    }
//                }
//                y += TILESIZE;
//            }
//            br.close();
//            return plansza;
//        } catch (IOException e) {
//            System.out.println("Blad wczytania planszy");
//            e.printStackTrace();
//            return null;
//        }
//    }
    private void stworzPlansze(String plik, Builder b) {
        BufferedReader br = null;
        try {
            InputStream pFile = getClass().getResourceAsStream(plik);
            br = new BufferedReader(new InputStreamReader(pFile));
            String linia;
            //int x;
            //int y = 4;
            b.setY(4);
            int liczba;
            int znaki;
            char znak;
            char cyfra1;
            char cyfra2;
            while ((linia = br.readLine()) != null) {
                //x = 4;
                b.setX(4);
                znaki = 0;
                while ((linia.length() - znaki) >= 3) {
                    znak = linia.charAt(znaki++);
                    cyfra1 = linia.charAt(znaki++);
                    cyfra2 = linia.charAt(znaki++);
                    liczba = (cyfra1 - '0') * 10 + (cyfra2 - '0');
                    b.add(liczba, znak);
//                    switch (znak) {
//                        case 'X':
//                            b.addSegmenstAir(liczba);
//                            break;
//                        case 'A':
//                            b.addSegmentsBlock(liczba);
//                            break;
//                        case 'B':
//                            b.addSegmentsBlockV(liczba);
//                            break;
//                        case 'C':
//                            b.addSegments(liczba);
//                            break;
//                        case 'G':
//                            b.addSegmentsAnim(liczba);
//                            break;
//                    }
                }
                b.changeHight();
            }
            br.close();
        }catch (IOException e) {
            System.out.println("Blad wczytania planszy");
            e.printStackTrace();
        }
    }

    public Game(String plik) {
        setPreferredSize(new Dimension(424, 268));
        setFocusable(true);
        //plansza = stworzPlansze(plik);

        Builder b = new ModifiedBuilder();
        //Builder b = new NormalBuilder();
        stworzPlansze(plik, b);
        plansza = b.getResult();



        sprite = new Sprite(plansza, "mario.png");
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ev) {
                switch (ev.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_RIGHT:
                        sprite.stop();
                        break;
                }
            }

            @Override
            public void keyPressed(KeyEvent ev) {
                switch (ev.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        sprite.left();
                        break;
                    case KeyEvent.VK_RIGHT:
                        sprite.right();
                        break;
                    case KeyEvent.VK_SPACE:
                    case KeyEvent.VK_UP:
                        sprite.jump();
                        break;
                }
            }
        });
        
        new Thread(new SpriteController(sprite, plansza, this)).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Segment s : plansza) {
            s.draw(g);
        }
        sprite.draw(g);
    }
    
}
