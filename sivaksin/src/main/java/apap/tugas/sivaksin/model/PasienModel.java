package apap.tugas.sivaksin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "pasien")
public class PasienModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPasien;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama_pasien", nullable = false)
    private String namaPasien;

    @NotNull
    @Size(max = 16)
    @Column(name = "nik", nullable = false)
    private String nik;

    @NotNull
    @Size(max = 13)
    @Column(name = "nomor_telepon", nullable = false)
    private String nomorTelepon;

    @NotNull
    @Column(name = "jenis_kelamin", nullable = false)
    private int jenisKelamin;

    @NotNull
    @Column(name = "tanggal_lahir", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime tanggalLahir;

    @NotNull
    @Size(max = 255)
    @Column(name = "tempat_lahir", nullable = false)
    private String tempatLahir;

    @NotNull
    @Size(max = 255)
    @Column(name = "pekerjaan", nullable = false)
    private String pekerjaan;

    @ManyToMany(mappedBy = "listPasien")
    List<FaskesModel> listFaskes;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pasien")
    private List<DokterPasienModel> listDokterPasien;
}
