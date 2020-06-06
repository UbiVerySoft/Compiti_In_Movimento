import processing.core.PApplet;
import processing.core.PImage;

public class Movimento{
    PApplet proceesing;
    float cordX;
    float cordY;

    float cordYPrec;
    float cordXPrec;

    float grandezza=100;
    float larghezza=200;

    float x, y, speedX, speedY;
    PImage personaggio;
    Campo c;

    boolean stop=false;


    public Movimento(PApplet proceesing) {
        this.proceesing = proceesing;
        personaggio = proceesing.loadImage("personaggio.png");
        cordX = proceesing.width/2;
        cordY = proceesing.height/2;
        int pos[][]={{15,10},{15,11},{15,12},{15,13},{15,14},{15,15},{15,16},{15,17},{15,18},{15,17},
                {14,18},{14,19},{13,19},{13,20},{12,20},{12,21},{11,21},{11,21},{5,10},{5,11},{15,1},{15,2},{15,2}};
        c = new Campo(this.proceesing, pos);
    }

    public void reset() {
        x = proceesing.width/2;
        y = proceesing.height/2;
    }

    public void show(float nose_x, float nose_y){
        if (nose_x>0 && nose_x<1270 ){
            cordX = (float) (1920*nose_x)/1270;
        }

        //movimento asse orizzontale con cordinata X del naso
        if(nose_y>0 && nose_y<(712-grandezza)){
            cordY = (float) ((1080*nose_y)/712);
        }

            proceesing.clear();
            stop=c.show(cordX, cordY, larghezza, grandezza);

        if (!stop) {
            proceesing.image(personaggio, cordX, cordY, grandezza, larghezza );
            cordYPrec=cordY;
            cordXPrec=cordX;
        }else{
            proceesing.image(personaggio, cordXPrec, cordYPrec, grandezza, larghezza );
        }
    }
}
