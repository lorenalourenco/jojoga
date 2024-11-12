package br.com.lorena.jojoga.service;

import br.com.lorena.jojoga.model.Dado;
import br.com.lorena.jojoga.model.Jogador;
import br.com.lorena.jojoga.model.Jogo;
import br.com.lorena.jojoga.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JogoService {
    @Autowired
    private JogoRepository jogoRepository;
    @Autowired
    private JogadorService jogadorService;

    public List<Jogo> getJogos() {
        return jogoRepository.findAll();
    }

    public Jogo createJogo(Jogo jogo) {

        List<Long> jogadores = jogo.getIdsParticipantes();
        jogo.setParticipantes(getParticipantes(jogadores));

        int dado1 = new Dado().rolar();
        int dado2 = new Dado().rolar();

        jogo.setDado1(dado1);
        jogo.setDado2(dado2);

        //find the winner of the game

        for (Jogador jogador : jogo.getParticipantes()) {
            if (jogador.getValorAposta() == dado1 + dado2) {
                jogo.setGanhador(jogador);
                break;
            }
        }

        jogoRepository.save(jogo);
        return jogo;
    }

    public List<Jogador> getParticipantes(List<Long> idsParticipantes) {
        List<Jogador> participantes = new ArrayList<>();
        for (Long id : idsParticipantes) {
            participantes.add(jogadorService.getJogador(Math.toIntExact(id)));
        }
        return participantes;
    }


public Map<Jogador, Integer> getJogadoresComMaisVitorias() {
    List<Jogador> jogadores = jogoRepository.findJogadoresComMaisVitorias();

    Map<Jogador, Integer> jogadoresComMaisVitorias = new HashMap<>();
    for (Jogador jogador : jogadores) {
        jogadoresComMaisVitorias.put(jogador, jogoRepository.countByGanhador(jogador));
    }

    Map<Jogador, Integer> jogadoresOrdenados = jogadoresComMaisVitorias.entrySet()
            .stream()
            .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
            .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (e1, e2) -> e1, // Para lidar com valores duplicados, se necessário
                    LinkedHashMap::new // Utilizando LinkedHashMap para manter a ordem
            ));

    return jogadoresOrdenados;
}

}
