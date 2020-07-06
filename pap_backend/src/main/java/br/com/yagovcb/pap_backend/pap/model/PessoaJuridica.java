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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
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
@Table(name = "pessoa_juridica", schema = "pap")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PessoaJuridica extends Pessoa {

    private static final long serialVersionUID = 1L;
    private static final String SEQ_TB_PESSOA_JURIDICA= "SEQ_TB_PESSOA_JURIDICA";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_TB_PESSOA_JURIDICA)
    @SequenceGenerator(name = SEQ_TB_PESSOA_JURIDICA, sequenceName = SEQ_TB_PESSOA_JURIDICA, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Id
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "id_loja")
    @Fetch(FetchMode.JOIN)
    private Loja loja;

    @Column(name = "inscricao_estadual", nullable = false)
    private String inscricaoEstadual;

    @Column(name = "cnpj", nullable = false)
    private String cnpj;

    @Column(name = "inscricao_municipal", nullable = false)
    private String inscricaoMunicipal;

    @Column(name = "codigo_suframa", nullable = false)
    private String codigoSuframa;

    @Lob
    @Column(name = "razao_social", nullable = false)
    private String razaoSocial;

    @Column(name = "site", nullable = false)
    private String site;

    @ManyToOne(fetch=FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name="id_agrupador"),
            @JoinColumn(name="id_loja_agrupador")
    })
    private PessoaJuridica agrupador;

    @Column(name = "coligada", nullable = false)
    private boolean coligada;

    @Column(name = "codigo_contabil", nullable = false)
    private Long codigoContabil;

    @Column(name = "renda_mensal", nullable = false)
    private double rendaMensal;
}
