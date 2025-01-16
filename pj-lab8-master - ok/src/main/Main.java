package main;

import db.DatabaseOperations;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseOperations dbOps = new DatabaseOperations();

        while (true) {
            System.out.println("\nMeniu:");
            System.out.println("1. Adăugarea unei persoane");
            System.out.println("2. Adăugarea unei excursii");
            System.out.println("3. Afișarea tuturor persoanelor și excursiilor");
            System.out.println("4. Afișarea excursiilor unei persoane");
            System.out.println("5. Afișarea persoanelor care au vizitat o destinație");
            System.out.println("6. Afișarea persoanelor care au făcut excursii într-un an");
            System.out.println("7. Ștergerea unei excursii");
            System.out.println("8. Ștergerea unei persoane");
            System.out.println("9. Ieșire");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consumă linia goală

            switch (choice) {
                case 1:
                    dbOps.adaugaPersoana();
                    break;
                case 2:
                    dbOps.adaugaExcursie();
                    break;
                case 3:
                    dbOps.afiseazaPersoaneSiExcursii();
                    break;
                case 4:
                    dbOps.afiseazaExcursiiPersoana();
                    break;
                case 5:
                    dbOps.afiseazaPersoaneDestinatie();
                    break;
                case 6:
                    dbOps.afiseazaPersoaneAn();
                    break;
                case 7:
                    dbOps.stergeExcursie();
                    break;
                case 8:
                    dbOps.stergePersoana();
                    break;
                case 9:
                    System.out.println("Ieșire...");
                    return;
                default:
                    System.out.println("Opțiune invalidă!");
            }
        }
    }
}
