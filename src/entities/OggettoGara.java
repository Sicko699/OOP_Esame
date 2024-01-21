package entities;

public class OggettoGara {
    private String nome;
    private int punteggio;
    private String tipologia;

    public OggettoGara(String nome, int punteggio, String tipologia){
        this.nome = nome;
        this.punteggio = punteggio;
        this.tipologia = tipologia;
    }

    public int getPunteggio(){
        return punteggio;
    }

    public String getTipologia(){
        return tipologia;
    }

    @Override
    public String toString(){
        return nome;
    }
}
