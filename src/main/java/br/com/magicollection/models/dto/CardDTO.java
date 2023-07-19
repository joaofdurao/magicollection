package br.com.magicollection.models.dto;

import br.com.magicollection.models.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {

    private Long id;
    private String name;
    private String type;
    private Integer power;
    private Integer toughness;
    private Boolean foil;
    private String edition;
    private String language;

    // toDTO
    public CardDTO(Card card) {
        this.id = card.getId();
        this.name = card.getName();
        this.type = card.getType();
        this.power = card.getPower();
        this.toughness = card.getToughness();
        this.foil = card.getFoil();
        this.edition = card.getEdition();
        this.language = card.getLanguage();
    }

    // toCard
    public Card toCard(CardDTO cardDTO) {
        Card card = new Card();
        card.setId(cardDTO.getId());
        card.setName(cardDTO.getName());
        card.setType(cardDTO.getType());
        card.setPower(cardDTO.getPower());
        card.setToughness(cardDTO.getToughness());
        card.setFoil(cardDTO.getFoil());
        card.setEdition(cardDTO.getEdition());
        card.setLanguage(cardDTO.getLanguage());

        return card;
    }

}
