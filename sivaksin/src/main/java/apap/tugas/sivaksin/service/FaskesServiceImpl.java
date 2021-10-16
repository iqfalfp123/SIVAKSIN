package apap.tugas.sivaksin.service;

import apap.tugas.sivaksin.model.FaskesModel;
import apap.tugas.sivaksin.repository.FaskesDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FaskesServiceImpl implements FaskesService {
    @Autowired
    FaskesDB faskesDB;

    @Override
    public void addFaskes(FaskesModel faskes) {
        faskesDB.save(faskes);
    }

    @Override
    public void updateFaskes(FaskesModel faskes) {
        faskesDB.save(faskes);
    }

    @Override
    public List<FaskesModel> getFaskesList() {
        return faskesDB.findAll();
    }

    @Override
    public FaskesModel getFaskesByIdFaskes(Long idFaskes) {
        Optional<FaskesModel> faskes = faskesDB.findByIdFaskes(idFaskes);
        if (faskes.isPresent()) {
            return faskes.get();
        }
        return null;
    }

    @Override
    public void deleteFaskes(FaskesModel faskes) {
        faskesDB.delete(faskes);
    }

    // @Override
    // public List<FaskesModel> getFaskesListOrder() {
    // return faskesDB.findByOrderByNamaFaskesAsc();
    // }
}