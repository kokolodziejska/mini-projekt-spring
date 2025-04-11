# mini-projekt-spring
Projekt wykonany w środowisku: InteliJ
### Połączenie z bazą
nalerzy wpisać w konsoli
```
docker compose up --build
```
### Dostęp do endpointów
Można przetestować działanie endpointów za pomoca swaggera 
```
http://localhost:8081/swagger-ui/index.html#/type-4-request-consol/handleType4
```


Zaimplmentowana została kolejka, na którą przekazywane są wszytkie rządania. Kolejka przetwarza 4 typy rządań:
- Typ1 - połaczenie z bazą danych (Dadawanie, edyzja, wyświetlanie "Userów")
- Typ2 - odrzucanie
- Typ3 - zapis do pliku ("output.txt")
- Typ4 - wynik wypisany na konsolę
