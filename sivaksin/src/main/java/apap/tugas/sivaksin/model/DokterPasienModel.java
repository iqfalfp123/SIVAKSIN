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
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "dokter_pasien")
public class DokterPasienModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDokterPasien;

    @NotNull
    @Size(max = 255)
    @Column(name = "batch_id", nullable = false)
    private String batchId;

    @NotNull
    @Column(name = "waktu_suntik", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime waktuSuntik;

    @NotNull
    @Column(name = "id_faskes", nullable = false)
    private Long idFaskes;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idPasien", referencedColumnName = "idPasien", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PasienModel pasien;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idDokter", referencedColumnName = "idDokter", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DokterModel dokter;



}
