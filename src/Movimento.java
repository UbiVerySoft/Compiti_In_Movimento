
import processing.core.PApplet;
import processing.core.PImage;

public class Movimento{
    PApplet proceesing;
    float cordX;
    float cordY;

    float cordYPrec;
    float cordXPrec;

    float cordXPrec2;
    float cordYPrec2;

    float grandezza=60;
    float larghezza=60;

    int cont=0;
    float sommaX=0;
    float sommaY=0;

    int discesa=0;


    float x, y, speedX, speedY;
    PImage personaggio;
    Campo c;

    boolean stop=false;

    boolean sx=false;
    boolean dx=false;
    boolean up=false;
    boolean dw=false;


    public Movimento(PApplet proceesing) {
        this.proceesing = proceesing;
        personaggio = proceesing.loadImage("personaggio.png");
        cordYPrec = proceesing.height/2;
        cordXPrec = proceesing.width/2;
        cordX = proceesing.width/2;
        cordY = proceesing.height/2;
        cordXPrec2=proceesing.width/2;
        cordYPrec2=proceesing.height/2;
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
        if(cont<2){
            sommaX+=nose_x;
            sommaY+=nose_y;
            cont++;
        }else{
            if (nose_x>0 && nose_x<620 ){
                if(sx && cordX<cordXPrec2 ){
                    //cordX = (float) (1920*nose_x)/620;
                    sx=false;
                }else if(dx && cordX>cordXPrec2){
                    //cordX = (float) (1920*nose_x)/620;
                    dx=false;
                }else {
                    cordX = (float) (1920*(sommaX/cont))/620;
                }
            }
            //movimento asse orizzontale con cordinata X del naso
            if(nose_y>0 && nose_y<(360-grandezza)){
                if(up && cordY>cordYPrec2 ){
                    //cordY = (float) ((1080*nose_y)/360);
                    up=false;
                }else if(dw && cordY<cordYPrec2){
                    //cordY = (float) ((1080*nose_y)/360);
                    dw=false;
                }else {
                    if(discesa==0 ){
                        cordY=((1080*nose_y)/470);
                    }else{
                        cordY=proceesing.height-larghezza;;
                        discesa=0;
                    }
                    System.out.println(cordY);
                    proceesing.clear();
                    if(cordY-cordYPrec>5 || cordY-cordYPrec<-5|| cordX-cordXPrec>5 || cordX-cordXPrec<-5 || cordY==cordYPrec ){
                        proceesing.image(personaggio, cordX, cordY, grandezza, larghezza );
                        cordYPrec=cordY;
                    }else {
                        discesa=1;
                        cordY=proceesing.height-larghezza;
                        proceesing.image(personaggio, cordX, cordY, grandezza, larghezza );
                        cordYPrec=cordY;

                    }
                }
            }

            if(cordX < cordXPrec2 && stop){
                //arriva da sinistra
                sx=true;
            }else if(cordX>cordXPrec2 && stop){
                //arriva da destra
                dx=true;
            }else if(cordY < cordYPrec2 && stop){
                //arriva da sopra
                up=true;
            }else  if(cordY>cordYPrec2 && stop){
                //arriva da sotto
                dw=true;
            }

            proceesing.clear();
            stop=c.show(cordX, cordY, larghezza, grandezza);

            if (!stop) {
                proceesing.image(personaggio, cordX, cordY, grandezza, larghezza );
                cordYPrec=cordY;
                cordXPrec=cordX;

            }else{
            /*for (int i=0; i<1000000000; i++ ){
                continue;
            }
            for (int i=0; i<1000000000; i++ ){
                continue;
            }*/
                proceesing.delay(1);
                proceesing.image(personaggio, cordXPrec, cordYPrec, grandezza, larghezza );
            }
            cordYPrec2=cordY;
            cordXPrec2=cordX;
            cont= 0;
            sommaX=sommaY=0;
        }
    }
}