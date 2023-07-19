package br.com.magicollection.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.magicollection.models.Card;
import br.com.magicollection.models.Deck;
import br.com.magicollection.models.DeckCards;
import br.com.magicollection.models.PlayerCards;
import br.com.magicollection.models.dto.DeckDTO;
import br.com.magicollection.repositories.CardRepository;
import br.com.magicollection.repositories.DeckCardsRepository;
import br.com.magicollection.repositories.DeckRepository;
import br.com.magicollection.repositories.PlayerCardsRepository;
import br.com.magicollection.repositories.PlayerRepository;

@Service
public class DeckService {

    private DeckRepository dkRepository;

    public DeckService(DeckRepository dkRepository) {
        this.dkRepository = dkRepository;
    }

    @Autowired
    private CardRepository cdRepository;

    @Autowired
    private DeckCardsRepository dkCRepository;

    @Autowired
    private PlayerCardsRepository plCRepository;

    @Autowired
    private PlayerRepository plRepository;

    // Create
    @Transactional
    public DeckDTO createDeck(Long playerId, DeckDTO deckDTO) {

        Deck deck = deckDTO.toDeck(deckDTO);
        deck.setPlayer(this.plRepository.findById(playerId).orElse(null));
        return new DeckDTO(this.dkRepository.save(deck));
    }

    // List
    @Transactional
    public List<DeckDTO> listDecks() {
        List<Deck> dkList = this.dkRepository.findAll();

        return dkList.stream().map(dk -> new DeckDTO(dk)).toList();
    }

    // FindById
    @Transactional
    public DeckDTO findDeckByID(Long id) {
        return new DeckDTO(this.dkRepository.findById(id).orElse(null));
    }

    // Update
    @Transactional
    public void updateDeck(DeckDTO deckDTO) {
        Optional<Deck> optionalDeck = this.dkRepository.findById(deckDTO.getId());
        if (optionalDeck.isPresent()) {
            this.dkRepository.updateDeck(deckDTO.getDeckName(), deckDTO.getId());;
        }

    }

    // Delete
    @Transactional
    public void deleteDeck(Long id) {
        DeckDTO optionalPlDTO = new DeckDTO(this.dkRepository.findById(id).orElse(null));
        if (optionalPlDTO != null) {
            this.dkRepository.deleteById(optionalPlDTO.getId());
        }
    }

    // UpdateDeckCards
    @Transactional
    public void updateDeckCards(Long deckId, Long cardId, Integer quantity) {
        Optional<Deck> optionalDeck = this.dkRepository.findById(deckId);
        Optional<Card> optionalCard = this.cdRepository.findById(cardId);
        Optional<PlayerCards> optionalPlayerCard = this.plCRepository
                .findByIdOptional(this.plCRepository.findPlayerCardId(this.dkRepository.deckPlayerId(), cardId));

        if (optionalDeck.isPresent() && optionalCard.isPresent() && optionalPlayerCard.isPresent()) {
            Deck upDeck = optionalDeck.get();
            Card addedCard = optionalCard.get();
            PlayerCards baseCard = optionalPlayerCard.get();
            Optional<DeckCards> optionalDeckCard = this.dkCRepository
                    .findByIdOptional(this.dkCRepository.findDeckCardId(deckId, cardId));

            if (optionalDeckCard.isPresent()) {
                if (quantity == 0) {
                    DeckCards updatedDeckCards = optionalDeckCard.get();
                    this.dkCRepository.delete(updatedDeckCards);
                } else if (baseCard.getQuantity() >= quantity) {
                    DeckCards updatedDeckCards = optionalDeckCard.get();
                    updatedDeckCards.setQuantity(quantity);
                    this.dkCRepository.save(updatedDeckCards);
                }

            } else {
                if (baseCard.getQuantity() >= quantity) {
                    DeckCards addedDeckCard = new DeckCards(quantity, addedCard, upDeck);
                    List<DeckCards> cardList = upDeck.getDeckCards();
                    cardList.add(addedDeckCard);
                    upDeck.setDeckCards(cardList);
                    this.dkRepository.save(upDeck);
                }

            }

        }

    }

}
