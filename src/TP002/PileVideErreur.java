package TP002;

class PileVideErreur extends Exception {
    public PileVideErreur(String message) {
        super(message);
    }
}

class PilePleineErreur extends Exception {
    public PilePleineErreur(String message) {
        super(message);
    }
}
