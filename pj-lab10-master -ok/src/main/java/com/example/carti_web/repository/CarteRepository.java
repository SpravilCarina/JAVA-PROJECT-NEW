package com.example.carti_web.repository;

import com.example.carti_web.entity.Carte;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarteRepository extends JpaRepository<Carte, String> {
    // Metodă pentru a găsi cărțile după autor
    List<Carte> findByAutor(String autor);
}
