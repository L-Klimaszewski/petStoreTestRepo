package petStore;

    /* Interfejs "AbstractTest" jest interfejsem generycznym i przyjmuje on typy generyczne jako parametry.
     Typy te mogę poźniej zastąpić dowolnym typem obiektu podczas tworzenia obiektu klasy implementującej ten
     interfejs. Dzięki temu mogę użyć tego interfejsu z różnymi typami obiektów bez konieczności pisania
     dodatkowego kodu. */

public interface AbstractTest<T,U,W>{

    // Metoda ta zwraca obiekt typu T, który reprezentuje ciało żądania wysłanego do API.
    T getRequestBody();

    // Metoda zwraca obiekt typu W, który reprezentuje żądanie wysłane do API.
    W getRequest();

    // Metoda, która zwraca obiekt typu U, który reprezentuje ciało odpowiedzi na żądanie API.
    U getResponseBody();

    // Metoda zwracacjąca adres URL, który będzie testowany.
    String getUrl();

    // Metoda zwraca obiekt klasy Class reprezentujący typ obiektu, który powinien zostać zwrócony w
    // odpowiedzi na żądanie API.
    Class<U> getResponseClass();

    // Metoda ustawiająca opdowiedź serwera na obiekt dowolnego typu.
    void setResponse(Object response);
}
