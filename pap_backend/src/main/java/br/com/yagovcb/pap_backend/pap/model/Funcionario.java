package br.com.yagovcb.pap_backend.pap.model;


import br.com.yagovcb.pap_backend.gefi.model.HistoricoFuncionario;
import br.com.yagovcb.pap_backend.ponto.model.HorarioSemana;
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
import java.io.Serializable;
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
@Table(name = "funcionario", schema = "pap")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Funcionario implements Serializable {
    private static final String SEQ_TB_FUNCIONARIO = "SEQ_TB_FUNCIONARIO";
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_TB_FUNCIONARIO)
    @SequenceGenerator(name = SEQ_TB_FUNCIONARIO, sequenceName = SEQ_TB_FUNCIONARIO, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Id
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "id_loja_base")
    @Fetch(FetchMode.JOIN)
    private Loja lojaBase;
    //------- Atributos -------
    @Column(name = "codigo_legado", nullable = false)
    private String codigoLegado;

    @Column(name = "pis", nullable = false)
    private String pis;

    @Column(name = "trabalho_externo", nullable = false)
    private boolean trabalhoExterno;

    @Column(name = "dia_folga", nullable = false)
    private Integer diaFolga;

    @Column(name = "matricula", nullable = false)
    private String matricula;

    @Column(name = "data_pagamento", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dataPagamento;

    @Column(name = "data_demissao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dataDemissao;

    @Column(name = "data_admissao", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dataAdmissao;

    @Column(name = "refeicao", nullable = false)
    private boolean refeicao;

    @Column(name = "template", nullable = false)
    private byte[] templateDigital;

    //---- Relacionamentos ----------
    @ManyToOne(fetch=FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name="id_pessoa_fisica"),
            @JoinColumn(name="id_loja_pessoa_fisica")
    })
    private PessoaFisica pessoaFisica;

    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_loja_alocacao")
    @Fetch(FetchMode.JOIN)
    private Loja lojaAlocacao;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.MERGE)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name="id_equipe"),
            @JoinColumn(name="id_loja_equipe")
    })
    private Equipe equipe;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.MERGE)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name="id_setor"),
            @JoinColumn(name="id_loja_setor")
    })
    private Setor setor;

    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_funcao_interna")
    @Fetch(FetchMode.JOIN)
    private FuncaoInterna funcaoInterna;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumns({
            @JoinColumn(name = "id_departamento"),
            @JoinColumn(name = "id_loja_departamento")
    })
    private List<Departamento> departamentos;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.MERGE)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name="id_supervisor"),
            @JoinColumn(name="id_loja_supervisor")
    })
    private Funcionario supervisor;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.MERGE)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name="id_gerente"),
            @JoinColumn(name="id_loja_gerente")
    })
    private Funcionario gerente;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumns({
            @JoinColumn(name = "id_historico_funcionario"),
            @JoinColumn(name = "id_loja_historico_funcionario")
    })
    private List<HistoricoFuncionario> historicoFuncionario;

    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_sindicato")
    @Fetch(FetchMode.JOIN)
    private Sindicato sindicato;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumns({
            @JoinColumn(name = "id_horario"),
            @JoinColumn(name = "id_loja_horario")
    })
    private List<HorarioSemana> horarios;
}
