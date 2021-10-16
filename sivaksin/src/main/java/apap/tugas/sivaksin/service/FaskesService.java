package apap.tugas.sivaksin.service;

import apap.tugas.sivaksin.model.FaskesModel;
import java.util.List;

public interface FaskesService {
    void addFaskes(FaskesModel faskes);

    void updateFaskes(FaskesModel faskes);

    List<FaskesModel> getFaskesList();

    FaskesModel getFaskesByIdFaskes(Long idFaskes);

    void deleteFaskes(FaskesModel faskes);

    // List<FaskesModel> getFaskesListOrder();

}