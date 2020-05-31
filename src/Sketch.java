import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import java.util.Vector;


public class Sketch extends PApplet {
    private PFont f;
    private Button b;
    private Campo c;
    private Vector<Monete> moneteVector;
    private int pagina = 0;
    private PImage imgPlayer;
    private PImage bg;
    private final int N_MONETE = 10;

    public static void main(String[] args) {
        PApplet.main("Sketch");
    }

    public void settings() {
        fullScreen();
    }

    public void setup() {
        f = createFont("stocky.ttf", 24);
        textFont(f);
        b = new Button(this);
        int pos[][]={{15,10},{15,11},{15,12},{15,13},{15,14},{15,15},{15,16},{15,17},{15,18},{15,17},
                {14,18},{14,19},{13,19},{13,20},{12,20},{12,21},{11,21},{11,21},{5,10},{5,11},{15,1},{15,2},{15,2}};
        c = new Campo(this, pos);
        moneteVector = new Vector<Monete>();
        for (int i = 0; i < N_MONETE; i++) moneteVector.add(new Monete(this));
        bg = loadImage("bg.jpg");
        imgPlayer = loadImage("personaggio.png");
    }

    public void draw() {
        if(pagina == 0){
            println("Pagina 0");
            background(bg);
            //image(imgPlayer, 50, 50);
            //for (Monete m: moneteVector) m.show();
            b.show();
        }
        if(pagina == 1){
            println("Pagina Game");
            c.show();
        }
    }

    public void keyPressed() {
        println("Click");
        pagina = 1;
    }
}
