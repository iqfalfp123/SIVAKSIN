package apap.tugas.sivaksin.controller;

import apap.tugas.sivaksin.model.*;
import apap.tugas.sivaksin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

@Controller
public class DokterPasienController {
    @Qualifier("dokterPasienServiceImpl")
    @Autowired
    private DokterPasienService dokterPasienService;

    @Qualifier("faskesServiceImpl")
    @Autowired
    private FaskesService faskesService;

    @Qualifier("dokterServiceImpl")
    @Autowired
    private DokterService dokterService;

    @Qualifier("pasienServiceImpl")
    @Autowired
    private PasienService pasienService;

    @GetMapping("/faskes/{idFaskes}/tambah/pasien")
    public String listDokterPasien(@PathVariable Long idFaskes, Model model) {
        List<DokterModel> listDokter = dokterService.getDokterList();
        List<PasienModel> listPasien = pasienService.getPasienList();
        model.addAttribute("dokterPasien", new DokterPasienModel());
        model.addAttribute("listDokter", listDokter);
        model.addAttribute("listPasien", listPasien);
        model.addAttribute("idFaskes", idFaskes);
        return "form-add-pasien-faskes";
    }
    
    @PostMapping("/faskes/{idFaskes}/tambah/pasien")
    public String tambahPasienFaskes(@PathVariable Long idFaskes, @ModelAttribute DokterPasienModel dokterPasien, Model model) {
        FaskesModel faskes = faskesService.getFaskesByIdFaskes(idFaskes);

        LocalTime waktuSuntik = dokterPasien.getWaktuSuntik().toLocalTime();
        model.addAttribute("namaPasien", dokterPasien.getPasien().getNamaPasien());
        model.addAttribute("namaFaskes", faskes.getNamaFaskes());
        if (waktuSuntik.isAfter(faskes.getJamMulai()) && waktuSuntik.isBefore(faskes.getJamTutup())) {
            String batch = BatchID(dokterPasien);
            dokterPasien.setIdFaskes(idFaskes);
            dokterPasien.setBatchId(batch); 
            dokterPasienService.addDokterPasien(dokterPasien, idFaskes);
            model.addAttribute("text", "berhasil dilakukan");
        }
        else {
            model.addAttribute("text", "tidak berhasil dilakukan, karena waktu suntik yang dimasukkan tidak sesuai dengan jam operasional");
        }
        return "add-pasien-faskes"; 
    }

    public String BatchID(DokterPasienModel dokterPasien) {
        String char1 = "";
        if (dokterPasien.getPasien().getJenisKelamin() == 1) {
            char1 = "1";
        } else {
            char1 = "2";
        }
        String namaPasien = dokterPasien.getPasien().getNamaPasien().toUpperCase();
        String char2 = namaPasien.substring(namaPasien.length() - 1);
        String char34 = dokterPasien.getPasien().getTempatLahir().substring(0, 2).toUpperCase();
        String char56 = String.valueOf(dokterPasien.getPasien().getTanggalLahir()).substring(5, 7);
        String char78 = String.valueOf(dokterPasien.getPasien().getTanggalLahir()).substring(8, 10);
        String charYear = String.valueOf(dokterPasien.getPasien().getTanggalLahir()).substring(0, 4);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String char1213 = "";
        Random random = new Random();
        int length = 2;
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            char1213 += randomChar;
        }

        int value = Integer.valueOf(charYear);
        value /= 10;
        String char911 = String.valueOf((int) Math.floor(value));
        String batch = char1 + char2 + char34 + char56 + char78 + char911 + char1213;
        return batch;
    }
}
