package br.com.magicollection.models.dto;

import java.util.List;

import br.com.magicollection.models.Deck;
import br.com.magicollection.models.DeckCards;
import br.com.magicollection.models.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeckDTO {

    private Long id;
    private String deckName;
    private Player player;
    private List<DeckCards> deckCards;

    // toDTO
    public DeckDTO(Deck deck) {
        this.id = deck.getId();
        this.deckName = deck.getDeckName();
        this.player = deck.getPlayer();
        this.deckCards = deck.getDeckCards();
    }

    // DtoToDeck
    public Deck toDeck(DeckDTO deckDTO) {
        Deck deck = new Deck();
        deck.setId(deckDTO.getId());
        deck.setDeckName(deckDTO.getDeckName());
        deck.setPlayer(deckDTO.getPlayer());
        deck.setDeckCards(deckDTO.getDeckCards());
        return deck;
    }

}
