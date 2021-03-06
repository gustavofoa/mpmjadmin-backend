package br.com.musicasparamissa.api.mympm.repository;

import br.com.musicasparamissa.api.mympm.entity.Repertorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("MyMpMRepertorioRepository")
public interface RepertorioRepository extends CrudRepository<Repertorio, Long> {

    Page<Repertorio> findByTituloIgnoreCaseContainingOrderByIdDesc(String filter, Pageable pageable);

    List<Repertorio> findByUsuarioId(Long idUsuario);

}
