package br.com.yagovcb.pap_backend.pap.model;


import br.com.yagovcb.pap_backend.ponto.model.HorarioSemana;
import br.com.yagovcb.pap_backend.ponto.model.RegistroPonto;
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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    private PessoaFisica pessoaFisica;
    private Loja lojaAlocacao;
    private Equipe equipe;
    private Setor setor;
    private FuncaoInterna funcaoInterna;
    private Loja lojaOrigem;
    private List<Departamento> departamentos;
    private Funcionario supervisor;
    private Funcionario gerente;
    private String codigoLegado;
    private List<FuncaoInterna> funcoes;
   //private List<HistoricoFuncionario> historicoFuncionario;
    private Entrevista entrevista;
    private Sindicato sindicato;
    private String pis;
    private boolean trabalhoExterno;
    private Integer diaFolga;
    private List<HorarioSemana> horarios;
    private byte[] template;
    private RegistroPonto registroPonto;
    private Integer quantidadeContratosLiquidados;
    private double limiteQuinzena1;
    private String matricula;
    private double limiteQuinzena2;
    private Date dataFolha;
    private Date dataDemissao;
    private Date dataAdmissao;
    private boolean refeicao;

}
