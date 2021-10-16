package apap.tugas.sivaksin.repository;

import apap.tugas.sivaksin.model.PasienModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface PasienDB extends JpaRepository<PasienModel, Long> {
    Optional<PasienModel> findByIdPasien(Long idPasien);

    // List<PasienModel> findByOrderByNamaPasienAsc();

}