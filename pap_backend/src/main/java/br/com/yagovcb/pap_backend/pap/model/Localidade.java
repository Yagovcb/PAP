package br.com.yagovcb.pap_backend.pap.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author yagovcb
 * @since 04/07/2020
 * */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "localidade", schema = "pap")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Localidade implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String SEQ_TB_LOCALIDADE = "SEQ_TB_LOCALIDADE";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_TB_LOCALIDADE)
    @SequenceGenerator(name = SEQ_TB_LOCALIDADE, sequenceName = SEQ_TB_LOCALIDADE, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_dne", nullable = false)
    private int idDne;

    @Column(name = "codigo_ibge", nullable = false)
    private int codigoIbge;

    @Column(name = "nome", nullable = false)
    private String nome;

    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_uf")
    @Fetch(FetchMode.JOIN)
    private Uf uf;
}
