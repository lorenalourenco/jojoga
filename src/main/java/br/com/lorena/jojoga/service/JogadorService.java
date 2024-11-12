package br.com.lorena.jojoga.service;


import br.com.lorena.jojoga.model.Jogador;
import br.com.lorena.jojoga.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;

    public List<Jogador> getJogadores() {
        return jogadorRepository.findAll();
    }

    public Jogador getJogador(Integer id) {
        return jogadorRepository.findById(id).map(jogador -> {
            return jogador;
        }).orElseThrow(() -> null);
    }

    public void createJogador(Jogador jogador) {
        jogadorRepository.save(jogador);
    }

    public void updateJogador(Integer id, String nome, int valorAposta) {
        Jogador jogador = this.getJogador(id);
        jogador.setNome(nome);
        jogador.setValorAposta(valorAposta);
        jogadorRepository.save(jogador);
    }

    public void deleteJogador(Integer id) {
        jogadorRepository.deleteById(id);
    }
}
