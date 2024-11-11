package academy.esercizi.esercizi_12.esercizio_12_1;

public class Robot {
    private int posX;
    private int posY;
    private String[][] pratoPopolato;


    public Robot(String[][] pratoPopolato) {
        this.posX = 0;
        this.posY = 1;
        this.pratoPopolato = pratoPopolato;
    }

    public void muoviSinistra(String[][] pratoPopolato) {
        if (posY > 0) {
            posY--;
            System.out.println("Robot spostato a sinistra.");
            tagliaPrato();
            sensore(pratoPopolato);
            stampaPratoTagliato();
        } else {
            System.out.println("Il robot non può muoversi più a sinistra.");
        }
    }

    public void muoviDestra(String[][] pratoPopolato) {
        if (posY < Prato.getY() - 1) {
            posY++;
            System.out.println("Robot spostato a destra.");
            tagliaPrato();
            sensore(pratoPopolato);
            stampaPratoTagliato();
        } else {
            System.out.println("Il robot non può muoversi più a destra.");
        }
    }

    public void muoviSu(String[][] pratoPopolato) {
        if (posX > 0) {
            posX--;
            System.out.println("Robot spostato verso l'alto.");
            tagliaPrato();
            sensore(pratoPopolato);
            stampaPratoTagliato();
        } else {
            System.out.println("Il robot non può muoversi più in alto.");
        }
    }

    public void muoviGiu(String[][] pratoPopolato) {
        if (posX < Prato.getX() - 1) {
            posX++;
            System.out.println("Robot spostato verso il basso.");
            tagliaPrato();
            sensore(pratoPopolato);
            stampaPratoTagliato();
        } else {
            System.out.println("Il robot non può muoversi più in basso.");
        }
    }

    public void tagliaPrato() {
        if (pratoPopolato[posX][posY].equals("■")) {
            pratoPopolato[posX][posY] = " ";
        }
    }

    public void stampaPratoTagliato() {
        for (String[] popolato : pratoPopolato) {
            for (String s : popolato) {
                System.out.printf("%-3s", s);
            }
            System.out.println();
        }
    }

    public void sensore(String[][] pratoPopolato) {
        String[] direzioni = {"Su", "Giu", "Sinistra", "Destra"};
        boolean trovato = false;

        for (String direzione : direzioni) {
            int nuovaPosX = posX;
            int nuovaPosY = posY;

            switch (direzione) {
                case "Su":
                    nuovaPosX--;
                    break;
                case "Giu":
                    nuovaPosX++;
                    break;
                case "Sinistra":
                    nuovaPosY--;
                    break;
                case "Destra":
                    nuovaPosY++;
                    break;
            }

            if (nuovaPosX >= 0 && nuovaPosX < pratoPopolato.length && nuovaPosY >= 0 && nuovaPosY < pratoPopolato[0].length) {
                String stato = pratoPopolato[nuovaPosX][nuovaPosY];

                if (stato.equals("■")) {
                    System.out.println("L'erba non è stata ancora tagliata a " + direzione);
                    trovato = true;
                } else if (stato.equals("|")) {
                    System.out.println("Sei arrivato al bordo a " + direzione);
                    trovato = true;
                } else {
                    System.out.println("Il prato è già stato tagliato a " + direzione);
                }
            }
        }
        if (!trovato) {
            System.out.println("Non ci sono più posizioni da tagliare nelle vicinanze.");
        }
    }

}





