import processing.core.PApplet;
import processing.core.PImage;

public class Movimento{
    PApplet proceesing;
    float cordX;
    float cordY;

    float grandezza=100;
    float larghezza=200;

    float x, y, speedX, speedY;
    PImage personaggio;

    public Movimento(PApplet proceesing) {
        this.proceesing = proceesing;
        personaggio = proceesing.loadImage("personaggio.png");
        cordX = proceesing.width/2;
        cordY = proceesing.height/2;
    }

    public void reset() {
        x = proceesing.width/2;
        y = proceesing.height/2;
    }

    public void show(float nose_x, float nose_y){
        if (nose_x>0 && nose_x<640){
            cordX= (1920*nose_x)/840;
            proceesing.clear();
            proceesing.image(personaggio, cordX, cordY, grandezza, larghezza );
        }

        //movimento asse orizzontale con cordinata X del naso
        if(nose_y>0 && nose_y<(400-grandezza)){
            cordY= (float) (((1080*nose_y)/470)*9.81);
            proceesing.clear();
            proceesing.image(personaggio, cordX, cordY, grandezza, larghezza );
        }
    }
}
