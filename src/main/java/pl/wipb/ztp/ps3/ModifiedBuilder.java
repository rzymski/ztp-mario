package pl.wipb.ztp.ps3;


import java.util.ArrayList;
import java.util.List;

public class ModifiedBuilder extends NormalBuilder{
    @Override
    public void addSegments(int liczba) {
        for (int i = 0; i < liczba; ++i) {
            Segment s = new SegmentBlock(x, y, "block3.png");
            plansza.add(s);
            x += TILESIZE;
        }
    }
}

//public class ModifiedBuilder extends Builder{
//    private final int TILESIZE = 32;
//    private List<Segment> plansza;
//    private int x=0, y=0;
//
//    public ModifiedBuilder(){
//        plansza = new ArrayList<>();
//    }
//
//    @Override
//    public void add(int liczba, char opcja) {
//        switch (opcja) {
//            case 'X':
//                addSegmenstAir(liczba);
//                break;
//            case 'A':
//                addSegmentsBlock(liczba);
//                break;
//            case 'B':
//                addSegmentsBlockV(liczba);
//                break;
//            case 'C':
//                addSegments(liczba);
//                break;
//            case 'G':
//                addSegmentsAnim(liczba);
//                break;
//        }
//    }
//    @Override
//    public void addSegments(int liczba) {
//        for (int i = 0; i < liczba; ++i) {
//            //Segment s = new Segment(x, y, "block3.png");
//            Segment s = new SegmentBlock(x, y, "block3.png");
//            plansza.add(s);
//            x += TILESIZE;
//        }
//    }
//    @Override
//    public void addSegmentsBlock(int liczba) {
//        for (int i = 0; i < liczba; ++i) {
//            Segment s = new SegmentBlock(x, y, "block1.png");
//            plansza.add(s);
//            x += TILESIZE;
//        }
//    }
//    @Override
//    public void addSegmentsBlockV(int liczba) {
//        for (int i = 0; i < liczba; ++i) {
//            Segment s = new SegmentBlockV(x, y, "block2.png");
//            plansza.add(s);
//            x += TILESIZE;
//        }
//    }
//    @Override
//    public void addSegmenstAir(int liczba) {
//        x += liczba * TILESIZE;
//    }
//    @Override
//    public void addSegmentsAnim(int liczba) {
//        for (int i = 0; i < liczba; ++i) {
//            Segment s = new SegmentAnim(x, y, "bonus.png", new int[]{0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 2, 2, 1, 1, 1, 0, 0});
//            plansza.add(s);
//            x += TILESIZE;
//        }
//    }
//    @Override
//    public void changeHight() {
//        y += TILESIZE;
//    }
//
//    @Override
//    public void setX(int x) {
//        this.x = x;
//    }
//
//    @Override
//    public void setY(int y) {
//        this.y = y;
//    }
//
//    @Override
//    public List<Segment> getResult() {
//        return plansza;
//    }
//}
