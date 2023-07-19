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

import br.com.magicollection.models.dto.DeckCardsDTO;
import br.com.magicollection.services.DeckCardsService;

@RestController
@RequestMapping({ "/api/magiccollection" })
public class DeckCardsController {
    private DeckCardsService dkCService;

    public DeckCardsController(DeckCardsService dkCService) {
        this.dkCService = dkCService;
    }

    // ListAll
    @GetMapping(path = "/listDeckCards")
    public ResponseEntity<List<DeckCardsDTO>> listDeckCards(@RequestParam String listBy) {
        return ResponseEntity.status(HttpStatus.OK).body(dkCService.listDeckCards(listBy));
    }

    // ListAllbyDeck
    @GetMapping(path = "/listDeckCards/{deckId}")
    public ResponseEntity<List<DeckCardsDTO>> listDeckCards(@PathVariable Long deckId, @RequestParam String listBy) {
        return ResponseEntity.status(HttpStatus.OK).body(dkCService.listDeckCards(deckId, listBy));
    }

    // GetById
    @GetMapping(path = "/getDeckCards/{id}")
    public ResponseEntity<DeckCardsDTO> getDeckCardsById(@PathVariable Long id) {
        DeckCardsDTO dkCDto = dkCService.findDeckCardsByID(id);
        if (dkCDto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dkCDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Delete
    @DeleteMapping(path = "/deleteDeckCards/{id}")
    public ResponseEntity<DeckCardsDTO> deleteDeckCards(@PathVariable Long id) {

        try {
            this.dkCService.deleteDeckCards(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

}
