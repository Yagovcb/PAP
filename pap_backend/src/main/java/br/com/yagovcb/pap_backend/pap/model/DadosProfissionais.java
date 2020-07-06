package br.com.yagovcb.pap_backend.pap.model;

import br.com.yagovcb.pap_backend.gefi.model.Loja;
import com.fasterxml.jackson.annotation.JsonFormat;
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
import java.util.Date;

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
@Table(name = "profissao", schema = "pap")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DadosProfissionais {

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

    @ManyToOne(fetch=FetchType.LAZY, targetEntity=PessoaFisica.class)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name="id_pessoa_fisica"),
            @JoinColumn(name="id_loja_pessoa_fisica")
    })
    private PessoaFisica owner;

    private String localTrabalho;

    private Date dataAdmissao;

    private Date dataDemissao;

    private String profissaoLegado;

    private String atividadeProfissionalLegado;

    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_profissao")
    @Fetch(FetchMode.JOIN)
    private Profissao profissao;

    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_atividade_profissional")
    @Fetch(FetchMode.JOIN)
    private AtividadeProfissional atividadeProfissional;

    @Column(name = "renda_mensal", nullable = false)
    private double rendaMensal;

    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    @Column(name = "data_inclusao", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dataInclusao;

    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name="id_endereco"),
            @JoinColumn(name="id_loja_endereco")
    })
    private EnderecoPessoa endereco;
}
