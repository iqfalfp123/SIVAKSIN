package apap.tugas.sivaksin.service;

import apap.tugas.sivaksin.model.VaksinModel;
import apap.tugas.sivaksin.repository.VaksinDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VaksinServiceImpl implements VaksinService {
    @Autowired
    VaksinDB vaksinDB;

    @Override
    public void addVaksin(VaksinModel vaksin) {
        vaksinDB.save(vaksin);
    }

    @Override
    public void updateVaksin(VaksinModel vaksin) {
        vaksinDB.save(vaksin);
    }

    @Override
    public List<VaksinModel> getVaksinList() {
        return vaksinDB.findAll();
    }

    @Override
    public VaksinModel getVaksinByIdVaksin(Long idVaksin) {
        Optional<VaksinModel> vaksin = vaksinDB.findByIdVaksin(idVaksin);
        if (vaksin.isPresent()) {
            return vaksin.get();
        }
        return null;
    }

    @Override
    public void deleteVaksin(VaksinModel vaksin) {
        vaksinDB.delete(vaksin);
    }

    // @Override
    // public List<VaksinModel> getVaksinListOrder() {
    // return vaksinDB.findByOrderByNamaVaksinAsc();
    // }
}