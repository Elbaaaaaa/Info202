import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;

public class Controleur {

    @FXML 
    private Pane pane;

    public static Carte carte;

    public void initialize() {
        carte = new Carte(9999);
        pane.getChildren().add(carte);

        carte.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 2 && carte.getNumero() == 9999) {
                Carte carte8888 = new Carte(8888);
                carte8888.setTranslateX(carte.getTranslateX());
                carte8888.setTranslateY(carte.getTranslateY());
                pane.getChildren().add(carte8888);
                pane.getChildren().remove(carte);
            }
        });
    }
}
