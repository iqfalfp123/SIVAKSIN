package apap.tugas.sivaksin.service;

import apap.tugas.sivaksin.model.DokterPasienModel;
import java.util.List;

public interface DokterPasienService {
    void addDokterPasien(DokterPasienModel dokterpasien, Long idFaskes);

    void updateDokterPasien(DokterPasienModel dokterpasien);

    List<DokterPasienModel> getDokterPasienList();

    DokterPasienModel getDokterPasienByIdDokterPasien(Long idDokterPasien);

    void deleteDokterPasien(DokterPasienModel dokterpasien);

    // List<DokterPasienModel> getDokterPasienListOrder();

}