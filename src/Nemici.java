import processing.core.PApplet;
import processing.core.PImage;

import java.util.Random;

public class Nemici {
    private PApplet processing;
    private PImage nemico;
    private final float posx = 450;
    private final float posy = 610;

    float grandezza=60;
    float larghezza=60;

    public Nemici(PApplet prc){
        this.processing = prc;
        nemico = processing.loadImage("nemico.png");
    }

    void show() {
        processing.image(nemico, posx, posy, grandezza, larghezza);
    }

}
