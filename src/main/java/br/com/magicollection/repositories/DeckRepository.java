package br.com.magicollection.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.magicollection.models.Deck;

public interface DeckRepository extends JpaRepository<Deck, Long> {

    // FindPlayerId
    @Query(value = "SELECT fk_Player_ID FROM TB_DECKS", nativeQuery = true)
    Long deckPlayerId();
    
    //UpdateDeck
    @Modifying
    @Query(value = "UPDATE TB_DECKS d SET d.DECKNAME = :dDeckname WHERE d.ID = :dId", nativeQuery = true)
    void updateDeck(@Param("dDeckname") String dDeckname, @Param("dId") Long dId);

}

