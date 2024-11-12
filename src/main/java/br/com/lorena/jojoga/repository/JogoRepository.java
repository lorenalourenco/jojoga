package br.com.lorena.jojoga.repository;

import br.com.lorena.jojoga.model.Jogador;
import br.com.lorena.jojoga.model.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JogoRepository extends JpaRepository<Jogo, Integer> {
    @Query(value = "SELECT j.ganhador FROM Jogo j GROUP BY j.ganhador ORDER BY COUNT(j.ganhador) DESC")
    List<Jogador> findJogadoresComMaisVitorias();

    @Query(value = "SELECT COUNT(j) FROM Jogo j WHERE j.ganhador = :jogador")
    Integer countByGanhador(Jogador jogador);
}
