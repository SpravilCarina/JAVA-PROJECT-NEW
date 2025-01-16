package tema2;

import java.io.*; // Importa clasele necesare pentru operatii cu fisiere
import java.util.*; // Importa clasele pentru manipularea colectiilor si Scanner

public class MainApp
{
    public static void main(String[] args)
    {
        try
        {
            File fisier_in = new File("in.txt");
            File fisier_out = new File("out.txt");
            // Creeaza obiectul Scanner pentru citirea datelor din fisierul de intrare
            Scanner scanner = new Scanner(fisier_in);
            // Creeaza obiectul PrintStream pentru scrierea datelor in fisierul de iesire
            PrintStream print = new PrintStream(fisier_out);

            Integer i = 0,
                    suma = 0;
            Double medie = Double.valueOf(0);
            Integer numar, minim = Integer.MAX_VALUE,
                    maxim = Integer.MIN_VALUE;
            System.out.println("~~ numerele din fisierul in.txt:");
            // Parcurgerea fisierului de intrare
            while (scanner.hasNext())
            {
                numar = scanner.nextInt();
                System.out.print(numar + " ");
                i++;
                suma += numar;
                if (numar <= minim) minim = numar;
                if (numar >= maxim) maxim = numar;
            }
            // Calculeaza media numerelor citite
            medie = Double.valueOf(suma) / Double.valueOf(i);

            print.println("suma = " + suma);
            print.println("medie = " + medie);
            print.println("valoare minima = " + minim);
            print.println("valoare maxima = " + maxim);
            System.out.println();
            System.out.println("~~ out.txt scris cu succes!");

            scanner.close();
            print.close();
        }
        // Tratarea exceptiei daca fisierul de intrare nu exista
        catch (FileNotFoundException exception) { System.out.println("fisier in.txt inexistent!"); }
    }
}
