package academy.esercizi.esercizi_1;

public class Esercizio_1_5 {

    public static void main(String[] args) {
        Esercizio_1_5 esercizio_1_5 = new Esercizio_1_5();
        esercizio_1_5.soluzione();
    }

    public void soluzione(){
        {
            int n = 1;
            int k = 2;
            int r = n;
            if (k < n) {
                r = k;
            }
            System.out.println("La condizione dell if e sempre falsa quindi non si esegue il blocco di codice");
            System.out.printf("%s,%s,%s", 1,2,1);
        }

        {
            int n = 1;
            int k = 2;
            int r;
            if (n < k) {
                r = k;
            }else {
                r = k+n;
            }
            System.out.println("La condizione dell if e sempre falsa ma essendoci else viene eseguito il codice al suo interno");
            System.out.printf("%s,%s,%s", 1,2,2);
        }

        {
            int n = 1;
            int k = 2;
            int r= k;
            if (r < k) {
                n = r;
            }else {
                k = n;
            }
            System.out.println("La condizione dell if e sempre falsa ma essendoci else viene eseguito il codice al suo interno");
            System.out.printf("%s,%s,%s", 1,1,2);
        }

        {
            int n = 1;
            int k = 2;
            int r= 3;
            if (r < n+ k) {
                r = 2*n;
            }else {
                k = 2*r;
            }
            System.out.println("La condizione dell if e sempre falsa ma essendoci else viene eseguito il codice al suo interno");
            System.out.printf("%s,%s,%s", 1,6,3);
        }

    }
}
