package TP002;

public interface pile {
    public void empiler(Object element) throws PilePleineErreur;
    public void depiler() throws PileVideErreur;
    public boolean isPileVide();
    public Object sommet() throws PileVideErreur;
}