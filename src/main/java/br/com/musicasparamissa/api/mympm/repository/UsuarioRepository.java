package br.com.musicasparamissa.api.mympm.repository;

import br.com.musicasparamissa.api.mympm.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("MyMpMUsuarioRepository")
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    @Query("select u from mympm_usuario u where " +
            "(u.email like %?1% or u.nome like %?1%) " +
            "and u.premium = ?2 " +
            "order by u.dataCompra desc")
    Page<Usuario> search(String filtro, Boolean premium, Pageable pageable);

    @Query("select u from mympm_usuario u where " +
            "(u.email like %?1% or u.nome like %?1%) " +
            "and u.premium = ?2 " +
            "and u.dataExpiracao < ?3 " +
            "order by u.dataCompra desc")
    Page<Usuario> searchExpired(String filtro, Boolean premium, Date expired, Pageable pageable);

    List<Usuario> findAllByOrderByNome();

    @Procedure
    void updateMauticSegments();

}
