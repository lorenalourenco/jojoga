package br.com.lorena.jojoga.controller;

import br.com.lorena.jojoga.service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ranking")
public class RankingController {
    @Autowired
    private JogoService jogoService;

    @RequestMapping("/list")
    public String listRanking(Model model) {
        model.addAttribute("jogadores", jogoService.getJogadoresComMaisVitorias());

        return "ranking/listaRanking";
    }
}
