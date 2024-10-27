package com.example.lipe_BD.models

class UserNotFoundException extends RuntimeException {

    UserNotFoundException(String message) {
        super(message)
    }
}
