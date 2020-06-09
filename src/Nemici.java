import processing.core.PApplet;
import processing.core.PImage;

import java.util.Random;
import java.util.Vector;

public class Nemici {
    // classe per la gestione dei nemici

    //dichiarazione variabili
    private PApplet processing;
    private PImage nemico;
    private float posx = 450;
    private float posy = 610;

    private float grandezza=60;
    private float larghezza=60;
    private Vector<Proiettili> p;

    public Nemici(PApplet prc){

        this.processing = prc;
        nemico = processing.loadImage("nemico.png");
        p = new Vector<Proiettili>();
    }

    public void addProiettili(){

        p.add(new Proiettili(processing, posx, posy, grandezza, larghezza));
    }

    public void rmProiettili(){

        p.remove(0);
    }

    void show() {

        //mostro il nemico e chiamo la funzione che mi mostra i proiettili
        if (p.size()<10) addProiettili();
        else rmProiettili();
        processing.image(nemico, posx, posy, grandezza, larghezza);
        for (Proiettili pr :p) pr.show();
    }
}
