package com.example.demo.repositories;

import com.example.demo.models.GameModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository //opicional já que JpaRepository implicitamente é reconhecido como bin do tipo repository
public interface GameRepository extends JpaRepository<GameModel, UUID> {
}
