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

import br.com.magicollection.models.dto.DeckDTO;
import br.com.magicollection.services.DeckService;

@RestController
@RequestMapping({ "/api/magiccollection" })
public class DeckController {
    private DeckService dkService;

    public DeckController(DeckService dkService) {
        this.dkService = dkService;
    }

  // Create
  @PostMapping(path = "/createDeck/{playerId}")
  public @ResponseBody ResponseEntity<DeckDTO> createDeck(@PathVariable Long playerId, @RequestBody DeckDTO deckDTO) {
      return ResponseEntity.status(HttpStatus.CREATED).body(this.dkService.createDeck(playerId, deckDTO));

  }

    // ListAll
    @GetMapping(path = "/listDeck")
    public ResponseEntity<List<DeckDTO>> listDecks() {
        return ResponseEntity.status(HttpStatus.OK).body(dkService.listDecks());
    }

    // GetById
    @GetMapping(path = "/getDeck/{id}")
    public ResponseEntity<DeckDTO> getDeckById(@PathVariable Long id) {
        DeckDTO dkDto = dkService.findDeckByID(id);
        if (dkDto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dkDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Update
    @PutMapping(path = "/updateDeck")
    public ResponseEntity<DeckDTO> updateDeck(@RequestBody DeckDTO pLayerDTO) {

        try {
            this.dkService.updateDeck(pLayerDTO);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    // Delete
    @DeleteMapping(path = "/deleteDeck/{id}")
    public ResponseEntity<DeckDTO> deleteDeck(@PathVariable Long id) {

        try {
            this.dkService.deleteDeck(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    // UpdateDeckCards
    @PutMapping(path = "/updateDeckCards/{deckId}/{cardId}/{quantity}")
    public ResponseEntity<DeckDTO> updateDeckCards(@PathVariable Long deckId,
            @PathVariable Long cardId, @PathVariable Integer quantity) {
        try {
            this.dkService.updateDeckCards(deckId, cardId, quantity);
            return ResponseEntity.status(HttpStatus.OK).body(this.dkService.findDeckByID(deckId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }
}
