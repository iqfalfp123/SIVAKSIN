package apap.tugas.sivaksin.controller;

import apap.tugas.sivaksin.model.*;
import apap.tugas.sivaksin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.time.LocalTime;

import java.util.List;

import javax.validation.constraints.Null;

import java.util.ArrayList;
@Controller
public class FaskesController {
    @Qualifier("faskesServiceImpl")
    @Autowired
    private FaskesService faskesService;
    private List<FaskesModel> newListFaskes = new ArrayList<FaskesModel>();
    private int value;

    @Qualifier("vaksinServiceImpl")
    @Autowired
    private VaksinService vaksinService;

    @Qualifier("dokterServiceImpl")
    @Autowired
    private DokterService dokterService;

    @Qualifier("pasienServiceImpl")
    @Autowired
    private PasienService pasienService;
    private List<PasienModel> listPasien = new ArrayList<PasienModel>();

    @GetMapping("/faskes")
    public String listFaskes(Model model) {
        List<FaskesModel> listFaskes = faskesService.getFaskesList();
        model.addAttribute("listFaskes", listFaskes);
        return "viewall-faskes";
    }
    
    @GetMapping("/faskes/tambah")
    public String addFaskesForm(Model model) {
        List<VaksinModel> listVaksin = vaksinService.getVaksinList();
        model.addAttribute("faskes", new FaskesModel());
        model.addAttribute("listVaksin", listVaksin);
        return "form-add-faskes";
    }
    
    @PostMapping("/faskes/tambah")
    public String addFaskesSubmit(@ModelAttribute FaskesModel faskes, Model model) {
        faskesService.addFaskes(faskes);
        model.addAttribute("namaFaskes", faskes.getNamaFaskes());
        return "add-faskes";
    }
    
    @GetMapping("/faskes/view")
    public String viewDetailFaskes(@RequestParam(value = "idFaskes") Long idFaskes, Model model) {
        FaskesModel faskes = faskesService.getFaskesByIdFaskes(idFaskes);
        List<PasienModel> listPasien = faskes.getListPasien();
        VaksinModel vaksin = faskes.getVaksin();
        model.addAttribute("faskes", faskes);
        model.addAttribute("listPasien", listPasien);
         model.addAttribute("vaksin", vaksin);
        return "view-faskes";
    }
    
    @GetMapping("/faskes/ubah/{idFaskes}")
    public String updateFaskesForm(@PathVariable Long idFaskes, Model model) {
        FaskesModel faskes = faskesService.getFaskesByIdFaskes(idFaskes);
        model.addAttribute("faskes", faskes);
        LocalTime time = LocalTime.now();
        List<VaksinModel> listVaksin = vaksinService.getVaksinList();
        model.addAttribute("listVaksin", listVaksin);
        if (time.isAfter(faskes.getJamMulai()) && time.isBefore(faskes.getJamTutup())) {
            model.addAttribute("text", "tidak dapat diupdate, karena faskes sedang beroperasi");
            return "update-faskes";
        }else if(faskes.getListPasien().size() != 0){
            return "form-update-faskes2";

        }else {
            return "form-update-faskes";
        }
    }
    
    @PostMapping("/faskes/ubah/{idFaskes}")
    public String updateFaskesSubmit(@PathVariable Long idFaskes, Model model) {
        FaskesModel faskes = faskesService.getFaskesByIdFaskes(idFaskes);
        model.addAttribute("idFaskes", faskes.getIdFaskes());
        faskesService.updateFaskes(faskes);
        model.addAttribute("text", "berhasil diupdate");
        return "update-faskes";
    }
    
    @PostMapping("/faskes/hapus/{idFaskes}")
    public String deleteFaskes(@PathVariable Long idFaskes, Model model) {
        FaskesModel faskes = faskesService.getFaskesByIdFaskes(idFaskes);
        model.addAttribute("faskes", faskes);
        LocalTime time = LocalTime.now();
        if (time.isAfter(faskes.getJamMulai()) && time.isBefore(faskes.getJamTutup())) {
            value = 1;
        }

        if (faskes.getListPasien().size() == 0 && value == 0) {
            faskesService.deleteFaskes(faskes);
            model.addAttribute("text", "berhasil dihapus");
        } else if(faskes.getListPasien().size() != 0){
            model.addAttribute("text", "tidak dapat dihapus karena faskes tersebut memiliki pasien");
            value = 0;
        }else {
            model.addAttribute("text", "tidak dapat dihapus karena faskes tersebut sedang beroperasi");
            value = 0;
        }
        return "delete-faskes";
    }
    
    // @GetMapping("/cari/faskes/")
    // public String findFaskes(Model model) {
    //     // FaskesModel faskes = faskesService.getFaskesByIdFaskes(idFaskes);
    //     List<VaksinModel> listVaksin = vaksinService.getVaksinList();
    //     model.addAttribute("faskes", new FaskesModel());
    //     model.addAttribute("listVaksin", listVaksin);
    //     return "find-faskes-by-vaccine";
    // }
    @GetMapping("/cari/faskes")
     public String findFaskesByVaccine(@RequestParam(value = "jenisVaksin", required = false) String jenisVaksin, Model model) {
         newListFaskes.clear();
         List<VaksinModel> listVaksin = vaksinService.getVaksinList();
         List<FaskesModel> listFaskes = faskesService.getFaskesList();
         for(FaskesModel x : listFaskes){
            if(x.getVaksin().getJenisVaksin().equals(jenisVaksin)) {
                newListFaskes.add(x);
            }
         }
        model.addAttribute("listFaskes", newListFaskes);
        model.addAttribute("listVaksin", listVaksin);
        return "find-faskes-by-vaccine";
     }
     
    //  @GetMapping("/cari/pasien/")
    //  public String findPasienByVaccineAndFaskes(Model model) {
    //      List<VaksinModel> listVaksin = vaksinService.getVaksinList();
    //      List<FaskesModel> listFaskes = faskesService.getFaskesList();
    //      model.addAttribute("listFaskes", listFaskes);
    //      model.addAttribute("listVaksin", listVaksin);
    //      return "find-pasien";
    //  }
     
     @GetMapping("/cari/pasien")
     public String findPasien(@RequestParam(value = "jenisVaksin", required = false) String jenisVaksin, @RequestParam(value = "namaFaskes", required = false) String namaFaskes, Model model) {
        listPasien.clear();
        List<VaksinModel> listVaksin = vaksinService.getVaksinList();
        List<FaskesModel> listFaskes = faskesService.getFaskesList();
        List<PasienModel> temp = new ArrayList<PasienModel>();
        List<DokterPasienModel> lstdp = new ArrayList<DokterPasienModel>();
        // DokterPasienModel dp = dokterPasienService.get
        if(jenisVaksin == null) {
            for (FaskesModel x : listFaskes) {
                if (x.getNamaFaskes().equals(namaFaskes)) {
                    temp = x.getListPasien();
                }
                for (PasienModel y : temp) {
                    listPasien.add(y);
                }
                temp.clear();
            }
        }else if(namaFaskes == null) {
            for (VaksinModel i : listVaksin) {
                if (i.getJenisVaksin().equalsIgnoreCase(jenisVaksin)) {
                    for (FaskesModel j : i.getListFaskes()) {
                        temp = j.getListPasien();
                        for (PasienModel k : temp) {
                            listPasien.add(k);
                        }
                    }
                }
                temp.clear();
            }
        }
        else{
            for (FaskesModel x : listFaskes) {
                if (x.getNamaFaskes().equals(namaFaskes) & x.getVaksin().getJenisVaksin().equals(jenisVaksin)) {
                    temp = x.getListPasien();
                }
                for (PasienModel y : temp) {
                    listPasien.add(y);
                }
                temp.clear();
            }  
        }
        for(PasienModel lp : listPasien){
            for(DokterPasienModel dp : lp.getListDokterPasien()){
                lstdp.add(dp);
            }
        }
        model.addAttribute("listPasien", listPasien);
        model.addAttribute("listVaksin", listVaksin);
        model.addAttribute("listFaskes", listFaskes);
        model.addAttribute("dp", lstdp);

        return "find-pasien";
    }
}
