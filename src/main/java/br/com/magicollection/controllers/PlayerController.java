package br.com.magicollection.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.magicollection.models.dto.PLayerDTO;
import br.com.magicollection.services.PlayerService;

@RestController
@RequestMapping({ "/api/magiccollection" })
public class PlayerController {

    private PlayerService plService;

    public PlayerController(PlayerService plService) {
        this.plService = plService;
    }

    // Create
    @PostMapping(path = "/createPlayer")
    public @ResponseBody ResponseEntity<PLayerDTO> createPlayer(@RequestBody PLayerDTO playerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.plService.createPlayer(playerDTO));

    }

    // ListAll
    @GetMapping(path = "/listPlayer")
    public ResponseEntity<List<PLayerDTO>> listPlayers() {
        return ResponseEntity.status(HttpStatus.OK).body(plService.listPlayers());
    }

    // GetById
    @GetMapping(path = "/getPlayer/{id}")
    public ResponseEntity<PLayerDTO> getPlayerById(@PathVariable Long id) {
        PLayerDTO plDto = plService.findPlayerByID(id);
        if (plDto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(plDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Update
    @PutMapping(path = "/updatePlayer")
    public ResponseEntity<PLayerDTO> updatePlayer(@RequestBody PLayerDTO pLayerDTO) {

        try {
            this.plService.updatePlayer(pLayerDTO);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        

    }

    // Delete
    @DeleteMapping(path = "/deletePlayer/{id}")
    public ResponseEntity<PLayerDTO> deletePlayer(@PathVariable Long id) {

        try {
            this.plService.deletePlayer(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    // UpdatePlayerCards
    @PutMapping(path = "/updatePlayerCards/{playerId}/{cardId}/{quantity}/{price}")
    public ResponseEntity<PLayerDTO> updatePlayerCards(@PathVariable Long playerId,
            @PathVariable Long cardId, @PathVariable Integer quantity, @PathVariable Double price) {
        try {
            this.plService.updatePlayerCards(playerId, cardId, quantity, price);
            return ResponseEntity.status(HttpStatus.OK).body(this.plService.findPlayerByID(playerId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }
}
