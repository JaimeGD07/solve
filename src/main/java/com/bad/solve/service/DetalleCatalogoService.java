package com.bad.solve.service;

import com.bad.solve.dto.DetalleCatalogoRequest;
import com.bad.solve.entity.Catalogo;
import com.bad.solve.entity.DetalleCatalogo;
import com.bad.solve.repository.CatalogoRepository;
import com.bad.solve.repository.DetalleCatalogoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class DetalleCatalogoService {
    private final DetalleCatalogoRepository repository;
    private final CatalogoRepository catalogoRepository;

    public DetalleCatalogoService(DetalleCatalogoRepository repository, CatalogoRepository catalogoRepository) {
        this.repository = repository;
        this.catalogoRepository = catalogoRepository;
    }

    public List<DetalleCatalogo> listar() {
        return repository.findAll();
    }

    public List<DetalleCatalogo> listarPorCatalogo(Long codCat) {
        return repository.findByCatalogoCodCatOrderByOrdenAsc(codCat);
    }

    public DetalleCatalogo obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Detalle de catálogo no encontrado: " + id));
    }

    @Transactional
    public DetalleCatalogo crear(DetalleCatalogoRequest request) {
        DetalleCatalogo detalle = new DetalleCatalogo();
        aplicarDatos(detalle, request);
        return repository.save(detalle);
    }

    @Transactional
    public DetalleCatalogo actualizar(Long id, DetalleCatalogoRequest request) {
        DetalleCatalogo detalle = obtener(id);
        aplicarDatos(detalle, request);
        return repository.save(detalle);
    }

    @Transactional
    public void eliminar(Long id) {
        repository.delete(obtener(id));
    }

    private void aplicarDatos(DetalleCatalogo detalle, DetalleCatalogoRequest request) {
        Catalogo catalogo = catalogoRepository.findById(request.getCodCat())
                .orElseThrow(() -> new EntityNotFoundException("Catálogo no encontrado: " + request.getCodCat()));
        detalle.setCatalogo(catalogo);
        detalle.setEtiqueta(request.getEtiqueta());
        detalle.setValor(request.getValor());
        detalle.setOrden(request.getOrden());
    }
}
