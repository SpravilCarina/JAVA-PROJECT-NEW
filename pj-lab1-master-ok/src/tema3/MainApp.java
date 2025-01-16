package tema3;

import java.util.*;  // Creeaza un obiect Scanner pentru citirea datelor de la utilizator

public class MainApp
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Integer n, i = 0;
        System.out.print("n? = ");
        n = scanner.nextInt();
        System.out.println();
        // Verifica daca numarul introdus este zero
        if (n == 0) System.out.println("~~ numarul ales nu are divizori!");
        else
        {
            System.out.println("~~ divizorii numarului ales:");
            // Gaseste si afiseaza divizorii numarului
            for (Integer div = 1; div <= Math.abs(n) / 2; div++)
                if (n % div == 0)  // Verifica daca div este un divizor
                {
                    i++; // Incrementeaza contorul pentru fiecare divizor gasit
                    System.out.print(div + " ");
                }
            // Adauga numarul in sine ca divizor
            System.out.println(Math.abs(n));
            if (i == 1) System.out.println("~~ (numarul ales este prim!)");
        }

        scanner.close();
    }
}
