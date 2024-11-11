package academy.esercizi.esercizi_15;

import academy.esercizi.utility.Validificazione;

public class Esercizio_15_2 {
    public static void main(String[] args) {
        Esercizio_15_2 esercizio_15_2 = new Esercizio_15_2();
        esercizio_15_2.soluzione();
    }

    private void soluzione() {
        ContaPersone contaPersone = new ContaPersone();
        contaPersone.addPersona();
        contaPersone.addPersona();
        contaPersone.addPersona();
        contaPersone.addPersona();
        contaPersone.removePersona();
        contaPersone.removePersona();
        contaPersone.removePersona();
        contaPersone.removePersona();
        contaPersone.removePersona();
        contaPersone.removePersona();
    }

    public static class ContaPersone{
        private int contatore;


        public void addPersona() {
            contatore++;
            System.out.println("Le persone sono: " + contatore);
        }

        public void removePersona(){
            if (contatore > 0){
                contatore--;
                System.out.println("Le persone sono: " + contatore);
            } else {
                System.out.println("Non ci sono piu persone da rimuovere");
            }
        }

        public int getContatore() {
            return contatore;
        }
    }

}
