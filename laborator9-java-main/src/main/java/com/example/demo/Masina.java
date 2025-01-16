package com.example.demo;

public class Masina {
private String nr_inmatriculare;
private String marca;
private int anul_fabricatiei;
private String culoarea;
private int nr_km;

    public Masina() {}

    public Masina(String nr_inmatriculare, String marca, int anul_fabricatiei, String culoarea, int nr_km) {
      super();
        this.nr_inmatriculare = nr_inmatriculare;
        this.marca = marca;
        this.anul_fabricatiei = anul_fabricatiei;
        this.culoarea = culoarea;
        this.nr_km = nr_km;
    }


    public String getNr_inmatriculare() {
        return nr_inmatriculare;
    }

    public void setNr_inmatriculare(String nr_inmatriculare) {
        this.nr_inmatriculare = nr_inmatriculare;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAnul_fabricatiei() {
        return anul_fabricatiei;
    }

    public void setAnul_fabricatiei(int anul_fabricatiei) {
        this.anul_fabricatiei = anul_fabricatiei;
    }

    public String getCuloarea() {
        return culoarea;
    }

    public void setCuloarea(String culoarea) {
        this.culoarea = culoarea;
    }

    public int getNr_km() {
        return nr_km;
    }

    public void setNr_km(int nr_km) {
        this.nr_km = nr_km;
    }

    @Override
    public String toString() {
        return "Masina{" +
                "nr_inmatriculare='" + nr_inmatriculare + '\'' +
                ", marca='" + marca + '\'' +
                ", anul_fabricatiei=" + anul_fabricatiei +
                ", culoarea='" + culoarea + '\'' +
                ", nr_km=" + nr_km +
                '}';
    }
}
