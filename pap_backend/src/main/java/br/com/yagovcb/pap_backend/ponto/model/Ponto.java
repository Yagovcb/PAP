package br.com.yagovcb.pap_backend.ponto.model;

import br.com.yagovcb.pap_backend.pap.model.Funcionario;
import br.com.yagovcb.pap_backend.pap.model.Loja;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
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
@Table(name = "ponto", schema = "ponto")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Ponto implements Serializable {
    private static final String SEQ_TB_PONTO = "SEQ_TB_PONTO";
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_TB_PONTO)
    @SequenceGenerator(name = SEQ_TB_PONTO, sequenceName = SEQ_TB_PONTO, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Id	@ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "id_loja")
    @Fetch(FetchMode.JOIN)
    private Loja loja;

    @Column(name = "abonado", nullable = false)
    private boolean abonado;

    @Column(name = "data_ponto", nullable = false)
    private Date dataPonto;

    @Column(name = "saldo_dia_faixa1", nullable = false)
    private Date saldoDiaFaixa1;

    @Column(name = "saldo_dia_faixa2", nullable = false)
    private Date saldoDiaFaixa2;

    @Column(name = "total_horas_trabalhadas")
    private Date totalHorasTrabalhadas;

    @Column(name = "hora_atraso")
    private Date horaAtraso;

    @Column(name = "horas_diurnas")
    private Date horasDiurnas;

    @Column(name = "horas_noturnas")
    private Date horasNoturnas;

    @Column(name = "hora_extra1")
    private Date horaExtra1;

    @Column(name = "hora_extra2")
    private Date horaExtra2;

    @ManyToOne(fetch=FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name="id_funcionario"),
            @JoinColumn(name="id_loja_funcionario")
    })
    private Funcionario funcionario;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name="id_horario"),
            @JoinColumn(name="id_loja_horario")
    })
    @Fetch(FetchMode.JOIN)
    private Horario horario;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_situacao_ponto")
    @Fetch(FetchMode.JOIN)
    private SituacaoPonto situacaoPonto;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumns({
            @JoinColumn(name = "id_ponto"),
            @JoinColumn(name = "id_loja")
    })
    private List<ItemPonto> itensPonto;
}
