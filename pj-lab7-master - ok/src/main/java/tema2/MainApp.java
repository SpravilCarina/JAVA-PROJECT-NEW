package tema2;
import static tema2.Json.*;
import static tema2.InstrumentMuzical.*;

import java.io.*;
import java.util.*;

public class MainApp
{
    private static void meniu()
    {
        System.out.println("1) inserare initiala (3 chitari + 3 tobe)");
        System.out.println("2) salvare colectie Set in .json");
        System.out.println("3) citire colectie Set din .json");
        System.out.println("4) afisare colectie instrumente");
        System.out.println("5) verificare daca colectia Set permite duplicate");
        System.out.println("0) iesire");
        System.out.println();
    }

    private static InstrumentMuzical adaugare(Scanner scanner, Integer tip)
    {
        InstrumentMuzical add = null;

        String producator;
        Float pret;
        do
        {
            System.out.print("producator? = ");
            producator = scanner.next();
        } while (producator.isBlank());
        do
        {
            System.out.print("pret? = ");
            pret = scanner.nextFloat();
        } while (pret <= 0);

        if (tip == 1) // chitara
        {
            Integer tip_chitara,
                    nr_corzi;
            do
            {
                System.out.print("tip chitara? (0 = electrica / 1 = acustica / 2 = clasica) = ");
                tip_chitara = scanner.nextInt();
            } while (tip_chitara < 0 || tip_chitara > 2);
            do
            {
                System.out.print("nr corzi? = ");
                nr_corzi = scanner.nextInt();
            } while (nr_corzi <= 0);
            add = new Chitara(producator, pret, tip_chitara, nr_corzi);
        }
        if (tip == 2) // set tobe
        {
            Integer tip_tobe,
                    nr_tobe, nr_cinele;

            do
            {
                System.out.print("tip set tobe? (0 = electrice / 1 = acustice) = ");
                tip_tobe = scanner.nextInt();
            } while (tip_tobe < 0 || tip_tobe > 1);
            do
            {
                System.out.print("nr tobe? = ");
                nr_tobe = scanner.nextInt();
            } while (nr_tobe <= 0);
            do
            {
                System.out.print("nr cinele? = ");
                nr_cinele = scanner.nextInt();
            } while (nr_cinele <= 0);
            add = new SetTobe(producator, pret, tip_tobe, nr_tobe, nr_cinele);
        }

        return add;
    }

    public static void main(String[] args)
    {
        Set<InstrumentMuzical> instrumente = new HashSet<>();
        InstrumentMuzical add;

        Boolean running = true;
        Scanner scanner = new Scanner(System.in);
        Integer optiune;
        while (running)
        {
            meniu();
            System.out.print("~~ optiunea ta: ");
            optiune = scanner.nextInt();
            System.out.println();

            switch (optiune)
            {
                case 0:
                    if (instrumente != null) instrumente.clear();
                    running = false;
                    break;
                case 1:
                    for (Integer i = 0; i < 1; i++)
                    {
                        System.out.println("~~ chitara " + (i + 1) + ":");
                        add = adaugare(scanner, 1);
                        if (add != null) instrumente.add(add);
                        System.out.println();
                    }
                    /*for (Integer i = 0; i < 3; i++)
                    {
                        System.out.println("~~ set tobe " + (i + 1) + ":");
                        add = adaugare(scanner, 2);
                        if (add != null) instrumente.add(add);
                        System.out.println();
                    }*/
                    System.out.println("~~ instrumente adaugate cu succes!");
                    break;
                case 2:
                    if (instrumente == null || instrumente.isEmpty()) System.out.println("~~ nu exista instrumente inregistrate!");
                    else
                    {
                        scriereJson(instrumente);
                        System.out.println("instrumente.json scris cu succes!");
                    }
                    System.out.println();
                    break;
                case 3:
                    instrumente = citireJson();
                    System.out.println("instrumente.json citit cu succes!");
                    System.out.println();
                    if (instrumente == null || instrumente.isEmpty()) System.out.println("~~ nu exista instrumente inregistrate!");
                    else
                    {
                        System.out.println("~~ Set complet instrumente:");
                        for (var ent : instrumente) System.out.println(ent);
                    }
                    System.out.println();
                    break;
                case 4:
                    if (instrumente == null || instrumente.isEmpty()) System.out.println("~~ nu exista instrumente inregistrate!");
                    else
                    {
                        System.out.println("~~ Set complet instrumente:");
                        for (var ent : instrumente) System.out.println(ent);
                    }
                    System.out.println();
                    break;
                case 5:
                    if (instrumente == null || instrumente.isEmpty()) System.out.println("~~ nu exista instrumente inregistrate!");
                    else
                    {
                        add = instrumente.stream().
                                findFirst().get();
                        if (instrumente.add(add))
                        {
                            System.out.println("~~ colectia Set permite duplicate!");

                            System.out.println("~~ impiedicare duplicate prin metoda equals(): ");
                            Boolean insert = true;
                            for (var ent : instrumente)
                                if (add.equals(ent))
                                {
                                    insert = false;
                                    break;
                                }
                            if (insert) System.out.println("elementul exista deja in colectia Set!");
                            else System.out.println("elementul poate fi adaugat in colectia Set!");
                        }
                        else System.out.println("~~ colectia Set NU permite duplicate!");
                    }
                    System.out.println();
                    break;
                default:
                    System.out.println("~~ optiune invalida!");
                    break;
            }
            if (running)
            {
                try
                {
                    System.out.print("(apasati ENTER pentru a continua)");
                    System.in.read();
                    System.out.println();
                }
                catch (Exception exception) {}
            }
        }
        scanner.close();
    }
}