package tema2;

import com.fasterxml.jackson.annotation.*;

enum TipChitara
{
    ELECTRICA, ACUSTICA, CLASICA;

    @JsonValue
    public int toValue() { return ordinal(); }
}
enum TipTobe
{
    ELECTRONICE, ACUSTICE;

    @JsonValue
    public int toValue() { return ordinal(); }
}

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public abstract class InstrumentMuzical
{
    private String producator;
    private Float pret;

    @JsonGetter
    public String getProducator() { return this.producator; }
    @JsonGetter
    public Float getPret() { return this.pret; }

    @JsonSetter
    public void setProducator(String producator) { this.producator = producator; }
    @JsonSetter
    public void setPret(Float pret) { this.pret = pret; }

    @JsonCreator
    public InstrumentMuzical(String producator, Float pret)
    {
        this.producator = producator;
        this.pret = pret;
    }

    @Override
    public String toString()
    { return this.producator + ", " + this.pret + " lei"; }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;

        InstrumentMuzical ent = (InstrumentMuzical)obj;
        if (this.producator.compareTo(ent.producator) != 0) return false;
        if (this.pret.compareTo(ent.pret) != 0) return false;

        return true;
    }

    public static class Chitara extends InstrumentMuzical
    {
        private TipChitara tip_chitara;
        private Integer nr_corzi;

        @JsonGetter
        public Integer getTip_chitara() { return this.tip_chitara.ordinal(); }
        @JsonIgnore
        public String getTip_chitara_string()
        {
            if (this.tip_chitara == TipChitara.ELECTRICA) return "electrica";
            if (this.tip_chitara == TipChitara.ACUSTICA) return "acustica";
            if (this.tip_chitara == TipChitara.CLASICA) return "clasica";
            return "N/A";
        }
        @JsonGetter
        public Integer getNr_corzi() { return this.nr_corzi; }

        @JsonSetter
        public void setTip_chitara(Integer tip_chitara) { this.tip_chitara = TipChitara.values()[tip_chitara]; }
        @JsonSetter
        public void setNr_corzi(Integer nr_corzi) { this.nr_corzi = nr_corzi; }

        @JsonCreator
        public Chitara(String producator, Float pret, Integer tip_chitara, Integer nr_corzi)
        {
            super(producator, pret);
            try
            { this.tip_chitara = TipChitara.values()[tip_chitara]; }
            catch (Exception exception) { this.tip_chitara = TipChitara.ELECTRICA; }
            this.nr_corzi = nr_corzi;
        }

        @Override
        public String toString()
        { return "CHITARA: " + super.toString() + ", " + this.getTip_chitara_string() + ", " + this.nr_corzi + " corzi"; }

        @Override
        public boolean equals(Object obj)
        {
            Chitara ent = (Chitara)obj;
            if (this.tip_chitara.compareTo(ent.tip_chitara) != 0) return false;
            if (this.nr_corzi.compareTo(ent.nr_corzi) != 0) return false;

            return super.equals(obj);
        }
    }
    public static class SetTobe extends InstrumentMuzical
    {
        private TipTobe tip_tobe;
        private Integer nr_tobe, nr_cinele;

        @JsonGetter
        public Integer getTip_tobe() { return this.tip_tobe.ordinal(); }
        public String getTip_tobe_string()
        {
            if (this.tip_tobe == TipTobe.ELECTRONICE) return "electronice";
            if (this.tip_tobe == TipTobe.ACUSTICE) return "acustice";
            return "N/A";
        }
        @JsonGetter
        public Integer getNr_tobe() { return this.nr_tobe; }
        @JsonGetter
        public Integer getNr_cinele() { return this.nr_cinele; }

        @JsonSetter
        public void setTip_tobe(Integer tip_tobe) { this.tip_tobe = TipTobe.values()[tip_tobe]; }
        @JsonSetter
        public void setNr_tobe(Integer nr_tobe) { this.nr_tobe = nr_tobe; }
        @JsonSetter
        public void setNr_cinele(Integer nr_cinele) { this.nr_cinele = nr_cinele; }

        @JsonCreator
        public SetTobe(String producator, Float pret, Integer tip_tobe, Integer nr_tobe, Integer nr_cinele)
        {
            super(producator, pret);
            try
            { this.tip_tobe = TipTobe.values()[tip_tobe]; }
            catch (Exception exception) { this.tip_tobe = TipTobe.ELECTRONICE; }
            this.nr_tobe = nr_tobe;
            this.nr_cinele = nr_cinele;
        }

        @Override
        public String toString()
        { return "SET TOBE: " + super.toString() + ", " + this.getTip_tobe_string() + ", " + this.nr_tobe + " tobe, " + this.nr_cinele + " cinele"; }

        @Override
        public boolean equals(Object obj)
        {
            SetTobe ent = (SetTobe)obj;
            if (this.tip_tobe.compareTo(ent.tip_tobe) != 0) return false;
            if (this.nr_tobe.compareTo(ent.nr_tobe) != 0) return false;
            if (this.nr_cinele.compareTo(ent.nr_cinele) != 0) return false;

            return super.equals(obj);
        }
    }
}