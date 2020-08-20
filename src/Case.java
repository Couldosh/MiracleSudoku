public class Case {
    private int valeur,line,column;

    public Case(int valeur,int line,int column) {
        this.valeur = valeur;
    }



    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public int getValeur() {
        return valeur;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "" +valeur ;
    }
}
