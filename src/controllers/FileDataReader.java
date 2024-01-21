package controllers;

import entities.OggettoGara;
import entities.Sfida;
import entities.Zona;
import interfaces.DataReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileDataReader implements DataReader {
    static HashMap<String, Zona> mappaZone = new HashMap<>();
    private static HashMap<String, OggettoGara> mappaOggetti = new HashMap<>();
    static ArrayList<Sfida> listaSfide = new ArrayList<>();

    private static String[] splitSezione(String sezione) {
        return sezione.split("\n");
    }

    public void leggiZone(String sezioneZone) {
        String[] listaZone = splitSezione(sezioneZone);
        for (String zona : listaZone) {
            try {
                String[] argomenti = zona.split(",");
                Zona corrente = new Zona(argomenti[0], argomenti[1]);
                mappaZone.put(argomenti[0], corrente);
            } catch (ArrayIndexOutOfBoundsException ignored) {}
        }
    }

    public void leggiOggettiGara(String sezioneOggetti) {
        String[] listaOggetti = splitSezione(sezioneOggetti);
        for (String oggetto : listaOggetti) {
            try {
                String[] argomenti = oggetto.split(",");
                OggettoGara corrente = new OggettoGara(argomenti[0], Integer.parseInt(argomenti[2]), argomenti[3]);
                mappaOggetti.put(argomenti[0], corrente);

                Zona zona = mappaZone.get(argomenti[1]);
                if (zona != null) {
                    zona.addOggetto(corrente);
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {}
        }
    }

    public void leggiSfide(String sezioneSfide) {
        String[] listaSfideString = splitSezione(sezioneSfide);
        for (String sfida : listaSfideString) {
            try {
                String[] argomenti = sfida.split(",");
                Zona zona = mappaZone.get(argomenti[1]);
                OggettoGara oggetto = mappaOggetti.get(argomenti[2]);
                Sfida.Difficolta difficolta = Sfida.Difficolta.valueOf(argomenti[3].toUpperCase());
                int puntiRicompensa = Integer.parseInt(argomenti[4]);

                listaSfide.add(new Sfida(argomenti[0], zona, oggetto, difficolta, puntiRicompensa));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {}
        }
    }

    public static void init(String filePath) {
        FileDataReader reader = new FileDataReader();
        try (Scanner fileInput = new Scanner(new FileInputStream(filePath))) {
            fileInput.useDelimiter("###");
            String[] sezioniFile = new String[3];
            int i = 0;
            while (fileInput.hasNext()) {
                sezioniFile[i] = fileInput.next();
                i++;
            }
            reader.leggiZone(sezioniFile[0]);
            reader.leggiOggettiGara(sezioniFile[1]);
            reader.leggiSfide(sezioniFile[2]);
        } catch (FileNotFoundException e) {
            System.out.println("Non ho trovato il file!");
        }
    }
}
