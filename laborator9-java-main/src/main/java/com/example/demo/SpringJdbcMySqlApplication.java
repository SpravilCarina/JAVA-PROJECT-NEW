package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.demo.Masina;
import com.example.demo.MasinaJdbcDao;

@SpringBootApplication
public class SpringJdbcMySqlApplication implements CommandLineRunner {

    @Autowired
    MasinaJdbcDao dao;

    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcMySqlApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\nToate masinile:");
        dao.findAll().forEach(System.out::println);
        System.out.println("\nMasina cu nr de inmatriculare :" + dao.findById("B45CNB"));
//        System.out.println("\nSterge Masina cu  nr_inmatriculare, numarul de randuri sterse: "
//                + dao.deleteById("TG90QWS"));
//        System.out.println("\nAdauga Masina cu  nr_inmatriculare, numarul de randuri adaugate: "
//                + dao.insert(new Masina("RM45OLT    ", "dacia", 2020, "albastra", 2450)));
//        System.out.println("\nActualizarea Masina cu nr_inmatriculare, numarul de randuri actualizate:"
//                + dao.update(new Masina("CJ56AQW", "skoda", 2010,"rosie",9087)));
        System.out.println("\nToate masinile dupa adaugare, actualizare, stergere:");
        dao.findAll().forEach(System.out::println);
        int numarMasini = dao.getNumarMasiniByMarca("dacia");
        System.out.println("Numărul de mașini cu marca '" + "' este: " + numarMasini);
        int masiniSubKm = dao.nrMasinisub1000();
        System.out.println("nr de masini sunt 100000 este:" + masiniSubKm);
         System.out.println("nr de masini mai vechi de 5 ani este: ");
        dao.masiniSub5Ani().forEach(System.out::println);
    }

}


