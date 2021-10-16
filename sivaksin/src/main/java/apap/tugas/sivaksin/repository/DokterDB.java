package apap.tugas.sivaksin.repository;

import apap.tugas.sivaksin.model.DokterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface DokterDB extends JpaRepository<DokterModel, Long> {
    Optional<DokterModel> findByIdDokter(Long idDokter);

    // List<DokterModel> findByOrderByNamaDokterAsc();

}