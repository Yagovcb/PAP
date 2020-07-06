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
@Table(name = "telefone", schema = "pap")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Telefone implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String SEQ_TB_TELEFONE = "SEQ_TB_TELEFONE";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_TB_TELEFONE)
    @SequenceGenerator(name = SEQ_TB_TELEFONE, sequenceName = SEQ_TB_TELEFONE, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Id
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "id_loja")
    @Fetch(FetchMode.JOIN)
    private Loja loja;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "ramal")
    private String ramal;

    @Column(name = "ddd", nullable = false, length = 2)
    private String ddd;

    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_tipo_telefone")
    @Fetch(FetchMode.JOIN)
    private TipoTelefone tipoTelefone;

    @Column(name = "atual", nullable = false)
    private boolean atual;
}
