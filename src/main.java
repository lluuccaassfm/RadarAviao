
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

public class main extends JFrame {

    private static final long serialVersionUID = 1L;
    private static int DELAY = 1000;
    BufferedImage bi;

    int tempo = 0;


    public void paint(Graphics g) {
        bi = new BufferedImage( desenho.tela.getLargura(), desenho.tela.getAltura(), BufferedImage.TYPE_INT_RGB);
        Graphics bg = bi.getGraphics();

        desenho d = new desenho();

        desenho.reta(bg, 0, -desenho.tela.getAltura()/2, 0, desenho.tela.getAltura()/2, Color.orange, 1);
        desenho.reta(bg, -desenho.tela.getLargura()/3, 0, desenho.tela.getLargura()/3, 0, Color.orange, 1);
        desenho.reta(bg, -desenho.tela.getLargura()/3, -desenho.tela.getAltura()/2, desenho.tela.getLargura()/3, desenho.tela.getAltura()/2, Color.orange, 1);
        desenho.reta(bg, desenho.tela.getLargura()/3, -desenho.tela.getAltura()/2, -desenho.tela.getLargura()/3, desenho.tela.getAltura()/2, Color.orange, 1);
        desenho.circulo(bg, 0,0,50,Color.orange,1);
        desenho.circulo(bg, 0,0,150,Color.orange,1);
        desenho.circulo(bg, 0,0,250,Color.orange,1);
        desenho.circulo(bg, 0,0,350,Color.orange,1);

        d.avioes(bg, tempo);


        g.drawImage(bi, 0, 0, this);
    }

    public void go() {
        TimerTask task = new TimerTask() {
            public void run() {
                tempo+=10;
                if(tempo>150) {
                    tempo=0;
                }
                repaint();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, DELAY);
    }


    public static void main(String args[]) {
        main frame = new main();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(desenho.tela.getLargura(), desenho.tela.getAltura());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.go();
    }
}