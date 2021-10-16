package apap.tugas.sivaksin.service;

import apap.tugas.sivaksin.model.DokterModel;
import apap.tugas.sivaksin.repository.DokterDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DokterServiceImpl implements DokterService {
    @Autowired
    DokterDB dokterDB;

    @Override
    public void addDokter(DokterModel dokter) {
        dokterDB.save(dokter);
    }

    @Override
    public void updateDokter(DokterModel dokter) {
        dokterDB.save(dokter);
    }

    @Override
    public List<DokterModel> getDokterList() {
        return dokterDB.findAll();
    }

    @Override
    public DokterModel getDokterByIdDokter(Long idDokter) {
        Optional<DokterModel> dokter = dokterDB.findByIdDokter(idDokter);
        if (dokter.isPresent()) {
            return dokter.get();
        }
        return null;
    }

    @Override
    public void deleteDokter(DokterModel dokter) {
        dokterDB.delete(dokter);
    }

    // @Override
    // public List<DokterModel> getDokterListOrder() {
    // return dokterDB.findByOrderByNamaDokterAsc();
    // }
}