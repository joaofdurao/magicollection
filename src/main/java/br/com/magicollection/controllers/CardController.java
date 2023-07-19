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

import br.com.magicollection.models.dto.CardDTO;
import br.com.magicollection.services.CardService;

@RestController
@RequestMapping({ "/api/magiccollection" })
public class CardController {
      private CardService cdService;

    public CardController(CardService cdService) {
        this.cdService = cdService;
    }

    // Create
    @PostMapping(path = "/createCard")
    public @ResponseBody ResponseEntity<CardDTO> createCard(@RequestBody CardDTO cardDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.cdService.createCard(cardDTO));

    }

    // ListAll
    @GetMapping(path = "/listCard")
    public ResponseEntity<List<CardDTO>> listCards() {
        return ResponseEntity.status(HttpStatus.OK).body(cdService.listCards());
    }

    // GetById
    @GetMapping(path = "/getCard/{id}")
    public ResponseEntity<CardDTO> getCardById(@PathVariable Long id) {
        CardDTO cdDto = cdService.findCardByID(id);
        if (cdDto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cdDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Update
    @PutMapping(path = "/updateCard")
    public ResponseEntity<CardDTO> updateCard(@RequestBody CardDTO pLayerDTO) {

        try {
            this.cdService.updateCard(pLayerDTO);
            return ResponseEntity.status(HttpStatus.OK).body(this.cdService.findCardByID(pLayerDTO.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        
    }

    // Delete
    @DeleteMapping(path = "/deleteCard/{id}")
    public ResponseEntity<CardDTO> deleteCard(@PathVariable Long id) {

        try {
            this.cdService.deleteCard(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }
}
