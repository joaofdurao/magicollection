package br.com.magicollection.models.dto;

import br.com.magicollection.models.Card;
import br.com.magicollection.models.Player;
import br.com.magicollection.models.PlayerCards;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerCardsDTO {

    private Long id;
    private Integer quantity;
    private Card card;
    private Double price;
    private Player player;

    // toDTO
    public PlayerCardsDTO(PlayerCards playerCards) {
        this.id = playerCards.getId();
        this.quantity = playerCards.getQuantity();
        this.card = playerCards.getCard();
        this.price = playerCards.getPrice();
        this.player = playerCards.getPlayer();

    }

    // toCardPlayerCards
    public PlayerCards toPlayerCards(PlayerCardsDTO playerCardsDTO) {
        PlayerCards playerCards = new PlayerCards();
        playerCards.setId(playerCardsDTO.getId());
        playerCards.setQuantity(playerCardsDTO.getQuantity());
        playerCards.setCard(playerCardsDTO.getCard());
        playerCards.setPrice(playerCardsDTO.getPrice());
        playerCards.setPlayer(playerCardsDTO.getPlayer());

        return playerCards;
    }
}
