package com.example.demo.controllers;

import com.example.demo.dtos.GameRecordDto;
import com.example.demo.models.GameModel;
import com.example.demo.repositories.GameRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
public class GameController {

    @Autowired
    GameRepository gameRepository;

    @PostMapping("/games")
    public ResponseEntity<GameModel> saveGame(@RequestBody @Valid GameRecordDto gameRecordDto){
        var gameModel = new GameModel();
        BeanUtils.copyProperties(gameRecordDto, gameModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(gameRepository.save(gameModel));
    }

    @GetMapping("/games")
    public ResponseEntity<List<GameModel>> getAllGames(){
        return ResponseEntity.status(HttpStatus.OK).body(gameRepository.findAll());
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<Object> getOneGame(@PathVariable(value="id")UUID id){
        Optional<GameModel> game = gameRepository.findById(id);
        if(game.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(game.get());

        //return game.<ResponseEntity<Object>>map(gameModel -> ResponseEntity.status(HttpStatus.OK).body(gameModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found!"));
    }

    @PutMapping("games/{id}")
    public ResponseEntity<Object> updateGame(@PathVariable(value="id") UUID id,
                                             @RequestBody @Valid GameRecordDto gameRecordDto){
        Optional<GameModel> game = gameRepository.findById(id);
        if(game.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found!");
        }
        var gameModel = game.get();
        BeanUtils.copyProperties(gameRecordDto, gameModel);
        return ResponseEntity.status(HttpStatus.OK).body(gameRepository.save(gameModel));
    }

    @DeleteMapping("games/{id}")
    public ResponseEntity<Object> deleteGame(@PathVariable(value="id") UUID id){
        Optional<GameModel> game = gameRepository.findById(id);
        if(game.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found!");
        }
        var gameModel = game.get();
        gameRepository.delete(game.get());
        return ResponseEntity.status(HttpStatus.OK).body("Game deleted successfully!");
    }
}
