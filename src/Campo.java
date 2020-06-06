import processing.core.PApplet;

import java.util.Vector;

public class Campo {
    private static final int BORDIX = 30;
    private static final int BORDIY = 20;
    private int[][] campo;
    private PApplet processing;

    public Campo(PApplet prc, int[][] pos){
        this.processing = prc;
        campo = new int[BORDIY][BORDIX];

        for (int y = 0; y < BORDIY; y++) {
            for (int x = 0; x < BORDIX; x++) {
                if (y==0 || y==BORDIY-1 || x==0 || x==BORDIX-1) campo[y][x]=1;
                else campo[y][x]=0;
            }
        }
        for (int i = 0; i < pos.length; i++) campo[pos[i][0]][pos[i][1]]=1;
    }

    public boolean show(float x, float y, float l, float g) {
        for (int i = 0; i < BORDIY; i++) {
            for (int c = 0; c < BORDIX; c++) {
                switch (campo[i][c]) {
                    case 1:
                        processing.stroke(0);
                        processing.fill(0);
                        break;
                    default:
                        processing.stroke(245);
                        processing.fill(255);
                        break;
                }
                processing.rect(((processing.width / BORDIX) + 1) * c, ((processing.height / BORDIY) + 1) * i,
                        (processing.width / BORDIX) + 1, (processing.height / BORDIY) + 1);
            }
        }
        for (int i = 0; i < BORDIY; i++) {
            for (int c = 0; c < BORDIX; c++) {
                if (campo[i][c] == 1) {
                    if ((x + l) >= (((processing.width / BORDIX) + 1) * c) &&
                            x <= ((((processing.width / BORDIX) + 1) * c) + ((processing.width / BORDIX) + 1)) &&
                            (y + g) >= (((processing.height / BORDIY) + 1) * i) &&
                            y <= (((processing.height / BORDIY) + 1)) * i + (processing.height / BORDIY) + 1 ){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}