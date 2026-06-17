package com.bad.solve.repository;

import com.bad.solve.entity.DetalleCatalogo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DetalleCatalogoRepository extends JpaRepository<DetalleCatalogo, Long> {
    List<DetalleCatalogo> findByCatalogoCodCatOrderByOrdenAsc(Long codCat);
}
