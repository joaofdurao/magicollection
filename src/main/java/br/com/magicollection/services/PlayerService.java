package br.com.magicollection.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.magicollection.models.Card;
import br.com.magicollection.models.Player;
import br.com.magicollection.models.PlayerCards;
import br.com.magicollection.models.dto.PLayerDTO;
import br.com.magicollection.repositories.CardRepository;
import br.com.magicollection.repositories.PlayerCardsRepository;
import br.com.magicollection.repositories.PlayerRepository;

@Service
public class PlayerService {

    private PlayerRepository plRepository;

    public PlayerService(PlayerRepository plRepository) {
        this.plRepository = plRepository;
    }

    @Autowired
    private CardRepository cdRepository;

    @Autowired
    private PlayerCardsRepository plCRepository;

    // Create
    @Transactional
    public PLayerDTO createPlayer(PLayerDTO playerDTO) {
        Player player = playerDTO.toPlayer(playerDTO);
        return new PLayerDTO(this.plRepository.save(player));
    }

    // List
    @Transactional
    public List<PLayerDTO> listPlayers() {
        List<Player> plList = this.plRepository.findAll();
        return plList.stream().map(pl -> new PLayerDTO(pl)).toList();
    }

    // FindById
    public PLayerDTO findPlayerByID(Long id) {
        return new PLayerDTO(this.plRepository.findById(id).orElse(null));
    }

    // Update
    @Transactional
    public void updatePlayer(PLayerDTO playerDTO) {
        Optional<Player> optionalPlayer = this.plRepository.findById(playerDTO.getId());
        if (optionalPlayer.isPresent()) {
            this.plRepository.updatePlayer(playerDTO.getUsername(), playerDTO.getEmail(), playerDTO.getPassword(),
                    playerDTO.getGender(), playerDTO.getId());
        }
    }

    // Delete
    @Transactional
    public void deletePlayer(Long id) {
        PLayerDTO optionalPlDTO = new PLayerDTO(this.plRepository.findById(id).orElse(null));
        if (optionalPlDTO != null) {
            this.plRepository.deleteById(optionalPlDTO.getId());
        }
    }

    // UpdatePlayerCards
    @Transactional
    public void updatePlayerCards(Long playerId, Long cardId, Integer quantity, Double price) {
        Optional<Player> optionalPlayer = this.plRepository.findById(playerId);
        Optional<Card> optionalCard = this.cdRepository.findById(cardId);

        if (optionalPlayer.isPresent() && optionalCard.isPresent()) {
            Player upPlayer = optionalPlayer.get();
            Card addedCard = optionalCard.get();
            Optional<PlayerCards> optionalPlayerCard = this.plCRepository
                    .findByIdOptional(this.plCRepository.findPlayerCardId(playerId, cardId));

            if (optionalPlayerCard.isPresent()) {
                if (quantity == 0) {
                    PlayerCards updatedPlayerCards = optionalPlayerCard.get();
                    this.plCRepository.delete(updatedPlayerCards);
                } else {
                    PlayerCards updatedPlayerCards = optionalPlayerCard.get();
                    updatedPlayerCards.setQuantity(quantity);
                    updatedPlayerCards.setPrice(price);
                    this.plCRepository.save(updatedPlayerCards);
                }

            } else {
                PlayerCards addedPlayerCard = new PlayerCards(quantity, addedCard, price, upPlayer);
                List<PlayerCards> cardList = upPlayer.getPlayerCards();
                cardList.add(addedPlayerCard);
                upPlayer.setPlayerCards(cardList);
                this.plRepository.save(upPlayer);
            }

        }

    }
}