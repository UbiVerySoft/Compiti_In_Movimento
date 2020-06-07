import processing.core.PApplet;
import processing.core.PImage;

public class Movimento{
    PApplet proceesing;
    float cordX;
    float cordY;

    float cordYPrec;
    float cordXPrec;

    float grandezza=60;
    float larghezza=60;

    float x, y, speedX, speedY;
    PImage personaggio;
    Campo c;

    boolean stop=false;


    public Movimento(PApplet proceesing) {
        this.proceesing = proceesing;
        personaggio = proceesing.loadImage("personaggio.png");
        cordX = proceesing.width/2;
        cordY = proceesing.height/2;
        int pos[][]={{18,10},{18,11},{18,12},{18,13},{18,14},{18,15},{18,16},{18,17},{18,18},{17,11},
                {17,12},{17,13},{17,14},{17,15},{17,16},{17,17},{16,12},{16,13},{16,14},{16,15},{16,16},
                {15,13},{15,14},{15,15},{14,14},{12,19},{12,20},{12,21},{12,22},{12,8},{12,9},{12,7},{12,6},
                {7,12},{6,13},{5,14},{6,15},{7,16},{4,21},{4,22},{4,23},{4,24},{4,7},{4,6},{4,5},{4,4}};

        c = new Campo(this.proceesing, pos);
    }

    public void reset() {
        x = proceesing.width/2;
        y = proceesing.height/2;
    }

    public void show(float nose_x, float nose_y){
        if (nose_x>0 && nose_x<1270 ){
            cordX = (float) (1920*nose_x)/400;
        }

        //movimento asse orizzontale con cordinata X del naso
        if(nose_y>0 && nose_y<(712-grandezza)){
            cordY = (float) ((1080*nose_y)/680);
        }

            proceesing.clear();


        if (!stop) {
            proceesing.image(personaggio, cordX, cordY, grandezza, larghezza );
            stop=c.show(cordX, cordY, larghezza, grandezza);
            cordYPrec=cordY;
            cordXPrec=cordX;
        }else{
            proceesing.image(personaggio, cordXPrec, cordYPrec, grandezza, larghezza );
            stop=c.show(cordXPrec, cordYPrec, larghezza, grandezza);
        }
    }
}
