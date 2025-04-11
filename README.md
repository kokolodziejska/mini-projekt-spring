# mini-projekt-spring

Projekt wykonany w środowisku: IntelliJ IDEA

### Połączenie z bazą danych

Aby uruchomić aplikację i połączyć się z bazą danych, wpisz w terminalu:
```
docker compose up --build
```

### Dokumentacja API (Swagger)

Aby przetestować działanie endpointów, otwórz Swagger UI:
```
http://localhost:8081/swagger-ui/index.html
```

### Typy żądań

W projekcie zaimplementowano kolejkę, do której przekazywane są wszystkie żądania. Kolejka obsługuje cztery typy:

- **Typ1** – operacje na bazie danych (dodawanie, edycja i wyświetlanie użytkowników typu `User`)
- **Typ2** – żądania odrzucane
- **Typ3** – zapis danych do pliku (`output.txt`)
- **Typ4** – wypisanie wyniku na konsolę




