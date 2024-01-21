package controllers;

import entities.Zona;
import entities.Sfida;
import entities.OggettoGara;

import java.util.*;

public class GestoreGara {
    private static String analisiPerTipoZona(String tipoZona, List<Zona> zoneList) {
        int numeroOggetti = 0;
        for (Zona zona : zoneList) {
            numeroOggetti += zona.getListaOggetti().size();
        }
        List<Sfida> sfideDifficili = trovaSfidePiuDifficiliPerTipoZona(tipoZona);
        StringBuilder nomiSfideDifficili = new StringBuilder();
        for (Sfida sfida : sfideDifficili) {
            if (nomiSfideDifficili.length() > 0) nomiSfideDifficili.append(", "); // Se nomisfide ha gia testo metto la virgola e primo elemento, senno solo primo eleemnto
            nomiSfideDifficili.append(sfida.toString());
        }
        return String.format("Tipo Zona: %s, Oggetti: %d, Sfide più difficili: %s\n",
                tipoZona, numeroOggetti, nomiSfideDifficili);
    }

    private static List<Sfida> trovaSfidePiuDifficiliPerTipoZona(String tipoZona) {
        int maxPuntiRicompensa = Integer.MIN_VALUE;
        List<Sfida> sfideDifficili = new ArrayList<>();
        for (Sfida sfida : FileDataReader.listaSfide) {
            if (sfida.getZona().getTipoZona().equals(tipoZona)) {
                int puntiRicompensa = sfida.getPuntiRicompensa();
                if (puntiRicompensa > maxPuntiRicompensa) {
                    maxPuntiRicompensa = puntiRicompensa;
                    sfideDifficili.clear(); // Pulisce la lista perché abbiamo trovato una sfida con una ricompensa più alta
                    sfideDifficili.add(sfida);
                } else if (puntiRicompensa == maxPuntiRicompensa) {
                    sfideDifficili.add(sfida); // Aggiunge la sfida alla lista se ha la stessa ricompensa della massima trovata
                }
            }
        }
        return sfideDifficili;
    }

    public static String Task1() {
        StringBuilder risultato = new StringBuilder("Analisi per Tipo di Zona:\n");
        Map<String, List<Zona>> gruppoZone = new HashMap<>();
        for (Zona zona : FileDataReader.mappaZone.values()) {
            gruppoZone.computeIfAbsent(zona.getTipoZona(), k -> new ArrayList<>()).add(zona);
        }
        for (Map.Entry<String, List<Zona>> entry : gruppoZone.entrySet()) {
            risultato.append(analisiPerTipoZona(entry.getKey(), entry.getValue()));
        }
        return risultato.toString();
    }

    private static String calcoloPunteggioTotalePerZona(Zona zona) {
        int punteggioTotale = 0;
        for (OggettoGara oggetto : zona.getListaOggetti()) {
            punteggioTotale += oggetto.getPunteggio();
        }
        String tipologiaMaxPunteggio = trovaTipologiaMaxPunteggio(zona);
        return String.format("Nome Zona: %s, Punteggio Totale: %d, Categoria/e oggetto con punteggio massimo: %s\n",
                zona, punteggioTotale, tipologiaMaxPunteggio);
    }

    private static String trovaTipologiaMaxPunteggio(Zona zona) {
        Map<String, Integer> tipologiaPunteggio = new HashMap<>();
        String tipologiaMax = "Nessuna";
        int maxPunteggio = 0;
        for (OggettoGara oggetto : zona.getListaOggetti()) {
            int punteggioTotale = tipologiaPunteggio.getOrDefault(oggetto.getTipologia(), 0) + oggetto.getPunteggio();
            tipologiaPunteggio.put(oggetto.getTipologia(), punteggioTotale);
            if (punteggioTotale > maxPunteggio) {
                maxPunteggio = punteggioTotale;
                tipologiaMax = oggetto.getTipologia();
            }
        }
        return tipologiaMax;
    }

    public static String Task2() {
        StringBuilder result = new StringBuilder("Calcolo del Punteggio Totale per Zona:\n");
        for (Zona zona : FileDataReader.mappaZone.values()) {
            result.append(calcoloPunteggioTotalePerZona(zona));
        }
        return result.toString();
    }


    public static String Task3() {
        StringBuilder risultato = new StringBuilder("Elenco delle Sfide per Ogni Oggetto:\n");
        Map<String, List<String>> sfidePerOggetto = new HashMap<>();
        for (Sfida sfida : FileDataReader.listaSfide) {
            String nomeOggetto = sfida.getOggetto().toString();
            sfidePerOggetto.computeIfAbsent(nomeOggetto, k -> new ArrayList<>()).add(sfida.toString());
        }
        for (Map.Entry<String, List<String>> entry : sfidePerOggetto.entrySet()) {
            risultato.append(String.format("%s: %s\n", entry.getKey(), String.join(", ", entry.getValue())));
        }
        return risultato.toString();
    }
}