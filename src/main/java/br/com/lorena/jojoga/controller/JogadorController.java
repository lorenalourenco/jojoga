package br.com.lorena.jojoga.controller;

import br.com.lorena.jojoga.model.Jogador;
import br.com.lorena.jojoga.service.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jogador")
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    @RequestMapping("/lista")
    public String listJogadores(Model model) {
        model.addAttribute("jogadores", jogadorService.getJogadores());
        return "jogador/listaJogadores";
    }

    @RequestMapping(value = {"/form", "/form/{id}"})
    public String showFormJogador(@PathVariable(required = false) Integer id, Model model) {
        if (id != null) {
            model.addAttribute("jogador", jogadorService.getJogador(id));
            model.addAttribute("action", "edit");
        } else {
            model.addAttribute("jogador", new Jogador());
            model.addAttribute("action", "create");
        }

        return "jogador/formJogador";
    }

    @RequestMapping("/create")
    public String createJogador(Jogador jogador) {
        jogadorService.createJogador(jogador);
        return "redirect:/jogador/lista";
    }

    @RequestMapping("/edit/{id}")
    public String editJogador(Jogador jogador, @PathVariable int id) {
        jogadorService.updateJogador(
            jogador.getId(),
            jogador.getNome(),
            jogador.getValorAposta()
        );

        return "redirect:/jogador/lista";
    }

    @RequestMapping("/delete/{id}")
    public String deleteJogador(@PathVariable int id) {
        jogadorService.deleteJogador(id);
        return "redirect:/jogador/lista";
    }


}
