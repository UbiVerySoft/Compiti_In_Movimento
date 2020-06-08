import processing.core.PApplet;
import processing.core.PImage;

import java.util.Random;

public class Nemici {
    private PApplet processing;
    private PImage nemico;
    private Random rnd = new Random();
    private float posx;
    private float posy;
    private float changey;
    private float changex;

    float grandezza=60;
    float larghezza=60;

    public Nemici(PApplet prc){
        this.processing = prc;
        nemico = processing.loadImage("nemico.png");
        posx = 10;
        posy = 1000;
        changex =  (float) Math.random();
        changey =  (float) Math.random();

    }

    void show() {
        processing.image(nemico, posx, posy, grandezza, larghezza);
        posy = posy + changey;
        posx = posx + changex;
        if (posy > processing.height) {
            posy = (float)Math.random();
        }
        if(posx > processing.width){
            posx = (float)Math.random();
        }
    }

}
