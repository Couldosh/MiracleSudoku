



public class SudokuGrid {

    public Case[][] grid = new Case[9][9];
    public int compteur = 0;

    public SudokuGrid(int[][] entryGrid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = new Case(entryGrid[i][j],i,j);
            }
        }
    }


    public boolean isNotInLine(int valeur, int line){
        for (int j = 0; j < 9; j++) {
            if (grid[line][j].getValeur()==valeur)return false;
        }
        return true;

    }

    public boolean isNotInColumn(int valeur,int column){
        for (int j = 0; j < 9; j++) {
            if (grid[j][column].getValeur()==valeur)return false;
        }
        return true;
    }

    public boolean isNotInBox(int valeur,int x, int y){
        int startX = (x/3)*3;
        int startY = (y/3)*3;
        for (int i = startX; i < startX+3; i++) {
            for (int j = startY; j < startY+3; j++) {
                if (grid[i][j].getValeur()==valeur)return false;
            }
        }
        return true;
    }

    public boolean isNotInKingMove(int valeur, int x,int y){
        for (int i = x-1; i <= x+1 ; i++) {
            for (int j = y-1; j <= y+1; j++) {
                if (i>=0 && i<=8 && j>=0 && j<=8 && i!=x && j!=y){
                    if (grid[i][j].getValeur()==valeur)return false;
                }
            }
        }
        return true;
    }

    public boolean isNotInKnightMove(int valeur,int x,int y){
        int[] possibleMovementX = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int[] possibleMovementY = { 1, 2, 2, 1, -1, -2, -2, -1 };
        for (int i = 0; i < 8; i++) {
            int movementX = x+possibleMovementX[i], movementY = y+possibleMovementY[i];
            if (movementX >= 0 && movementY >= 0 && movementX <= 8 && movementY <= 8) {
                if (grid[movementX][movementY].getValeur() == valeur) return false;
            }
        }
        return true;
    }

    public boolean isNotConsecutiveNumber(int valeur,int x,int y){
        int[] possibleMovementX = {-1,0,0,1};
        int[] possibleMovementY = {0,-1,1,0};
        for (int i = 0; i < 4; i++) {
            int movementX = x+possibleMovementX[i], movementY = y+possibleMovementY[i];
            if (movementX >= 0 && movementY >= 0 && movementX <= 8 && movementY <= 8) {
                if (valeur == 1){
                    if(grid[movementX][movementY].getValeur() == valeur+1)return false;
                }else if (grid[movementX][movementY].getValeur() == valeur-1 || grid[movementX][movementY].getValeur() == valeur+1) return false;
            }
        }
        return true;
    }


    public boolean estValeurPossible(int valeur,int line,int column) {
        return  isNotInColumn(valeur,column)
                && isNotInLine(valeur,line)
                && isNotInBox(valeur,line,column)
                && isNotInKingMove(valeur, line, column)
                && isNotInKnightMove(valeur, line, column)
                && isNotConsecutiveNumber(valeur, line, column);
    }



    public void affiche() {
        for (int l=0; l<9; l++) {
            for (int c=0; c<9; c++) {
                if (grid[l][c].getValeur()!=0) System.out.print(grid[l][c]+" ");
                else System.out.print(' ');
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public  boolean trouveSolution(int ligne,int colonne) {

        int ligneSuivante;
        int colonneSuivante;
        if (colonne==8) { ligneSuivante = ligne+1; colonneSuivante=0; }
        else            { ligneSuivante = ligne;   colonneSuivante = colonne+1; }

        if (ligne==9) { //Ligne == 9 => Fin
            affiche();
            return true;
        }

        if (grid[ligne][colonne].getValeur()!=0) { //Si case déjà remplie, on passe à la suivante
            return trouveSolution(ligneSuivante,colonneSuivante);
        } else {
            for (int valeur=1; valeur<10; valeur++) { //Essai de toutes les valeurs possible
                if (!estValeurPossible(valeur,ligne,colonne)) continue; //Si la valeur n'est pas possible, alors on continue
                grid[ligne][colonne].setValeur(valeur); //Sinon on utilise cette valeur
                boolean correct = trouveSolution(ligneSuivante,colonneSuivante);
                if (correct) return true; // Si la suite est juste, alors cette valeur était bonne
            }
            grid[ligne][colonne].setValeur(0); // c'était une case "vide", on la remet
            return false; // Je n'ai pas trouvé de valeur correcte
        }
    }

}
