import java.io.Serializable;
import java.io.File;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

public class Carte extends ImageView implements Serializable {

    private static final String IMAGE_DIRECTORY = "photo";
    private static final String IMAGE_PREFIX = "Carte_";
    private static final String IMAGE_SUFFIX = ".png";
    private static final char SEPARATOR_CHAR = File.separatorChar;
    private static final Integer IMAGE_HEIGHT = 500;

    private final int numero;
    private double dragX, dragY;
    private ArrayList<CercleCliquable> listeCercles = new ArrayList<>();
    private boolean carteOuverte = false;
    private boolean carteCliquee = false;
    private Carte carteSuivante;
    private int nombreClics = 0;


    public Carte(int numero) {
        this.numero = numero;

        String chemin = IMAGE_DIRECTORY + SEPARATOR_CHAR + IMAGE_PREFIX + this.numero + IMAGE_SUFFIX;

        // Declare and initialize carte8888 before adding a CercleCliquable to it
        CercleCliquable carte8888 = new CercleCliquable(52, 288, 17, 15);
        ajouterCercle(carte8888);

        CercleCliquable carte111 = new CercleCliquable(140, 291, 17, 111);
        ajouterCercle(carte111);

        CercleCliquable carte24 = new CercleCliquable(98, 188, 17, 24);
        ajouterCercle(carte24);

        CercleCliquable carte55 = new CercleCliquable(210, 143, 17, 55);
        ajouterCercle(carte55);

        CercleCliquable carte6 = new CercleCliquable(214, 276, 17, 6);
        ajouterCercle(carte6);

        CercleCliquable carte777 = new CercleCliquable(182, 338, 17, 777);
        ajouterCercle(carte777);

        CercleCliquable carte8 = new CercleCliquable(146, 208, 17, 8);
        ajouterCercle(carte8);

        CercleCliquable carte222 = new CercleCliquable(146, 208, 17, 222);
        ajouterCercle(carte222);

        CercleCliquable carte9 = new CercleCliquable(215, 265, 17, 9);
        ajouterCercle(carte9);

        // Add the image and set its properties
        setImage(new Image(chemin));
        setPreserveRatio(true);
        setFitHeight(IMAGE_HEIGHT);

        // Add the mouse event handlers for dragging the card
        setOnMousePressed((MouseEvent event) -> {
            dragX = event.getSceneX() - getTranslateX();
            dragY = event.getSceneY() - getTranslateY();
        });
        setOnMouseDragged((MouseEvent event) -> {
            setTranslateX(event.getSceneX() - dragX);
            setTranslateY(event.getSceneY() - dragY);
        });

        // Add the mouse event handler for clicking on the circles
        setOnMouseClicked(e -> clickDansLeCercle(e));
        if(numero == 6){
        setOnMouseClicked(e -> {pivoter();});
        }
        

    }

    public void pivoter() {
        if (numero == 6) {
            nombreClics++;
            double angle = nombreClics * 90.0;
            setRotate(angle);
        }
    }
    

    public void ajouterCercle(CercleCliquable c) {
        listeCercles.add(c);
    }

    public int getNumero() {
        return numero;
    }

    public boolean isCarteOuverte() {
        return carteOuverte;
    }

    public boolean isCarteCliquee() {
        return carteCliquee;
    }

    public void setCarteOuverte(boolean carteOuverte) {
        this.carteOuverte = carteOuverte;
    }

    public void clickDansLeCercle(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        System.out.println("x : " + x + ", y : " + y);
        for (CercleCliquable cercle : listeCercles) {
            if (cercle.contains(new Point2D(x, y))) {
                int numeroClique = cercle.getNumero();
                Carte nouvelleCarte = null;
                for (Node node : ((Pane) getParent()).getChildren()) {
                    if (node instanceof Carte && ((Carte) node).getNumero() == numeroClique) {
                        nouvelleCarte = (Carte) node;
                        break;
                    }
                }
                if (nouvelleCarte == null) {
                    nouvelleCarte = new Carte(numeroClique);
                    nouvelleCarte.setTranslateX(this.getTranslateX() + 50);
                    nouvelleCarte.setTranslateY(this.getTranslateY() + 50);
                    ((Pane) getParent()).getChildren().add(nouvelleCarte);
                }
                
                nouvelleCarte.setCarteOuverte(true);
                this.carteCliquee = true;
            }
        }
    }
}                