package apap.tugas.sivaksin.controller;

import apap.tugas.sivaksin.model.*;
import apap.tugas.sivaksin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@Controller
public class PasienController {
    @Qualifier("pasienServiceImpl")
    @Autowired
    private PasienService pasienService;

    @Qualifier("faskesServiceImpl")
    @Autowired
    private FaskesService faskesService;

    @Qualifier("dokterServiceImpl")
    @Autowired
    private DokterService dokterService;

    @Qualifier("vaksinServiceImpl")
    @Autowired
    private VaksinService vaksinService;

    @GetMapping("/pasien")
    public String listPasien(Model model) {
        List<PasienModel> listPasien = pasienService.getPasienList();
        model.addAttribute("listPasien", listPasien);
        return "viewall-pasien";
    }
    
    @GetMapping("/pasien/tambah")
    public String addPasienForm(Model model) {
        model.addAttribute("pasien", new PasienModel());
        return "form-add-pasien";
    }
    
    @PostMapping("/pasien/tambah")
    public String addPasienSubmit(@ModelAttribute PasienModel pasien, Model model) {
        pasienService.addPasien(pasien);
        model.addAttribute("namaPasien", pasien.getNamaPasien());
        return "add-pasien";
    }
    
    @GetMapping("/pasien/{idPasien}")
    public String viewDetailPasien(@PathVariable Long idPasien, Model model) {
        PasienModel pasien = pasienService.getPasienByIdPasien(idPasien);
        List<DokterPasienModel> listDokterPasien = pasien.getListDokterPasien();
        FaskesModel faskes = new FaskesModel();
        DokterModel dokter = new DokterModel();
        model.addAttribute("pasien", pasien);
        model.addAttribute("dp", listDokterPasien);
        model.addAttribute("listFaskes", pasien.getListFaskes());
        return "view-pasien";
    }
    
    @GetMapping("/pasien/ubah/{idPasien}")
    public String updatePasienForm(@PathVariable Long idPasien, Model model) {
        PasienModel pasien = pasienService.getPasienByIdPasien(idPasien);
        model.addAttribute("pasien", pasien);
        return "form-update-pasien";
    }
    
    @PostMapping("/pasien/ubah")
    public String updatePasienSubmit(@ModelAttribute PasienModel pasien, Model model) {
        model.addAttribute("idPasien", pasien.getIdPasien());
        pasienService.updatePasien(pasien);
        model.addAttribute("namaPasien", pasien.getNamaPasien());
        return "update-pasien";
    }
    @GetMapping("/cari/jumlah-pasien/bulan-ini")
    public String findPasienThisMonth(Model model){
        int count = 0;
        LocalDateTime now = LocalDateTime.now();
        int skrg = now.getMonthValue();
        List<PasienModel> pasien = pasienService.getPasienList();
        List<DokterPasienModel> dp = new ArrayList<DokterPasienModel>();
        for(PasienModel x: pasien){
           dp = x.getListDokterPasien();
           for(DokterPasienModel y: dp){
               if(y.getWaktuSuntik().toLocalDate().getMonthValue() == skrg){
                   count++;
               }
           }
        }
        System.out.println(count);
        return "home";
    }

}