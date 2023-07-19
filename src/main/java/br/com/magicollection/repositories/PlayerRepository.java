package br.com.magicollection.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.magicollection.models.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    //UpdatePlayer
    @Modifying
    @Query(value = "UPDATE TB_PLAYERS p SET p.USERNAME = :pUsername, p.EMAIL = :pEmail, p.PASSWORD = :pPassword, p.GENDER = :pGender WHERE p.ID = :pId", nativeQuery = true)
    void updatePlayer(@Param("pUsername") String pUsername, @Param("pEmail") String pEmail, @Param("pPassword") String pPassword, @Param("pGender") String pGender, @Param("pId") Long pId);

}
