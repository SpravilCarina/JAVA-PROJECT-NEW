package com.example.carti_web.controller;

import com.example.carti_web.entity.Carte;
import com.example.carti_web.repository.CarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CarteWebController {

    @Autowired
    private CarteRepository carteRepository;

    // Răspunde la cererea GET pentru a afișa lista de cărți
    @GetMapping("/lista-carti")
    public String listaCarti(Model model) {
        model.addAttribute("carti", carteRepository.findAll());
        model.addAttribute("mesaj", "Lista cartilor");
        return "carti"; // Numele fișierului Thymeleaf
    }

    // Răspunde la cererea POST pentru a adăuga o carte
    @PostMapping("/adauga-carte")
    public String adaugaCarte(String isbn, String titlu, String autor, Model model) {
        if (isbn.isEmpty() || titlu.isEmpty() || autor.isEmpty()) {
            model.addAttribute("mesaj", "Adaugarea nu se realizează daca nu completați toate caracteristicile!");
        } else {
            Carte carte = new Carte(isbn,titlu,autor);
            carteRepository.save(carte);
            model.addAttribute("mesaj", "Adaugare realizata cu succes!");
        }
        return "redirect:/lista-carti"; // Redirecționează la lista de cărți
    }

    // Filtrarea cărților după autor
    @PostMapping("/filtrare-carti")
    public String filtreazaCarti(String autor, Model model) {
        if (autor.isEmpty()) {
            model.addAttribute("carti", carteRepository.findAll());
            model.addAttribute("mesaj", "Toate cărțile din baza de date:");
        } else {
            model.addAttribute("carti", carteRepository.findByAutor(autor));
            model.addAttribute("mesaj", "Cărțile următoare aparțin autorului " + autor + ":");
        }
        return "carti"; // Numele fișierului Thymeleaf
    }
}
