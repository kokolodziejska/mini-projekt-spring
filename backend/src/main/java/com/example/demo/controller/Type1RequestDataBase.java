package com.example.demo.controller;
import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.UpdateHobbyDto;
import com.example.demo.dto.UpdateBirthDateDto;
import com.example.demo.dto.UpdateMoviesDto;
import com.example.demo.model.Request;
import com.example.demo.service.RequestQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import java.util.List;



@RestController
@RequestMapping("/type1")
@RequiredArgsConstructor
@CrossOrigin
public class Type1RequestDataBase {

    private final RequestQueueService queueService;
    private final UserRepository userRepository;



    @PostMapping("/add-user")
    public ResponseEntity<String> createUser(@RequestBody CreateUserDto dto) {
        String content = String.format("""
            {
              "action": "CREATE",
              "user": {
                "name": "%s",
                "birthDate": "%s",
                "hobby": "%s",
                "favoriteMovies": "%s"
              }
            }""", dto.getName(), dto.getBirthDate(), dto.getHobby(), dto.getFavoriteMovies());

        queueService.addRequest(new Request(null, "Typ1", content));
        return ResponseEntity.ok("Typ1 - utworzono użytkownika");
    }

    @PostMapping("/update-user-hobby")
    public ResponseEntity<String> updateHobby(@RequestBody UpdateHobbyDto dto) {
        String content = String.format("""
            {
              "action": "UPDATE_HOBBY",
              "name": "%s",
              "hobby": "%s"
            }""", dto.getName(), dto.getHobby());

        queueService.addRequest(new Request(null, "Typ1", content));
        return ResponseEntity.ok("Typ1 - zmieniono hobby");
    }

    @PostMapping("/update-user-birthdate")
    public ResponseEntity<String> updateBirthDate(@RequestBody UpdateBirthDateDto dto) {
        String content = String.format("""
            {
              "action": "UPDATE_BIRTHDATE",
              "name": "%s",
              "birthDate": "%s"
            }""", dto.getName(), dto.getBirthDate());

        queueService.addRequest(new Request(null, "Typ1", content));
        return ResponseEntity.ok("Typ1 - zmieniono datę urodzenia");
    }

    @PostMapping("/update-user-movies")
    public ResponseEntity<String> updateMovies(@RequestBody UpdateMoviesDto dto) {
        String content = String.format("""
            {
              "action": "UPDATE_MOVIES",
              "name": "%s",
              "favoriteMovies": "%s"
            }""", dto.getName(), dto.getFavoriteMovies());

        queueService.addRequest(new Request(null, "Typ1", content));
        return ResponseEntity.ok("Typ1 - zmieniono ulubione filmy");
    }
    @GetMapping("/all-users")
    public ResponseEntity<String> getAllUsersViaQueue() {
        String content = """
        {
          "action": "SHOW_ALL_USERS"
        }""";

        queueService.addRequest(new Request(null, "Typ1", content));
        return ResponseEntity.ok("Typ1 wysłany do kolejki.");
    }

}
