package TP002;

import java.util.Scanner;

public class PolonaiseInvers {
    private PileTable pile;

    public PolonaiseInvers(int taille) {
        pile = new PileTable(taille);
    }

    public double evaluer(String expression) throws PileVideErreur, PilePleineErreur {
        String[] tokens = expression.split(" ");

        for (String token : tokens) {
            if (estNombre(token)) {
                pile.empiler(Double.parseDouble(token)); 
            } else if (estOperateur(token)) {
                if (pile.isPileVide()) {
                    throw new PileVideErreur("Erreur : Expression invalide (pas assez d'opérandes)");
                }
                double b = (double) pile.sommet();
                pile.depiler();

                if (pile.isPileVide()) {
                    throw new PileVideErreur("Erreur : Expression invalide (pas assez d'opérandes)");
                }
                double a = (double) pile.sommet();
                pile.depiler();

                double result = calculer(a, b, token);
                pile.empiler(result);
            } else {
                throw new IllegalArgumentException("Erreur : Caractère invalide.");
            }
        }

        if (pile.isPileVide()) {
            throw new PileVideErreur("Erreur : Expression incorrecte.");
        }

        double resultatFinal = (double) pile.sommet();
        pile.depiler();

        if (!pile.isPileVide()) {
            throw new IllegalArgumentException("Erreur : Trop d'opérandes.");
        }

        return resultatFinal;
    }

    private boolean estNombre(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean estOperateur(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private double calculer(double a, double b, String operateur) {
        switch (operateur) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b == 0) {
                    throw new ArithmeticException("Erreur : Division par zéro.");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Erreur : Opérateur inconnu.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez une expression en notation polonaise inverse :");
        String expression = scanner.nextLine();
        
        PolonaiseInvers calc = new PolonaiseInvers(50);
        try {
            double resultat = calc.evaluer(expression);
            System.out.println("Résultat : " + resultat);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}
