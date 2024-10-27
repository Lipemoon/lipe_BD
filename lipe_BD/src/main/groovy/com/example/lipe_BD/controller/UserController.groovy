package com.example.lipe_BD.controller
import com.example.lipe_BD.entity.UserEntity
import com.example.lipe_BD.models.InvalidRequestException
import com.example.lipe_BD.models.UserNotFoundException
import com.example.lipe_BD.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController {

    @Autowired
    UserService userService

    @GetMapping
    List<UserEntity> getAllUsers(@RequestParam(required = false, value ="name") String name) {
        userService.getAllUsers(name)
    }

    @GetMapping("/{id}")
    ResponseEntity getUserById(@PathVariable("id") Long id) {
        try {
            UserEntity user = userService.getUserById(id)
            ResponseEntity.ok(user)
        } catch (UserNotFoundException ex) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body([erro: ex.message])
        }
    }

    @PostMapping
    ResponseEntity createUser(@RequestBody UserEntity user) {
        try {
            UserEntity createdUser = userService.createUser(user)
            ResponseEntity.status(HttpStatus.CREATED).body(createdUser)
        } catch (InvalidRequestException ex) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body([erro: ex.message])
        }
    }

    @PutMapping("/{id}")
    ResponseEntity updateUser(@PathVariable("id") Long id, @RequestBody UserEntity userDetails) {
        try {
            UserEntity updatedUser = userService.updateUser(id, userDetails)
            ResponseEntity.ok(updatedUser)
        } catch (UserNotFoundException ex) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body([erro: ex.message])
        } catch (InvalidRequestException ex) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body([erro: ex.message])
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable("id") Long id) {
        try {
            userService.deleteUser(id)
            ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        } catch (UserNotFoundException ex) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body([erro: ex.message])
        }
    }

}
