package br.com.yagovcb.pap_backend.pap.model;

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
@Table(name = "pessoa_fisica", schema = "pap")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PessoaFisica extends Pessoa {

    private static final long serialVersionUID = 1L;
    private static final String SEQ_TB_PESSOA_FISICA = "SEQ_TB_PESSOA_FISICA";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_TB_PESSOA_FISICA)
    @SequenceGenerator(name = SEQ_TB_PESSOA_FISICA, sequenceName = SEQ_TB_PESSOA_FISICA, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Id
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "id_loja")
    @Fetch(FetchMode.JOIN)
    private Loja loja;

    @Column(name = "renda_mensal", nullable = false)
    private Double rendaMensal;

    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_estado_civil")
    @Fetch(FetchMode.JOIN)
    private EstadoCivil estadoCivil;

    @Column(name = "apelido", nullable = false)
    private String apelido;

    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_profissao")
    @Fetch(FetchMode.JOIN)
    private Profissao profissao;

    @ManyToOne(fetch=FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name="id_conjuge"),
            @JoinColumn(name="id_loja_conjuge")
    })
    private PessoaFisica conjuge;

    @Column(name = "falecido", nullable = false)
    private Boolean falecido;

    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_escolaridade")
    @Fetch(FetchMode.JOIN)
    private Escolaridade escolaridade;

    @ManyToOne(fetch=FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name="id_filiacao_pai"),
            @JoinColumn(name="id_loja_filiacao_pai")
    })
    private PessoaFisica filiacaoPai;

    @ManyToOne(fetch=FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name="id_filiacao_mae"),
            @JoinColumn(name="id_loja_filiacao_mae")
    })
    private PessoaFisica filiacaoMae;

    @Column(name = "numero_dependentes", nullable = false)
    private short numeroDependentes;
}
