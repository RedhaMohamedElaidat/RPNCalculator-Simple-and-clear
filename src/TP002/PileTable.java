package TP002;

public class PileTable implements pile {
    private int taillemax;
    private int sommet;
    private Object[] tableau;

    public PileTable(int max) {
        taillemax = max;
        sommet = -1;
        tableau = new Object[taillemax];
    }

    @Override
    public void empiler(Object element) throws PilePleineErreur {
        if (sommet == taillemax - 1) {
            throw new PilePleineErreur("Erreur : la pile est pleine");
        }
        sommet++;
        tableau[sommet] = element;
    }

    @Override
    public void depiler() throws PileVideErreur {
        if (isPileVide()) {
            throw new PileVideErreur("Erreur : la pile est vide");
        }
        sommet--;
    }

    @Override
    public boolean isPileVide() {
        return (sommet == -1);
    }

    @Override
    public Object sommet() throws PileVideErreur {
        if (isPileVide()) {
            throw new PileVideErreur("Erreur : la pile est vide");
        }
        return tableau[sommet];
    }
}
