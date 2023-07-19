package br.com.magicollection.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.magicollection.models.Card;
import br.com.magicollection.models.dto.CardDTO;
import br.com.magicollection.repositories.CardRepository;
import jakarta.transaction.Transactional;

@Service
public class CardService {
   
   
    private CardRepository cdRepository;

    public CardService(CardRepository cdRepository) {
        this.cdRepository = cdRepository;
    }

    // Create
    @Transactional
    public CardDTO createCard(CardDTO cardDTO) {
        Card card = cardDTO.toCard(cardDTO);
        return new CardDTO(this.cdRepository.save(card));
    }

    // List
    @Transactional
    public List<CardDTO> listCards() {
        List<Card> cdList = this.cdRepository.findAll();
        List<CardDTO> cdDTOList = new ArrayList<>();

        for (Card card : cdList) {
            CardDTO cdDTO = new CardDTO(card);
            cdDTOList.add(cdDTO);
        }
        return cdDTOList;
    }

    // FindById
    public CardDTO findCardByID(Long id) {
        return new CardDTO(this.cdRepository.findById(id).orElse(null));
    }

    // Update
    @Transactional
    public void updateCard(CardDTO cardDTO) {
        Optional<Card> optionalCard = this.cdRepository.findById(cardDTO.getId());
            if (optionalCard.isPresent()) {
                    Card card = cardDTO.toCard(cardDTO);
                    this.cdRepository.save(card);
        }

    }

    // Delete
    @Transactional
    public void deleteCard(Long id) {
        CardDTO optionalPlDTO = new CardDTO(this.cdRepository.findById(id).orElse(null));
        if (optionalPlDTO != null) {
            this.cdRepository.deleteById(optionalPlDTO.getId());
        }
    }
}
