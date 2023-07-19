package br.com.magicollection.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.magicollection.models.DeckCards;

public interface DeckCardsRepository extends JpaRepository<DeckCards, Long> {

    // FindByIdOptional
    @Query(value = "SELECT * FROM TB_DECKCARDS dkC WHERE dkC.ID = :id ", nativeQuery = true)
    Optional<DeckCards> findByIdOptional(@Param("id") Long id);

    // FindDeckCardId
    @Query(value = "SELECT dkC.ID FROM TB_DECKCARDS dkC WHERE fk_Deck_ID = :deckId AND fk_Card_ID = :cardId", nativeQuery = true)
    Long findDeckCardId(@Param("deckId") Long deckId, @Param("cardId") Long cardId);

    // ListByPriceASC
    @Query(value = "SELECT * FROM TB_DECKCARDS dkc ORDER BY PRICE ASC", nativeQuery = true)
    List<DeckCards> listByPriceASC();

    // ListByCardNameASC
    @Query(value = "SELECT dkc.ID, dkc.QUANTITY, dkc.fk_Card_ID, dkc.fk_Deck_ID FROM TB_DECKCARDS dkc JOIN TB_CARDS c ON dkc.fk_Card_ID = c.ID ORDER BY c.NAME ASC", nativeQuery = true)
    List<DeckCards> listByCardsNameASC();

    // ListbyDeck
    @Query(value = "SELECT dkc.ID, dkc.QUANTITY, dkc.fk_Card_ID, dkc.fk_Deck_ID FROM TB_DECKCARDS dkc JOIN TB_DECKS d ON dkc.fk_Deck_ID = d.ID WHERE fk_Deck_ID = :deckId", nativeQuery = true)
    List<DeckCards> listByDeck(Long deckId);

    // ListDeckCardsByNameASC
    @Query(value = "SELECT dkc.ID, dkc.QUANTITY, dkc.fk_Card_ID, dkc.fk_Deck_ID FROM TB_DECKCARDS dkc JOIN TB_CARDS c ON dkc.fk_Card_ID = c.ID JOIN TB_DECKS d ON dkc.fk_Deck_ID = d.ID WHERE fk_Deck_ID = :deckId ORDER BY c.NAME ASC", nativeQuery = true)
    List<DeckCards> listDeckCardsByNameASC(@Param("deckId") Long deckId);

}
