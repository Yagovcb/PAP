package br.com.yagovcb.pap_backend.gefi.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "loja", schema = "pap")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Loja implements Serializable {

    private static final String SEQ_TB_LOJA = "SEQ_TB_LOJA";
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_TB_LOJA)
    @SequenceGenerator(name = SEQ_TB_LOJA, sequenceName = SEQ_TB_LOJA, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "sigla", nullable = false)
    private String sigla;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "id_tipo_loja", referencedColumnName = "id")
    private TipoLoja tipoLoja;
}
