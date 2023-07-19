package br.com.magicollection.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.magicollection.models.Card;

public interface CardRepository extends JpaRepository<Card, Long> {


}
