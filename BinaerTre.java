public class BinaerTre<T extends Comparable<T>> {

    private BinaerTreNode<T> rot;

    public BinaerTre() {
        this.rot = null;
    }

    public void leggTil(T verdi) {
        rot = leggTilRek(rot, verdi);
    }

    private BinaerTreNode<T> leggTilRek(BinaerTreNode<T> node, T verdi) {
        if (node == null) {
            return new BinaerTreNode<>(verdi);
        }

        if (verdi.compareTo(node.getElement()) < 0) {
            node.setVenstre(leggTilRek(node.getVenstre(), verdi));
        } else {
            node.setHogre(leggTilRek(node.getHogre(), verdi));
        }

        int venstreH = (node.getVenstre() == null) ? 0 : node.getVenstre().getHogdeU();
        int hogreH = (node.getHogre() == null) ? 0 : node.getHogre().getHogdeU();

        node.setHogdeU(1 + Math.max(venstreH, hogreH));
        return node;
    }

    public boolean erBalansert() {
        return erBalansert(rot);
    }

    private boolean erBalansert(BinaerTreNode<T> node) {
        if (node == null) return true;

        int venstreH = (node.getVenstre() == null) ? 0 : node.getVenstre().getHogdeU();
        int hogreH = (node.getHogre() == null) ? 0 : node.getHogre().getHogdeU();

        if (Math.abs(venstreH - hogreH) > 1) {
            return false;
        }

        return erBalansert(node.getVenstre()) && erBalansert(node.getHogre());
    }

    // Utskrift av verdier i inorden mellom to grenser
    public void skrivVerdier(T nedre, T ovre) {
        skrivVerdierRek(rot, nedre, ovre);
        System.out.println();
    }

    private void skrivVerdierRek(BinaerTreNode<T> t, T min, T maks) {
        if (t != null) {
            // Gå til venstre bare om det kan finnes verdi > min
            if (t.getElement().compareTo(min) > 0)
                skrivVerdierRek(t.getVenstre(), min, maks);

            if (t.getElement().compareTo(min) >= 0 &&
                t.getElement().compareTo(maks) <= 0) {
                System.out.print(t.getElement() + " ");
            }

            // Gå til høyre bare om det kan finnes verdi < maks
            if (t.getElement().compareTo(maks) < 0)
                skrivVerdierRek(t.getHogre(), min, maks);
        }
    }

    // For testing – returnerer inorden-streng
    public String toStringInorden() {
        return toStringInorden(rot).trim();
    }

    private String toStringInorden(BinaerTreNode<T> node) {
        if (node == null) return "";
        return toStringInorden(node.getVenstre()) +
               node.getElement() + " " +
               toStringInorden(node.getHogre());
    }
}

class BinaerTreNode<T> {
    private T element;
    private BinaerTreNode<T> venstre, hogre;
    private int hogdeU;

    public BinaerTreNode(T element) {
        this.element = element;
        this.venstre = null;
        this.hogre = null;
        this.hogdeU = 1;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public BinaerTreNode<T> getVenstre() {
        return venstre;
    }

    public void setVenstre(BinaerTreNode<T> venstre) {
        this.venstre = venstre;
    }

    public BinaerTreNode<T> getHogre() {
        return hogre;
    }

    public void setHogre(BinaerTreNode<T> hogre) {
        this.hogre = hogre;
    }

    public int getHogdeU() {
        return hogdeU;
    }

    public void setHogdeU(int hogdeU) {
        this.hogdeU = hogdeU;
    }
}
