import processing.core.PApplet;

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

    public void creaCampo(){
        for (int y = 0; y < BORDIY; y++){
            for (int x = 0; x < BORDIX; x++ ){
                switch (campo[y][x]){
                    case 1:
                        processing.stroke(0);
                        processing.fill(0);
                        break;
                    default:
                        processing.stroke(245);
                        processing.fill(255);
                        break;
                }
                processing.rect( ((processing.width/BORDIX)+1)*x, ((processing.height/BORDIY)+1)*y,
                        (processing.width/BORDIX)+1, (processing.height/BORDIY)+1);
            }
        }
    }

    public void RiempiMatrice(){

    }

    public void show() {
        creaCampo();
    }
}