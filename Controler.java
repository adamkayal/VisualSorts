import javafx.scene.canvas.GraphicsContext;


public class Controler {

    static Model model;

    public Controler() {

        model = new Model();
    }

    void sort(int k, int i) {
        model.sort(k,i);
    }

    void draw(GraphicsContext context) {
        model.draw(context);
    }

    void createRect(GraphicsContext context, int n) {
        model.createRect(context,n);
    }

    boolean isSorted() {
        return model.isSorted();
    }

    void setSorted(boolean bool) {
        model.setSorted(bool);
    }
}