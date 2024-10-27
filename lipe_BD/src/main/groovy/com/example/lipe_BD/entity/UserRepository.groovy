package com.example.lipe_BD.entity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByName(String name)
}