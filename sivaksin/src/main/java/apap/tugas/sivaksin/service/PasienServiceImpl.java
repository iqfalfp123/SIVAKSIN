package apap.tugas.sivaksin.service;

import apap.tugas.sivaksin.model.PasienModel;
import apap.tugas.sivaksin.repository.PasienDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PasienServiceImpl implements PasienService {
    @Autowired
    PasienDB pasienDB;

    @Override
    public void addPasien(PasienModel pasien) {
        pasienDB.save(pasien);
    }

    @Override
    public void updatePasien(PasienModel pasien) {
        pasienDB.save(pasien);
    }

    @Override
    public List<PasienModel> getPasienList() {
        return pasienDB.findAll();
    }

    @Override
    public PasienModel getPasienByIdPasien(Long idPasien) {
        Optional<PasienModel> pasien = pasienDB.findByIdPasien(idPasien);
        if (pasien.isPresent()) {
            return pasien.get();
        }
        return null;
    }

    @Override
    public void deletePasien(PasienModel pasien) {
        pasienDB.delete(pasien);
    }

    // @Override
    // public List<PasienModel> getPasienListOrder() {
    //     return pasienDB.findByOrderByNamaPasienAsc();
    // }
}