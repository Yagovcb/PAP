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
@Table(name = "documento", schema = "pap")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Documento implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String SEQ_TB_DOCUMENTO = "SEQ_TB_DOCUMENTO";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_TB_DOCUMENTO)
    @SequenceGenerator(name = SEQ_TB_DOCUMENTO, sequenceName = SEQ_TB_DOCUMENTO, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_tipo_documento")
    @Fetch(FetchMode.JOIN)
    private TipoDocumento tipoDocumento;

    @Column(name = "numero", nullable = false)
    private String numero;

    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_uf_emissor")
    @Fetch(FetchMode.JOIN)
    private Uf ufEmissor;

    @Column(name = "atual", nullable = false)
    private Boolean atual;
}
