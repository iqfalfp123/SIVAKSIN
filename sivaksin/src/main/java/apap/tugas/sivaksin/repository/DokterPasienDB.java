package apap.tugas.sivaksin.repository;

import apap.tugas.sivaksin.model.DokterPasienModel;
import apap.tugas.sivaksin.model.FaskesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface DokterPasienDB extends JpaRepository<DokterPasienModel, Long> {
    Optional<DokterPasienModel> findByIdDokterPasien(Long idDokterPasien);

    // List<FaskesModel> findByIdFaskes(Long idFaskes);

}