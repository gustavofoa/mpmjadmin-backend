package br.com.musicasparamissa.mpmjadmin.backend.service;

import br.com.musicasparamissa.mpmjadmin.backend.entity.Musica;
import br.com.musicasparamissa.mpmjadmin.backend.entity.SugestaoMusica;
import br.com.musicasparamissa.mpmjadmin.backend.repository.ItemLiturgiaRepository;
import br.com.musicasparamissa.mpmjadmin.backend.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class MusicaService {
	
	@Autowired
	private MusicaRepository musicaRepository;

	@Autowired
	private ItemLiturgiaRepository itemLiturgiaRepository;

	public Set<Musica> search(String filter){
		return musicaRepository.findBySlugIgnoreCaseContainingOrNomeIgnoreCaseContainingOrLetraIgnoreCaseContaining(filter, filter, filter);
	}

	public Page<Musica> filter(String filter, Pageable pageable) {
		return musicaRepository.findBySlugIgnoreCaseContainingOrNomeIgnoreCaseContainingOrLetraIgnoreCaseContaining(filter, filter, filter, pageable);
	}

	@Transactional
	public void save(Musica musica) {
		musicaRepository.save(musica);
	}

    @Transactional
    public void delete(Musica musica) {
        Set<SugestaoMusica> sugestoesMusica = itemLiturgiaRepository.findByMusica(musica);
        for (SugestaoMusica sugestaoMusica : sugestoesMusica) {
            sugestaoMusica.getAvulsas().remove(musica);
            sugestaoMusica.getRemover().remove(musica);
            itemLiturgiaRepository.save(sugestaoMusica);
        }
        musicaRepository.delete(musica);
    }

	public boolean exists(String slug) {
		return musicaRepository.exists(slug);
	}
	
}
