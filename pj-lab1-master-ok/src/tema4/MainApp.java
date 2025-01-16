package tema4;

import java.util.*; // Importa clasele necesare pentru generarea numerelor aleatoar

public class MainApp
{
    // Metoda pentru calcularea CMMDC folosind algoritmul lui Euclid
    private static Integer cmmdc(Integer x, Integer y)
    {
        while (x != y)  // Continua pana cand cele doua numere devin egale
        {
            if (x > y) x -= y;
            else y -= x;
        }
        return x; // Returneaza valoarea comuna, care este CMMDC
    }

    public static void main(String[] args)
    {
        Random rng = new Random(); // Creeaza un generator de numere aleatoare
        Integer x, y;
        // Seteaza seed-ul generatorului pentru a produce numere diferite la fiecare rulare
        rng.setSeed(System.currentTimeMillis());
        // Genereaza primul numar aleator intre 1 si 29 (excludem 0)
        do { x = rng.nextInt(30); } while (x == 0);
        do { y = rng.nextInt(30); } while (y == 0);

        System.out.println("~~ cele doua numere generate aleator:");
        System.out.println(x + " " + y);
        System.out.println("cel mai mare divizor comun = " + cmmdc(x, y));
    }
}
