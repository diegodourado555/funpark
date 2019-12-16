package br.com.repository;
import br.com.domain.MenuPerfil;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MenuPerfil entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MenuPerfilRepository extends JpaRepository<MenuPerfil, Long> {

}
