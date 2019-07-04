
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

public class desenho {

    private int distF = 1000;
    private int distObservador = 200;

    public static void ponto(Graphics g, int x, int y, Color cor, int tamanho) {
        g.setColor(cor);

        x = x + tela.getLargura()/2;
        y = -y +tela.getAltura()/2;

        g.fillRect(x, y, tamanho, tamanho);

    }

    public static void reta(Graphics g, int x1, int y1, int x2, int y2, Color cor, int tamanho) {
        int d = 0;

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int dx2 = 2 * dx;
        int dy2 = 2 * dy;

        int ix = x1 < x2 ? 1 : -1;
        int iy = y1 < y2 ? 1 : -1;

        int x = x1;
        int y = y1;

        if (dx >= dy) {
            while (true) {
                desenho.ponto(g, x, y, cor, tamanho);
                if (x == x2)
                    break;
                x += ix;
                d += dy2;
                if (d > dx) {
                    y += iy;
                    d -= dx2;
                }
            }
        } else {
            while (true) {
                desenho.ponto(g, x, y, cor, tamanho);
                if (y == y2)
                    break;
                y += iy;
                d += dx2;
                if (d > dy) {
                    x += ix;
                    d -= dy2;
                }
            }
        }
    }

    public static void circulo(Graphics g, int xcentro, int ycentro, int r, Color cor, int tamanho) {

        int d = (5 - r * 4)/4;
        int x = 0;
        int y = r;

        while (x <= y) {
            desenho.ponto(g,xcentro + x, ycentro + y, cor , tamanho);
            desenho.ponto(g,xcentro + x, ycentro - y, cor , tamanho);
            desenho.ponto(g,xcentro - x, ycentro + y, cor , tamanho);
            desenho.ponto(g,xcentro - x, ycentro - y, cor , tamanho);
            desenho.ponto(g,xcentro + y, ycentro + x, cor , tamanho);
            desenho.ponto(g,xcentro + y, ycentro - x, cor , tamanho);
            desenho.ponto(g,xcentro - y, ycentro + x, cor , tamanho);
            desenho.ponto(g,xcentro - y, ycentro - x, cor , tamanho);

            if (d < 0) {
                d += 2 * x + 1;
            } else {
                d += 2 * (x - y) + 1;
                y--;
            }
            x++;
        } ;

    }
    public static void circuloCheio(Graphics g, int xcentro, int ycentro, int r, Color cor) {

        int d = (5 - r * 4)/4;
        int x = 0;
        int y = r;

        while (x <= y) {
            desenho.reta(g,xcentro - x, ycentro + y,xcentro,ycentro, cor , 2);
            desenho.reta(g,xcentro + x, ycentro + y,xcentro,ycentro, cor , 2);
            desenho.reta(g,xcentro + x, ycentro - y,xcentro,ycentro, cor , 2);
            desenho.reta(g,xcentro - x, ycentro + y,xcentro,ycentro, cor , 2);
            desenho.reta(g,xcentro - x, ycentro - y,xcentro,ycentro, cor , 2);
            desenho.reta(g,xcentro + y, ycentro + x,xcentro,ycentro, cor , 2);
            desenho.reta(g,xcentro + y, ycentro - x,xcentro,ycentro, cor , 2);
            desenho.reta(g,xcentro - y, ycentro + x,xcentro,ycentro, cor , 2);
            desenho.reta(g,xcentro - y, ycentro - x,xcentro,ycentro, cor , 2);

            if (d < 0) {
                d += 2 * x + 1;
            } else {
                d += 2 * (x - y) + 1;
                y--;
            }
            x++;
        } ;
    }

    public static void projetar(Graphics g, int x, int y, int z, int f, Color cor, int tamanho) {
        int x1 = (f/z)*x ;
        int y1 = (f/z)*y;
        ponto(g, x1, y1, cor, tamanho);
    }

    private double projecaoX(double x){
        double newx = (this.distObservador / this.distF) * x;

        try{
            return  newx;
        } catch(NumberFormatException exception) {
            return  0;
        }
    }

    private double projecaoY (double y){
        double newy = (this.distObservador / this.distF) * y;

        try{
            return newy;
        } catch(NumberFormatException exception) {
            return  0;
        }
    }

    public static void avioes(Graphics g, int t) {

        ArrayList<dadosModel> dadosModel = varreduras.avioa(t);
        ArrayList<dadosModel> rastro = varreduras.avioa(t-10);

        for(dadosModel p : dadosModel) {

            int x1 = (int) p.x;
            int y1 = (int) p.y;

            for(dadosModel r : rastro) {
                if(p.voo.equals(r.voo)) {
                    //desenha o rastro
                    reta(g, x1/50, y1/50, (int)r.x/50, (int)r.y/50, Color.cyan, 1);

                    //desenha o triangulo que representa o avião
                    triangulo(g, x1/50, y1/50, Color.green, 2);

                    //desenha cod do voo
                    g.setColor(Color.white);
                    g.drawString(p.voo, x1/50+ tela.getLargura()/2, -y1/50+tela.getAltura()/2);
                    g.drawString("X; " + String.valueOf(p.x), x1/50+ tela.getLargura()/2, -y1/50+tela.getAltura()/2 +10);
                    g.drawString("Y; " + String.valueOf(p.y), x1/50+ tela.getLargura()/2, -y1/50+tela.getAltura()/2 +20);
                }
            }
        }
    }

    private static void triangulo(Graphics g, int x, int y, Color cor, int tamanho){
        reta(g, x, y+10, x-8, y-10, cor, tamanho);
        reta(g, x, y+10, x+8, y-10, cor, tamanho);
        reta(g, x-8, y-10, x+8, y-10, cor, tamanho);
    }

    static class tela{
        static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        public static  int getLargura() {return (int)screenSize.getWidth();}
        public static  int getAltura()  {return (int)screenSize.getHeight();}
    }
}



