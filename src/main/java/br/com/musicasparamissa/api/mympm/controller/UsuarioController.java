package br.com.musicasparamissa.api.mympm.controller;

import br.com.musicasparamissa.api.mympm.entity.Compra;
import br.com.musicasparamissa.api.mympm.entity.Usuario;
import br.com.musicasparamissa.api.mympm.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("MyMpMUsuarioController")
@RequestMapping("/mympm/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> list() {

        return usuarioService.list();

    }

    @GetMapping("/{id}/compras")
    public List<Compra> getCompras(@PathVariable("id") Long idUsuario) {

        return usuarioService.listCompras(idUsuario);

    }

    @PutMapping("/{id}")
    public void save(@PathVariable Long id, @RequestBody Usuario usuario) {

        usuarioService.save(id, usuario);

    }

    @PutMapping("/update-mautic-segments")
    public void updateMauticSegments() {

        usuarioService.updateMauticSegments();

    }

    @GetMapping("/search")
    public Page<Usuario> search(@RequestParam("filter") String filter,
                                @RequestParam("premium") Boolean premium,
                                @PageableDefault(value = Integer.MAX_VALUE) Pageable pageable) {

        return usuarioService.search(filter, premium, pageable);

    }

    @PutMapping("/{id}/premium/{premium}")
    public void setProcessed(
            @PathVariable("id") Long id,
            @PathVariable("premium") Boolean premium) {

        usuarioService.updatePremium(id, premium);

    }

}
