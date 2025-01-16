package main;

import model.Mobilier;
import service.GestionareMobilier;
import service.GestionarePerechi;

import java.util.List;

public class App {
    public static void main(String[] args) {
        // Gestionare perechi de numere
        GestionarePerechi.gestioneazaPerechi();

        // Gestionare mobilier
        List<Mobilier> listaMobilier = GestionareMobilier.citireMobilier("mobilier.json");
        if (!listaMobilier.isEmpty()) {
            GestionareMobilier.afisareMobilier(listaMobilier);
            GestionareMobilier.afisarePlaciMobilier(listaMobilier, "Birou");
            GestionareMobilier.estimareColiPal(listaMobilier, "Dulap");
        }
    }
}
