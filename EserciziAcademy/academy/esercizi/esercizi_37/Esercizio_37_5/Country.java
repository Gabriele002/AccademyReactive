package academy.esercizi.esercizi_37.Esercizio_37_5;

public class Country {
    private Integer abitanti;
    private String nome;
    private int superficia;

//    @Override
//    public int compareTo(Country o) {
//        return abitanti.compareTo(o.abitanti);
//    }

    public Country(String nome,Integer abitanti, Integer superficia) {
        this.nome = nome;
        this.abitanti = abitanti;
        this.superficia = superficia;
    }

    public Integer getAbitanti() {
        return abitanti;
    }

    public void setAbitanti(Integer abitanti) {
        this.abitanti = abitanti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSuperficia() {
        return superficia;
    }

    public void setSuperficia(int superficia) {
        this.superficia = superficia;
    }

    @Override
    public String toString() {
        return "Country{" +
                "abitanti=" + abitanti +
                ", nome='" + nome + '\'' +
                ", superficia=" + superficia +
                '}';
    }
}
