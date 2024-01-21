package entities;

import java.util.ArrayList;

public class Zona {
    private String nome;
    private ArrayList<OggettoGara> listaOggetti;
    private String tipoZona;

    public Zona(String nome, String tipoZona){
        this.nome = nome;
        this.tipoZona = tipoZona;
        listaOggetti = new ArrayList<>();
    }
    public void addOggetto(OggettoGara oggetto){
        listaOggetti.add(oggetto);
    }
    public String getTipoZona(){
        return tipoZona;
    }
    public ArrayList<OggettoGara> getListaOggetti(){
        return listaOggetti;
    }
    @Override
    public String toString(){
        return nome;
    }
}
