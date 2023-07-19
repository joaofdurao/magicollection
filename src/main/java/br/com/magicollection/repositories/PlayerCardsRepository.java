package br.com.magicollection.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.magicollection.models.PlayerCards;

public interface PlayerCardsRepository extends JpaRepository<PlayerCards, Long> {

    // FindByIdOptional
    @Query(value = "SELECT * FROM TB_PLAYERCARDS plc WHERE plc.ID = :id ", nativeQuery = true)
    Optional<PlayerCards> findByIdOptional(@Param("id") Long id);

    // FindPlayerCardId
    @Query(value = "SELECT plc.ID FROM TB_PLAYERCARDS plc WHERE fk_Player_ID = :playerId AND fk_Card_ID = :cardId", nativeQuery = true)
    Long findPlayerCardId(@Param("playerId") Long playerId, @Param("cardId") Long cardId);

    // ListByCardNameASC
    @Query(value = "SELECT plc.ID, plc.QUANTITY, plc.fk_Card_ID, plc.PRICE, plc.fk_Player_ID FROM TB_PLAYERCARDS plc JOIN TB_CARDS c ON plc.fk_Card_ID = c.ID ORDER BY c.NAME ASC", nativeQuery = true)
    List<PlayerCards> listByCardsNameASC();

    // ListByPriceASC
    @Query(value = "SELECT * FROM TB_PLAYERCARDS plc ORDER BY PRICE ASC", nativeQuery = true)
    List<PlayerCards> listByPriceASC();

    // ListbyPlayer
    @Query(value = "SELECT plc.ID, plc.QUANTITY, plc.fk_Card_ID, plc.PRICE, plc.fk_Player_ID FROM TB_PLAYERCARDS plc JOIN TB_PLAYERS p ON plc.fk_Player_ID = p.ID WHERE fk_Player_ID = :playerId", nativeQuery = true)
    List<PlayerCards> listByPlayer(Long playerId);

    // ListPlayerCardsByNameASC
    @Query(value = "SELECT plc.ID, plc.QUANTITY, plc.fk_Card_ID, plc.PRICE, plc.fk_Player_ID FROM TB_PLAYERCARDS plc JOIN TB_CARDS c ON plc.fk_Card_ID = c.ID JOIN TB_PLAYERS p ON plc.fk_Player_ID = p.ID WHERE fk_Player_ID = :playerId ORDER BY c.NAME ASC", nativeQuery = true)
    List<PlayerCards> listPlayerCardsByNameASC(@Param("playerId") Long playerId);

    // ListPlayercardsByPriceASC
    @Query(value = "SELECT plc.ID, plc.QUANTITY, plc.fk_Card_ID, plc.PRICE, plc.fk_Player_ID FROM TB_PLAYERCARDS plc JOIN TB_PLAYERS p ON plc.fk_Player_ID = p.ID WHERE fk_Player_ID = :playerId ORDER BY PRICE ASC", nativeQuery = true)
    List<PlayerCards> listPlayerCardsByPriceASC(@Param("playerId") Long playerId);

}
