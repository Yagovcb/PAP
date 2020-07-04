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
@Table(name = "atividade_profissional", schema = "pap")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AtividadeProfissional implements Serializable {

    private static final String SEQ_TB_ATIVIDADE_PROFISSIONAL = "SEQ_TB_ATIVIDADE_PROFISSIONAL";
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_TB_ATIVIDADE_PROFISSIONAL)
    @SequenceGenerator(name = SEQ_TB_ATIVIDADE_PROFISSIONAL, sequenceName = SEQ_TB_ATIVIDADE_PROFISSIONAL, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    private String descricao;
    private short quantidadeSalarioMinimo;
    private short quantidadeSalarioMaximo;
}
