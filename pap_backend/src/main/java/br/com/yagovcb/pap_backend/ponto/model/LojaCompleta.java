package br.com.yagovcb.pap_backend.ponto.model;

import br.com.yagovcb.pap_backend.gefi.model.Loja;
import br.com.yagovcb.pap_backend.pap.model.EnderecoPessoa;
import br.com.yagovcb.pap_backend.pap.model.PessoaJuridica;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

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
@Table(name = "loja_completa", schema = "pap")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LojaCompleta extends Loja {
    private static final String SEQ_TB_LOJA_COMPLETA = "SEQ_TB_LOJA_COMPLETA";
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_TB_LOJA_COMPLETA)
    @SequenceGenerator(name = SEQ_TB_LOJA_COMPLETA, sequenceName = SEQ_TB_LOJA_COMPLETA, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Id	@ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "id_loja")
    @Fetch(FetchMode.JOIN)
    private Loja loja;

    @Column(name = "data_inauguracao", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dataInauguracao;

    @Column(name = "data_fechamento", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dataFechamento;

    @Column(name = "data_inauguracao", nullable = false)
    private boolean emiteReciboEntrada;

    @Column(name = "data_inauguracao", nullable = false)
    private double percentualJurosAtraso;

    @Column(name = "data_inauguracao", nullable = false)
    private double percentualDescontoMaximo;

    @ManyToOne(fetch= FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name="id_saide"),
            @JoinColumn(name="id_loja_saide")
    })
    private Loja saiDe;

    @ManyToOne(fetch= FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name="id_abastecida_por"),
            @JoinColumn(name="id_loja_abastecida_por")
    })
    private Loja abastecidaPor;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "id_grupo_loja")
    private List<GrupoLoja> grupoLoja;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name="id_pessoa_juridica"),
            @JoinColumn(name="id_loja_pessoa_juridica")
    })
    @Fetch(FetchMode.JOIN)
    private PessoaJuridica pessoaJuridica;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name="id_endereco_pessoa"),
            @JoinColumn(name="id_loja_endereco_pessoa")
    })
    @Fetch(FetchMode.JOIN)
    private EnderecoPessoa enderecoLoja;

}
