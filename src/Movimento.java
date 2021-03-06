//importazione librerie
import processing.core.PApplet;
import processing.core.PImage;

public class Movimento {
    // classe per gestire il movimento

    //dichiarazione variabili
    private PApplet proceesing;
    private float cordX;
    private float cordY;

    private float cordYPrec;
    private float cordXPrec;

    private float cordXPrec2;
    private float cordYPrec2;

    private float grandezza = 60;
    private float larghezza = 60;

    private int cont = 0;
    private float sommaX = 0;
    private float sommaY = 0;

    private int discesa = 0;

    private float x, y, speedX, speedY;
    private PImage personaggio;
    private Campo c;

    private boolean stop = false;

    private boolean sx = false;
    private boolean dx = false;
    private boolean up = false;
    private boolean dw = false;


    public Movimento(PApplet proceesing) {

        //inizializzazione variabili
        this.proceesing = proceesing;
        personaggio = proceesing.loadImage("personaggio.png");
        cordYPrec = proceesing.height / 2;
        cordXPrec = proceesing.width / 2;
        cordX = proceesing.width / 2;
        cordY = proceesing.height / 2;
        cordXPrec2 = proceesing.width / 2;
        cordYPrec2 = proceesing.height / 2;

        //dichiarazione campo da gioco
        int pos[][] = {{18, 10}, {18, 11}, {18, 12}, {18, 13}, {18, 14}, {18, 15}, {18, 16}, {18, 17}, {18, 18}, {17, 11},
                {17, 12}, {17, 13}, {17, 14}, {17, 15}, {17, 16}, {17, 17}, {16, 12}, {16, 13}, {16, 14}, {16, 15}, {16, 16},
                {15, 13}, {15, 14}, {15, 15}, {14, 14}, {12, 19}, {12, 20}, {12, 21}, {12, 22}, {12, 8}, {12, 9}, {12, 7}, {12, 6},
                {7, 12}, {6, 13}, {5, 14}, {6, 15}, {7, 16}, {4, 21}, {4, 22}, {4, 23}, {4, 24}, {4, 7}, {4, 6}, {4, 5}, {4, 4}};
        c = new Campo(this.proceesing, pos);
    }

    public void reset() {

        x = proceesing.width / 2;
        y = proceesing.height / 2;
    }

    public void show(float nose_x, float nose_y) {

        //gestione del movimento con la media di due valori letti da open pose
        if (cont < 2) {
            sommaX += nose_x;
            sommaY += nose_y;
            cont++;
        } else {

            //movimento asse orizzontale con cordinata x del naso
            if (nose_x > 0 && nose_x < 620) {

                //controllo se il personaggio è bloccato in un blocco arrivando da sinistra e se la cordX è minore di quella prec.
                if (sx && cordX < cordXPrec2) {
                    sx = false;

                    //controllo se il personaggio è bloccato in un blocco arrivando da destra e se la cordX è maggiore di quella prec.
                } else if (dx && cordX > cordXPrec2) {

                    //cordX = (float) (1920*nose_x)/620;
                    dx = false;
                } else {

                    //se il personaggio non è bloccato in nessun blocco calcolo la coordinata
                    cordX = (float) (1920 * (sommaX / cont)) / 620;
                }
            }

            //movimento asse verticale con cordinata y del naso
            if (nose_y > 0 && nose_y < (470 - grandezza)) {

                //controllo se il personaggio è bloccato in un blocco arrivando dall'alto e se la cordy è maggiore di quella prec.
                if (up && cordY > cordYPrec2) {

                    //cordY = (float) ((1080*nose_y)/360);
                    up = false;

                    //controllo se il personaggio è bloccato in un blocco arrivando dal basso e se la cordY è minore di quella prec.
                } else if (dw && cordY < cordYPrec2) {

                    //cordY = (float) ((1080*nose_y)/360);
                    dw = false;
                } else {
                    cordY = ((1080 * nose_y) / 470);
                    //controllo se il personaggio sta cadendo
                    /*if(discesa==0 ){

                        //no --> calcolo la coordinata

                    }else{

                        //si-->posiziono il personaggio a terra
                        cordY=proceesing.height-larghezza;;
                        discesa=0;
                    }
                    System.out.println(cordY);
                    proceesing.clear();

                    //controllo se il personaggio è fermo
                    if(cordY-cordYPrec>5 || cordY-cordYPrec<-5|| cordX-cordXPrec>5 || cordX-cordXPrec<-5 || cordY==cordYPrec ){
                        proceesing.image(personaggio, cordX, cordY, grandezza, larghezza );
                        cordYPrec=cordY;
                    }else {
                        discesa=1;
                        cordY=proceesing.height-larghezza;
                        proceesing.image(personaggio, cordX, cordY, grandezza, larghezza );
                        cordYPrec=cordY;

                    }
                }*/
                }

                // controllo se il personaggio è bloccato in un blocco
                if (cordX < cordXPrec2 && stop) {

                    //arriva da sinistra
                    sx = true;
                } else if (cordX > cordXPrec2 && stop) {

                    //arriva da destra
                    dx = true;
                } else if (cordY < cordYPrec2 && stop) {

                    //arriva da sopra
                    up = true;
                } else if (cordY > cordYPrec2 && stop) {

                    //arriva da sotto
                    dw = true;
                }

                proceesing.clear();
                stop = c.show(cordX, cordY, larghezza, grandezza);    // chiamo la funzione che verifica se il personaggio è vicino a un blocco

                if (!stop) {

                    // stop== false --> personaggio non vicino a un blocco

                    //stampo il personaggio nella posizione calcolata dalla nostra equazione
                    proceesing.image(personaggio, cordX, cordY, grandezza, larghezza);
                    cordYPrec = cordY;
                    cordXPrec = cordX;

                } else {

                    // stop== true --> personaggio vicino a un blocco

                    //stampo il personaggio enlla coordinata prec.
                    proceesing.delay(1);
                    proceesing.image(personaggio, cordXPrec, cordYPrec, grandezza, larghezza);
                }

                cordYPrec2 = cordY;
                cordXPrec2 = cordX;

                //assegno a zero le variabili che gestiscono il campionamneto
                cont = 0;
                sommaX = sommaY = 0;
            }
        }
    }
}