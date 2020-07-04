package br.com.yagovcb.pap_backend.pap.model;

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
@Table(name = "pessoa", schema = "pap")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String SEQ_TB_PESSOA = "SEQ_TB_PESSOA";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_TB_PESSOA)
    @SequenceGenerator(name = SEQ_TB_PESSOA, sequenceName = SEQ_TB_PESSOA, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Id
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "id_loja")
    @Fetch(FetchMode.JOIN)
    private Loja loja;

    private char sexo;
    private double valorAluguel;
   // private Localidade naturalidade;
    private Date dataNascimento;
    //private String cpf;
    private short numeroDependentes;
    //private String rg;
    private Uf ufEmissor;
}
