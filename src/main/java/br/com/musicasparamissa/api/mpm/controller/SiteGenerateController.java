package br.com.musicasparamissa.api.mpm.controller;

import br.com.musicasparamissa.api.mpm.service.SiteGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("MpmSiteGenerateController")
    @RequestMapping("/mpm/site-generate")
public class SiteGenerateController {

    @Autowired
    private SiteGenerateService siteGenerateService;

    @PostMapping
    public ResponseEntity<Void> generateAll() {

        siteGenerateService.generateAll();

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/musica/{musica}")
    public ResponseEntity<Void> generateMusica(@PathVariable("musica") String musica) {

        siteGenerateService.generateMusica(musica);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/sugestoes-para/{artista}")
    public ResponseEntity<Void> generateSugestoesPara(@PathVariable("diaLiturgico") String diaLiturgico) {

        siteGenerateService.generateSugestoesPara(diaLiturgico);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/musicas-de/{categoria}")
    public ResponseEntity<Void> generateMusicasDe(@PathVariable("categoria") String categoria) {

        siteGenerateService.generateMusicasDe(categoria);

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
