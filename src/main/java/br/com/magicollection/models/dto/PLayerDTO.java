package br.com.magicollection.models.dto;

import java.util.List;

import br.com.magicollection.models.Deck;
import br.com.magicollection.models.Player;
import br.com.magicollection.models.PlayerCards;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PLayerDTO {

    private Long id;
    private String username;
    private String email;
    private String password;
    private String gender;
    private List<Deck> Decks;
    private List<PlayerCards> playerCards;

    // toDTO
    public PLayerDTO(Player player) {
        this.id = player.getId();
        this.username = player.getUsername();
        this.email = player.getEmail();
        this.password = player.getPassword();
        this.gender = player.getGender();
        this.Decks = player.getDecks();
        this.playerCards = player.getPlayerCards();
    }

    // toPlayer
    public Player toPlayer(PLayerDTO playerDTO) {
        Player player = new Player();
        player.setId(playerDTO.getId());
        player.setUsername(playerDTO.getUsername());
        player.setEmail(playerDTO.getEmail());
        player.setPassword(playerDTO.getPassword());
        player.setGender(playerDTO.getGender());
        player.setDecks(playerDTO.getDecks());
        player.setPlayerCards(playerDTO.getPlayerCards());
        return player;
    }

}
