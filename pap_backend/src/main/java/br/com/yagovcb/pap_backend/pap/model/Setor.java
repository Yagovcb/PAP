package br.com.yagovcb.pap_backend.pap.model;

import br.com.yagovcb.pap_backend.gefi.model.Funcionario;
import br.com.yagovcb.pap_backend.gefi.model.Loja;
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
import javax.persistence.JoinColumns;
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
@Table(name = "setor", schema = "pap")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Setor implements Serializable {

    private static final String SEQ_TB_SETOR = "SEQ_TB_SETOR";
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_TB_SETOR)
    @SequenceGenerator(name = SEQ_TB_SETOR, sequenceName = SEQ_TB_SETOR, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Id
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "id_loja")
    @Fetch(FetchMode.JOIN)
    private Loja loja;

    @Column(name = "contato", nullable = false)
    private String contato;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "codigo_legado", nullable = false)
    private Integer codigoLegado;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.MERGE)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name="id_chefe_setor"),
            @JoinColumn(name="id_loja_chefe_setor")
    })
    private Funcionario chefeSetor;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.MERGE)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name="id_email_setor"),
            @JoinColumn(name="id_loja_email_setor")
    })
    private Email email;
}
