package academy.esercizi.esercizio_39;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Esercizio_39 {
    public static void main(String[] args) {
        Esercizio_39 esercizio_39 = new Esercizio_39();
        esercizio_39.soluzione();
    }

    private void soluzione() {
        UfficioPostale ufficioPostale = new UfficioPostale(Arrays.asList("Rossi", "Bianchi"));

        Runnable pacco = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("PACCO");
                    Sportello sportelloLibero = ufficioPostale.getSportelloLibero();
                    System.out.println("Buongiorno" + sportelloLibero.getNome() + ", dovrei spedire questo pacco");
                    Thread.sleep(10000);
                    System.out.println("Il pacco è stato consegnato da " + sportelloLibero.getNome());
                    sportelloLibero.cambiaStato();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Runnable bolletta = () -> {
            try {
                System.out.println("BOLLETTA");
                Sportello sportelloLibero = ufficioPostale.getSportelloLibero();
                System.out.println("Buongiorno" + sportelloLibero.getNome() + ", avrei una bolelta da pagare");
                Thread.sleep(2000);
                System.out.println("Grazie, la bolletta è stata pagata da " + sportelloLibero.getNome());
                sportelloLibero.cambiaStato();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };


        Telegramma primoTelegramma = new Telegramma("1", ufficioPostale);
        Telegramma secondoTelegramma = new Telegramma("2", ufficioPostale);
        List<Thread> codaUtenti = new ArrayList<>();
        codaUtenti.add(new Thread(pacco));
        codaUtenti.add(new Thread(bolletta));
        codaUtenti.add(new Thread(primoTelegramma));
        codaUtenti.add(new Thread(pacco));
        codaUtenti.add(new Thread(secondoTelegramma));
        codaUtenti.add(new Thread(pacco));
        codaUtenti.add(new Thread(pacco));
        codaUtenti.add(new Thread(pacco));

        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);

        codaUtenti.forEach(executorService::execute);

        executorService.shutdown();

    }
}
