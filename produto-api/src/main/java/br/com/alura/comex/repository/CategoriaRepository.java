package br.com.alura.comex.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.comex.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
  
    boolean existsByNome(String nome);
    Page<Categoria> findAllByAtivoTrue(Pageable paginação);
    
}

