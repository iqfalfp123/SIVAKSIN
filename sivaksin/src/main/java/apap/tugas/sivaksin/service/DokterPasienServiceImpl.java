package apap.tugas.sivaksin.service;

import apap.tugas.sivaksin.model.DokterPasienModel;
import apap.tugas.sivaksin.model.FaskesModel;
import apap.tugas.sivaksin.model.PasienModel;
import apap.tugas.sivaksin.repository.DokterPasienDB;
import apap.tugas.sivaksin.repository.FaskesDB;
import apap.tugas.sivaksin.repository.PasienDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DokterPasienServiceImpl implements DokterPasienService {
    @Autowired
    DokterPasienDB dokterpasienDB;
    @Autowired
    FaskesDB faskesDB;
    @Autowired
    PasienDB pasienDB;

    @Override
    public void addDokterPasien(DokterPasienModel dokterpasien, Long idFaskes) {
        FaskesModel faskes = faskesDB.findById(idFaskes).get();
        PasienModel pasien = dokterpasien.getPasien();
        faskes.getListPasien().add(pasien);
        pasien.getListFaskes().add(faskes);
        faskesDB.save(faskes);
        pasienDB.save(pasien);
        dokterpasienDB.save(dokterpasien);
    }

    @Override
    public void updateDokterPasien(DokterPasienModel dokterpasien) {
        dokterpasienDB.save(dokterpasien);
    }

    @Override
    public List<DokterPasienModel> getDokterPasienList() {
        return dokterpasienDB.findAll();
    }

    @Override
    public DokterPasienModel getDokterPasienByIdDokterPasien(Long idDokterPasien) {
        Optional<DokterPasienModel> dokterpasien = dokterpasienDB.findByIdDokterPasien(idDokterPasien);
        if (dokterpasien.isPresent()) {
            return dokterpasien.get();
        }
        return null;
    }

    @Override
    public void deleteDokterPasien(DokterPasienModel dokterpasien) {
        dokterpasienDB.delete(dokterpasien);
    }

    // @Override
    // public List<DokterPasienModel> getDokterPasienListOrder() {
    // return dokterpasienDB.findByOrderByNamaDokterPasienAsc();
    // }
}