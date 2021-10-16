package apap.tugas.sivaksin.repository;

import apap.tugas.sivaksin.model.FaskesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface FaskesDB extends JpaRepository<FaskesModel, Long> {
    Optional<FaskesModel> findByIdFaskes(Long idFaskes);

    // List<FaskesModel> findByOrderByNamaFaskesAsc();

}