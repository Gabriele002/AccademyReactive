package academy.esercizi.esercizi_15;

public class Esercizio_15_3 {
    public static void main(String[] args) {
        Esercizio_15_3 esercizio_15_3 = new Esercizio_15_3();
        esercizio_15_3.soluzione();
    }

    private void soluzione() {
        ContaPersone contaPersone = new ContaPersone();
        contaPersone.addPersona();
        contaPersone.setMaxPersone(10);
        contaPersone.addPersona();
        contaPersone.addPersona();
        contaPersone.addPersona();
    }

    public static class ContaPersone {
        private int contatore;
        private int maxPersone;

        public void setMaxPersone(int maxPersone) {
            this.maxPersone = maxPersone;
        }

        public void addPersona() {
            if (maxPersone == 0) {
                System.out.println("Non e possibile aggiungere una persona se non si setta prima il numero massimo di partecipanti");
            } else {
                if (contatore > maxPersone){
                    System.out.println("Impossibile aggiungere la persona, raggiunto il limite massimo");
                } else {
                    contatore++;
                    System.out.println("Le persone sono: " + contatore);
                    System.out.println("Puoi aggiugnere massimo altre: " + (maxPersone - contatore));
                }


            }
        }

        public void removePersona() {
            if (contatore > 0) {
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
