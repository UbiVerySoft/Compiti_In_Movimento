import processing.core.PApplet;

import static javax.swing.SwingConstants.CENTER;

public class Button {
    int rectX, rectY;
    int rectSizeX = 700;
    int rectSizeY = 90;
    PApplet processing;

    public Button(PApplet prc) {
        this.processing = prc;
        rectX = (this.processing.width)-(this.processing.width/2)+(rectSizeX/2);
        rectY = (this.processing.height)-(rectSizeY*2);
    }

    public void show(){
        this.processing.fill(255,255,255);
        this.processing.stroke(255,255,255);
        this.processing.textAlign(CENTER);
        this.processing.text("CLICCA QUALSIASI TASTO PER INIZIARE", (float) (processing.width/2-(rectSizeX/2.5)), processing.height/2+(processing.height/(float)2.9));
    }
}
