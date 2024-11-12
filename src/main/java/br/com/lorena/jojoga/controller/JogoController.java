package br.com.lorena.jojoga.controller;

import br.com.lorena.jojoga.model.Jogo;
import br.com.lorena.jojoga.service.JogadorService;
import br.com.lorena.jojoga.service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/jogo")
public class JogoController {
    @Autowired
    private JogoService jogoService;
    @Autowired
    private JogadorService jogadorService;

    @GetMapping("/form")
    public String showFormJogo(Model model) {
        Jogo jogo = new Jogo();
        model.addAttribute("jogo", jogo);
        model.addAttribute("jogadores", jogadorService.getJogadores());
        return "jogos/jogoForm";
    }

    @PostMapping("/salvar")
    public String salvarJogo(@ModelAttribute("jogo") Jogo jogo, Model model) {
        //Lista de jogadores selecionados
        Jogo resultado = jogoService.createJogo(jogo);
        model.addAttribute("jogo", resultado);
        return "jogos/resultadoJogo";
    }






}
