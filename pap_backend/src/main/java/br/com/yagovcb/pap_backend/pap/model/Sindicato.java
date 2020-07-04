package br.com.yagovcb.pap_backend.pap.model;

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
@Table(name = "sindicato", schema = "ponto")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Sindicato implements Serializable {
    private static final String SEQ_TB_SINDICATO = "SEQ_TB_SINDICATO";
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_TB_SINDICATO)
    @SequenceGenerator(name = SEQ_TB_SINDICATO, sequenceName = SEQ_TB_SINDICATO, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "limite_hora_xtra", nullable = false)
    private int limiteHoraExtra;

    @Column(name = "aceita_compensacao_hora_extra", nullable = false)
    private boolean aceitaCompensacaoHoraExtra;

    @Column(name = "percentual_primeira_faixa", nullable = false)
    private int percentualPrimeiraFaixa;

    @Column(name = "percentual_segunda_faixa", nullable = false)
    private int percentualSegundaFaixa;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "sigla", nullable = false)
    private String sigla;
}
