import processing.core.PApplet;
import processing.core.PImage;

import java.util.Random;

public class Proiettili {
    private PApplet processing;
    private PImage nemico;
    private float posx;
    private float posy;
    private  Random rnd = new Random();
    private  float changey;
    private  float changex;

    float grandezza=10;
    float larghezza=10;

    public Proiettili(PApplet prc, float x, float y, float l, float h){
        this.processing = prc;
        nemico = processing.loadImage("Proiettili.png");
        changey = rnd.nextInt(50)-25;
        changex = rnd.nextInt(50)-25;
        posx = x + (l/2);
        posy = y + (h/2);
    }

    void show() {
        processing.image(nemico, posx, posy, grandezza, larghezza);
        posy = posy + changey;
        posx = posx + changex;
    }
}
