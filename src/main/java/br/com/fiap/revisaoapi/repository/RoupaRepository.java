package br.com.fiap.revisaoapi.repository;
import br.com.fiap.revisaoapi.model.Roupa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoupaRepository extends JpaRepository<Roupa, Long> {
    
}
