// //package th.mfu.boot;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.Collection;
// import java.util.HashMap;
// import java.util.Map;

// @RestController
// @RequestMapping("/users")
// public class UserController {

//     public static Map<String, User> userRepository = new HashMap<>();

//     @PostMapping
//     public ResponseEntity<String> registerUser(@RequestBody User user) {
//         if (userRepository.containsKey(user.getUsername())) {
//             return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
//         }
//         userRepository.put(user.getUsername(), user);
//         return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
//     }

//     @GetMapping("/{username}")
//     public ResponseEntity<User> getUser(@PathVariable String username) {
//         User user = userRepository.get(username);
//         if (user == null) {
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//         }
//         return new ResponseEntity<>(user, HttpStatus.OK);
//     }

//     @GetMapping
//     public ResponseEntity<Collection<User>> list() {
//         return new ResponseEntity<>(userRepository.values(), HttpStatus.OK);
//     }

//     @DeleteMapping("/{username}")
//     public ResponseEntity<String> deleteUser(@PathVariable String username) {
//         if (userRepository.remove(username) == null) {
//             return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
//         }
//         return new ResponseEntity<>("User deleted", HttpStatus.NO_CONTENT);
//     }
// }
