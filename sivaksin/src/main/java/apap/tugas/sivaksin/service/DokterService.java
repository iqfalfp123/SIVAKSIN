package apap.tugas.sivaksin.service;

import apap.tugas.sivaksin.model.DokterModel;
import java.util.List;

public interface DokterService {
    void addDokter(DokterModel dokter);

    void updateDokter(DokterModel dokter);

    List<DokterModel> getDokterList();

    DokterModel getDokterByIdDokter(Long idDokter);

    void deleteDokter(DokterModel dokter);

    // List<DokterModel> getDokterListOrder();

}