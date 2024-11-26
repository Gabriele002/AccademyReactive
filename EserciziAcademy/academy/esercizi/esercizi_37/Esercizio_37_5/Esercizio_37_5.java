package academy.esercizi.esercizi_37.Esercizio_37_5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Esercizio_37_5 {
    public static void main(String[] args) {
        Esercizio_37_5 esercizio_37_5 = new Esercizio_37_5();
        esercizio_37_5.soluzione();
    }

    private void soluzione() {
        List<Country> countries = new ArrayList<>();
        SuperficeComparable superficeComparable = new SuperficeComparable();
        Country italia = new Country("Italia", 12000, 10);
        Country spagna = new Country("Spagna", 120, 30);
        Country africa = new Country("Africa", 1000000, 20);
        countries.add(africa);
        countries.add(italia);
        countries.add(spagna);

        countries.forEach(System.out::println);
        System.out.println("---------------");

        countries = countries.stream()
                .sorted((a, b) -> a.getSuperficia() < b.getSuperficia() ? -1 : a.getSuperficia() == b.getSuperficia() ? 0 : 1)
                .collect(Collectors.toList()
                );
        countries.forEach(x -> System.out.println(x));


       countries.sort(Comparator.comparing(country -> country.getSuperficia()));
               //(Comparator.comparing(country -> country.getSuperficia()));
       countries.sort((a, b) -> a.getSuperficia() > b.getSuperficia() ? -1 : a.getSuperficia() == b.getSuperficia() ? 0 : 1);
       System.out.println("---------------");

//        countries.sort((o1, o2) -> o1.getSuperficia() > o2.getSuperficia() ? 1:0);
        countries.forEach(System.out::println);

        System.out.println("---------------");

        countries.sort(superficeComparable);
        countries.forEach(System.out::println);

    }
}
