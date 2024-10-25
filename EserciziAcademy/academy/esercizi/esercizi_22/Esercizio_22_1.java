package academy.esercizi.esercizi_22;

public class Esercizio_22_1 {

    public static void main(String[] args) {
        Esercizio_22_1 esercizio_22_1 = new Esercizio_22_1();
        esercizio_22_1.soluzione();
    }

    public void soluzione() {
        int importoPagato = 500;
        int sommaDaPagare = 274;

        int resto = importoPagato - sommaDaPagare;

        int dollari = resto / 100;
        System.out.println("Dollari: " + dollari);
        resto = resto - (dollari * 100);

        int quarti = resto / 25;
        System.out.println("Quarti: " + quarti);
        resto = resto - (quarti * 25);

        int dime = resto / 10;
        System.out.println("Dime: " + dime);
        resto = resto - (dime * 10);


        int nickel = resto / 5;
        System.out.println("Nickel: " + nickel);
        resto = resto - (nickel * 5);

        int penny = resto;
        System.out.println("Penny: " + penny);
    }
}
