package apap.tugas.sivaksin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "vaksin")
public class VaksinModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idVaksin;

    @NotNull
    @Column(name = "efikasi_vaksin", nullable = false)
    private double efikasiVaksin;

    @NotNull
    @Size(max = 255)
    @Column(name = "jenis_vaksin", nullable = false)
    private String jenisVaksin;

    @NotNull
    @Size(max = 255)
    @Column(name = "asal_negara", nullable = false)
    private String asalNegara;

    @OneToMany(mappedBy = "vaksin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<FaskesModel> listFaskes;
}
