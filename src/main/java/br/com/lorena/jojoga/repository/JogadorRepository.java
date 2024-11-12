package br.com.lorena.jojoga.repository;

import br.com.lorena.jojoga.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadorRepository extends JpaRepository<Jogador, Integer> {
}
