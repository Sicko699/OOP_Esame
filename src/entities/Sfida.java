package entities;
public class Sfida {
    public enum Difficolta{
        BASSA,
        MEDIA,
        ALTA
    }

    private String nomeSfida;
    private Zona zona;
    private OggettoGara oggetto;
    private Difficolta difficolta;
    private int puntiRicompensa;

    public Sfida(String nomeSfida, Zona zona, OggettoGara oggetto,
                 Difficolta difficolta, int puntiRicompensa){
        this.nomeSfida = nomeSfida;
        this.zona = zona;
        this.oggetto = oggetto;
        this.difficolta = difficolta;
        this.puntiRicompensa = puntiRicompensa;
    }

    public Zona getZona(){
        return zona;
    }

    public OggettoGara getOggetto(){
        return oggetto;
    }

    public Difficolta getDifficolta(){
        return difficolta;
    }

    public int getPuntiRicompensa(){
        return puntiRicompensa;
    }

    @Override
    public String toString(){
        return nomeSfida;
    }
}
