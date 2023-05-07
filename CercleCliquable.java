import javafx.geometry.Point2D;

public class CercleCliquable {
    private int x, y, rayon, numero;

    public CercleCliquable(int x, int y, int rayon, int numero) {
        this.x = x;
        this.y = y;
        this.rayon = rayon;
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public boolean contains(Point2D point) {
        double distance = Math.sqrt(Math.pow(point.getX() - x, 2) + Math.pow(point.getY() - y, 2));
        return distance <= rayon;
    }
    
}
