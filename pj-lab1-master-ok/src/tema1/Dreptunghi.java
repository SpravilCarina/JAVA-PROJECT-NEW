package tema1;

public class Dreptunghi
{
    private int lungime, latime; // Atributiile pentru dimensiunile dreptunghiului
    private int arie, perimetru; // Atributiile calculate pentru arie si perimetru

    public Dreptunghi(int lungime, int latime)
    {
        this.lungime = lungime;
        this.latime = latime;

        this.arie = lungime * latime;
        this.perimetru = (2 * lungime) + (2 * latime);
    }

    public int getLungime() { return lungime; }
    public int getLatime() { return latime; }
    public int getArie() { return arie; }
    public int getPerimetru() { return perimetru; }
}