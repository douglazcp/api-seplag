package br.gov.mt.apiseplag.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "foto_pessoa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FotoPessoa {
    @Id
    @Column(name = "fp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "pes_id")
    @JsonIgnore
    private Pessoa pessoa;

    @Column(name = "fp_data")
    private LocalDate data;

    @Column(name = "fp_bucket")
    private String bucket;

    @Column(name = "fp_hash")
    private String hash;

    @Transient
    private String foto; //base64

    @Transient
    private List<String> fotos;

}
