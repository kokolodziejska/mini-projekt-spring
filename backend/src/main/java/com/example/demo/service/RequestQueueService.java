package com.example.demo.service;

import com.example.demo.model.Request;
import com.example.demo.model.User;
import com.example.demo.repository.RequestRepository;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
@RequiredArgsConstructor
public class RequestQueueService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    private final BlockingQueue<Request> queue = new LinkedBlockingQueue<>();

    public void addRequest(Request request) {
        queue.offer(request);
    }

    @PostConstruct
    public void startProcessing() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Request request = queue.take();
                    handleRequest(request);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    private void handleRequest(Request request) {
        System.out.println(">> PRZETWARZANIE: " + request);
        switch (request.getType()) {
            case "Typ1" -> {
                System.out.println("Obsługa Typ1 - operacja na użytkowniku");
                handleUserOperation(request.getContent());
            }
            case "Typ2" -> System.out.println("Typ2 – odrzucono: " + request.getContent());
            case "Typ3" -> {
                System.out.println("Zapisuję do pliku: " + request.getContent());
                saveToFile(request.getContent());
            }
            case "Typ4" -> System.out.println("Typ4 – log na konsolę: " + request.getContent());
            default -> System.out.println("Nieznany typ: " + request.getType());
        }
    }

    private void saveToFile(String content) {
        try (FileWriter writer = new FileWriter("output.txt", true)) {
            writer.write(content + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleUserOperation(String content) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            JsonNode root = mapper.readTree(content);
            String action = root.get("action").asText();

            switch (action) {
                case "CREATE" -> {
                    User user = mapper.treeToValue(root.get("user"), User.class);
                    userRepository.save(user);
                    System.out.println("Utworzono użytkownika: " + user.getName());
                }
                case "UPDATE_HOBBY" -> {
                    String name = root.get("name").asText();
                    String hobby = root.get("hobby").asText();
                    Optional<User> userOpt = userRepository.findByName(name);
                    if (userOpt.isEmpty()) {
                        System.out.println("Nie znaleziono użytkownika: " + name);
                        return;
                    }
                    User user = userOpt.get();
                    user.setHobby(hobby);
                    userRepository.save(user);
                    System.out.println("Zmieniono hobby dla " + name);
                }
                case "UPDATE_BIRTHDATE" -> {
                    String name = root.get("name").asText();
                    String birthDate = root.get("birthDate").asText();
                    Optional<User> userOpt = userRepository.findByName(name);
                    if (userOpt.isEmpty()) {
                        System.out.println("Nie znaleziono użytkownika: " + name);
                        return;
                    }
                    User user = userOpt.get();
                    user.setBirthDate(LocalDate.parse(birthDate));
                    userRepository.save(user);
                    System.out.println("Zmieniono datę urodzenia dla " + name);
                }
                case "UPDATE_MOVIES" -> {
                    String name = root.get("name").asText();
                    String movies = root.get("favoriteMovies").asText();
                    Optional<User> userOpt = userRepository.findByName(name);
                    if (userOpt.isEmpty()) {
                        System.out.println("Nie znaleziono użytkownika: " + name);
                        return;
                    }
                    User user = userOpt.get();
                    user.setFavoriteMovies(movies);
                    userRepository.save(user);
                    System.out.println("Zmieniono filmy dla " + name);
                }
                case "SHOW_ALL_USERS" -> {
                    List<User> users = userRepository.findAll();
                    System.out.println("Lista wszystkich użytkowników:");
                    users.forEach(System.out::println);
                }
                default -> System.out.println("Nieznana akcja użytkownika: " + action);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Błąd przetwarzania operacji na użytkowniku");
        }
    }

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }
}
