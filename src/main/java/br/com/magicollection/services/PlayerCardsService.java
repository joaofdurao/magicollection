package br.com.magicollection.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.magicollection.models.PlayerCards;
import br.com.magicollection.models.dto.PlayerCardsDTO;
import br.com.magicollection.repositories.PlayerCardsRepository;
import jakarta.transaction.Transactional;

@Service
public class PlayerCardsService {

    private PlayerCardsRepository plCRepository;

    public PlayerCardsService(PlayerCardsRepository plCRepository) {
        this.plCRepository = plCRepository;
    }

    // List
    @Transactional
    public List<PlayerCardsDTO> listPlayerCards(String listBy) {

        if (listBy.equalsIgnoreCase("price")) {
            List<PlayerCards> plCList = this.plCRepository.listByPriceASC();
            return plCList.stream().map(plC -> new PlayerCardsDTO(plC)).toList();
        } else if (listBy.equalsIgnoreCase("name")) {
            List<PlayerCards> plCList = this.plCRepository.listByCardsNameASC();
            return plCList.stream().map(plC -> new PlayerCardsDTO(plC)).toList();
        } else {
            List<PlayerCards> plCList = this.plCRepository.findAll();
            return plCList.stream().map(plC -> new PlayerCardsDTO(plC)).toList();
        }

    }

    // FindByPlayerCards
    @Transactional
    public List<PlayerCardsDTO> listPlayerCards(Long playerId, String listBy) {

        if (listBy.equalsIgnoreCase("price")) {
            List<PlayerCards> plCList = this.plCRepository.listPlayerCardsByPriceASC(playerId);
            return plCList.stream().map(plC -> new PlayerCardsDTO(plC)).toList();
        } else if (listBy.equalsIgnoreCase("name")) {
            List<PlayerCards> plCList = this.plCRepository.listPlayerCardsByNameASC(playerId);
            return plCList.stream().map(plC -> new PlayerCardsDTO(plC)).toList();
        } else {
            List<PlayerCards> plCList = this.plCRepository.listByPlayer(playerId);
            return plCList.stream().map(plC -> new PlayerCardsDTO(plC)).toList();
        }

    }

    // FindById
    @Transactional
    public PlayerCardsDTO findPlayerCardsByID(Long id) {
        return new PlayerCardsDTO(this.plCRepository.findById(id).orElse(null));
    }

    // Delete
    @Transactional
    public void deletePlayerCards(Long id) {
        PlayerCardsDTO optionalPlCDTO = new PlayerCardsDTO(this.plCRepository.findById(id).orElse(null));
        if (optionalPlCDTO != null) {
            this.plCRepository.deleteById(optionalPlCDTO.getId());
        }
    }

}
