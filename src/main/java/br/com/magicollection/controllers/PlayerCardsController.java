package br.com.magicollection.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.magicollection.models.dto.PlayerCardsDTO;
import br.com.magicollection.services.PlayerCardsService;

@RestController
@RequestMapping({ "/api/magiccollection" })
public class PlayerCardsController {
    private PlayerCardsService plCService;

    public PlayerCardsController(PlayerCardsService plCService) {
        this.plCService = plCService;
    }

    // ListAll
    @GetMapping(path = "/listPlayerCards")
    public ResponseEntity<List<PlayerCardsDTO>> listPlayerCards(@RequestParam String listBy) {
        return ResponseEntity.status(HttpStatus.OK).body(plCService.listPlayerCards(listBy));
    }

    // ListAllbyPlayer
    @GetMapping(path = "/listPlayerCards/{playerId}")
    public ResponseEntity<List<PlayerCardsDTO>> listPlayerCards(@PathVariable Long playerId,
            @RequestParam String listBy) {
        return ResponseEntity.status(HttpStatus.OK).body(plCService.listPlayerCards(playerId, listBy));
    }

    // GetById
    @GetMapping(path = "/getPlayerCards/{id}")
    public ResponseEntity<PlayerCardsDTO> getPlayerCardsById(@PathVariable Long id) {
        PlayerCardsDTO plCDto = plCService.findPlayerCardsByID(id);
        if (plCDto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(plCDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Delete
    @DeleteMapping(path = "/deletePlayerCards/{id}")
    public ResponseEntity<PlayerCardsDTO> deletePlayerCards(@PathVariable Long id) {

        try {
            this.plCService.deletePlayerCards(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }
}
