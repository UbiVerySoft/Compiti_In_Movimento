import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Vector;


public class Sketch extends PApplet {
    private PFont f;
    private Button b;

    private Nemici n;
    private Movimento m;
    private Vector<Monete> moneteVector;
    private int pagina = 0;
    private PImage imgPlayer;
    private PImage bg;
    private final int N_MONETE = 10;
    private DatagramSocket socket;
    private DatagramPacket packet;

    float nose_x = 0;
    float nose_y= 0;
    float r_wrist_x;
    float r_wrist_y;
    float l_wrist_x;
    float l_wrist_y;

    byte[] buf = new byte[24]; //Set your buffer size as desired

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
        m = new Movimento(this);
        n = new Nemici(this);

        moneteVector = new Vector<Monete>();
        for (int i = 0; i < N_MONETE; i++) moneteVector.add(new Monete(this));

        bg = loadImage("bg.jpg");
        imgPlayer = loadImage("Logo.png");

        background(0,0,0);

        try {
            socket = new DatagramSocket(4124); // Set your port here
        } catch (Exception e) {
            e.printStackTrace();
            println(e.getMessage());
        }
    }

    public void draw() {

        try {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(buf, buf.length, address, port);

            nose_x = Float.intBitsToFloat( (buf[0]& 0xFF) ^ (buf[1]& 0xFF)<<8 ^ (buf[2]& 0xFF)<<16 ^ (buf[3]& 0xFF)<<24 );
            nose_y = Float.intBitsToFloat( (buf[4]& 0xFF) ^ (buf[5]& 0xFF)<<8 ^ (buf[6]& 0xFF)<<16 ^ (buf[7]& 0xFF)<<24 );
            r_wrist_x = Float.intBitsToFloat( (buf[8]& 0xFF) ^ (buf[9]& 0xFF)<<8 ^ (buf[10]& 0xFF)<<16 ^ (buf[11]& 0xFF)<<24 );
            r_wrist_y = Float.intBitsToFloat( (buf[12]& 0xFF) ^ (buf[13]& 0xFF)<<8 ^ (buf[14]& 0xFF)<<16 ^ (buf[15]& 0xFF)<<24 );
            l_wrist_x = Float.intBitsToFloat( (buf[16]& 0xFF) ^ (buf[17]& 0xFF)<<8 ^ (buf[18]& 0xFF)<<16 ^ (buf[19]& 0xFF)<<24 );
            l_wrist_y = Float.intBitsToFloat( (buf[20]& 0xFF) ^ (buf[21]& 0xFF)<<8 ^ (buf[22]& 0xFF)<<16 ^ (buf[23]& 0xFF)<<24 );

            println("DATA:");
            println(nose_x);
            println(nose_y);
            println(r_wrist_x);
            println(r_wrist_y);
            println(l_wrist_x);
            println(l_wrist_y);
            println("DATA END\n");

        } catch (IOException e) {
            e.printStackTrace();
            println(e.getMessage());
        }

        if(pagina == 0){
            println("Pagina 0");
            background(bg);
            image(imgPlayer, 50, 50, 500, 500);
            for (Monete m: moneteVector) m.show();
            b.show();
        }
        if(pagina == 1){
            println("Pagina Game");
            m.show(nose_x, nose_y);
            n.show();
        }
    }

    public void keyPressed() {
        println("Click");
        pagina = 1;
    }
}
