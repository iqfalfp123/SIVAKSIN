package apap.tugas.sivaksin.service;

import apap.tugas.sivaksin.model.VaksinModel;
import java.util.List;

public interface VaksinService {
    void addVaksin(VaksinModel vaksin);

    void updateVaksin(VaksinModel vaksin);

    List<VaksinModel> getVaksinList();

    VaksinModel getVaksinByIdVaksin(Long idVaksin);

    void deleteVaksin(VaksinModel vaksin);

    // List<VaksinModel> getVaksinListOrder();

}