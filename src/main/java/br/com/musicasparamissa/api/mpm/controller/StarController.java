package br.com.musicasparamissa.api.mpm.controller;

import br.com.musicasparamissa.api.exception.NotFoundException;
import br.com.musicasparamissa.api.mpm.dto.Star;
import br.com.musicasparamissa.api.mpm.dto.StarVote;
import br.com.musicasparamissa.api.mpm.entity.Musica;
import br.com.musicasparamissa.api.mpm.repository.MusicaRepository;
import br.com.musicasparamissa.api.mpm.service.SiteGenerateService;
import br.com.musicasparamissa.api.mpm.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/stars")
public class StarController {

    @Autowired
    private StarService starService;

    @Autowired
    private MusicaRepository musicaRepository;

    @Autowired
    private SiteGenerateService siteGenerateService;

    @GetMapping
    public ResponseEntity<Map<String, Star>> list(){
        return new ResponseEntity<>(starService.list(), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> vote(
            @PathVariable("id") String musicaSlug,
            @RequestParam("stars") int stars) {

        if(stars == 0)
            return new ResponseEntity <>("<html><head></head><body>0</body></html>", HttpStatus.OK);

        try{
            Musica musica = starService.vote(musicaSlug, stars);

            Map<String, StarVote> jsonObj = new HashMap <>();

            String legend = String.format(
                    "<span property='ratingValue'>%.2f</span> em <span property='ratingCount'>%d</span> voto%s",
                    musica.getRating() * 5 / 100.0, musica.getVotes(), musica.getVotes() > 1 ? "s" : "");

            jsonObj.put(musicaSlug, new StarVote(musica.getRating(), legend));

            siteGenerateService.generateStars(musicaRepository.findAll());
            siteGenerateService.generateOnlyMusica(musica, siteGenerateService.getContext());

            return new ResponseEntity<>(jsonObj, HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
