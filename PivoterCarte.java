public class PivoterCarte {
    public static void pivoterCarte(Carte carte) {
        carte.setRotate((carte.getRotate() + 90) % 360);
    }
}
