package br.com.magicollection.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_CARDS")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", length = 20)
    private String name;

    @Column(name = "TYPE", length = 20)
    private String type;

    @Column(name = "POWER")
    private Integer power;

    @Column(name = "TOUGHNESS")
    private Integer toughness;

    @Column(name = "FOIL")
    private Boolean foil;

    @Column(name = "EDITION", length = 30)
    private String edition;

    @Column(name = "LANGUAGE")
    private String language;

}
