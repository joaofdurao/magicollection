package br.com.magicollection.models.dto;

import br.com.magicollection.models.Card;
import br.com.magicollection.models.Deck;
import br.com.magicollection.models.DeckCards;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeckCardsDTO {

    private Long id;
    private Integer quantity;
    private Card card;
    private Deck deck;

    // toDTO
    public DeckCardsDTO(DeckCards deckCards) {
        this.id = deckCards.getId();
        this.quantity = deckCards.getQuantity();
        this.card = deckCards.getCard();
        this.deck = deckCards.getDeck();

    }

    // toCardDeckCards
    public DeckCards toDeckCards(DeckCardsDTO deckCardsDTO) {
        DeckCards deckCards = new DeckCards();
        deckCards.setId(deckCardsDTO.getId());
        deckCards.setQuantity(deckCardsDTO.getQuantity());
        deckCards.setCard(deckCardsDTO.getCard());
        deckCards.setDeck(deckCardsDTO.getDeck());

        return deckCards;
    }
}
