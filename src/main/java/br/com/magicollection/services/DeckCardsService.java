package br.com.magicollection.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.magicollection.models.DeckCards;
import br.com.magicollection.models.dto.DeckCardsDTO;
import br.com.magicollection.repositories.DeckCardsRepository;
import jakarta.transaction.Transactional;

@Service
public class DeckCardsService {

    private DeckCardsRepository dkCRepository;

    public DeckCardsService(DeckCardsRepository dkCRepository) {
        this.dkCRepository = dkCRepository;
    }

    // List
    @Transactional
    public List<DeckCardsDTO> listDeckCards(String listBy) {

        if (listBy.equalsIgnoreCase("name")) {
            List<DeckCards> dkCList = this.dkCRepository.listByCardsNameASC();
            return dkCList.stream().map(dkC -> new DeckCardsDTO(dkC)).toList();
        } else {
            List<DeckCards> dkCList = this.dkCRepository.findAll();
            return dkCList.stream().map(dkC -> new DeckCardsDTO(dkC)).toList();
        }
    }

    // FindByDeckCards
    @Transactional
    public List<DeckCardsDTO> listDeckCards(Long deckId, String listBy) {

        if (listBy.equalsIgnoreCase("name")) {
            List<DeckCards> dkCList = this.dkCRepository.listDeckCardsByNameASC(deckId);
            return dkCList.stream().map(dkC -> new DeckCardsDTO(dkC)).toList();
        } else {
            List<DeckCards> dkCList = this.dkCRepository.listByDeck(deckId);
            return dkCList.stream().map(dkC -> new DeckCardsDTO(dkC)).toList();
        }
    }

    // FindById
    @Transactional
    public DeckCardsDTO findDeckCardsByID(Long id) {
        return new DeckCardsDTO(this.dkCRepository.findById(id).orElse(null));
    }

    // Delete
    @Transactional
    public void deleteDeckCards(Long id) {
        DeckCardsDTO optionaldkCDTO = new DeckCardsDTO(this.dkCRepository.findById(id).orElse(null));
        if (optionaldkCDTO != null) {
            this.dkCRepository.deleteById(optionaldkCDTO.getId());
        }
    }

}
