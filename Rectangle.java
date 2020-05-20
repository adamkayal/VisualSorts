import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle {

    private double largeur, x, vx;
    private int y, hauteur;
    private Color color;

    public Rectangle(double x, double largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.x = x;
        this.y = 389-hauteur;
        this.color = Color.hsb(30, 0.75, 1);
    }

    public int getHauteur() {
        return this.hauteur;
    }

    public int getY() {
        return this.y;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void draw(GraphicsContext context) {
		context.setFill(color);
        context.fillRect(x, y, largeur, hauteur);
    }
}