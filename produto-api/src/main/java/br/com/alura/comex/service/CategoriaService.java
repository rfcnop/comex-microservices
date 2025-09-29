package br.com.alura.comex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alura.comex.dto.ResponseCategoriaDto;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.repository.CategoriaRepository;
import jakarta.transaction.Transactional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Transactional
    public void cadastrar(Categoria categoria) {
        if (categoria == null)
            return;
        if (repository.existsByNome(categoria.getNome()))
            return;

        repository.save(categoria);
    }

    public Page<ResponseCategoriaDto> listar(Pageable paginação) {
        return repository.findAllByAtivoTrue(paginação).map(ResponseCategoriaDto::new);
    }

    public Categoria buscaPorId(Long idDaCategoria) {
        var optional = repository.findById(idDaCategoria);
        if (!optional.isPresent())
            return null;
        
        return optional.get();
    }
  
}
