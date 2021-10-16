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
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "faskes")
public class FaskesModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFaskes;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama_faskes", nullable = false)
    private String namaFaskes;

    @NotNull
    @Size(max = 255)
    @Column(name = "kabupaten_faskes", nullable = false)
    private String kabupatenFaskes;

    @NotNull
    @Size(max = 255)
    @Column(name = "provinsi_faskes", nullable = false)
    private String provinsiFaskes;

    @NotNull
    @Column(name = "kuota_faskes", nullable = false)
    private int kuotaFaskes;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime jamMulai;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime jamTutup;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name ="idVaksin", referencedColumnName = "idVaksin", nullable = false)
    private VaksinModel vaksin;

    @ManyToMany
    @JoinTable(
        name = "faskes_pasien", 
        joinColumns = @JoinColumn(name = "id_faskes"), 
        inverseJoinColumns = @JoinColumn(name = "id_pasien"))
    List<PasienModel> listPasien;
}