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
@Table(name = "equipe", schema = "pap")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Equipe implements Serializable {
    private static final String SEQ_TB_EQUIPE = "SEQ_TB_EQUIPE";
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_TB_EQUIPE)
    @SequenceGenerator(name = SEQ_TB_EQUIPE, sequenceName = SEQ_TB_EQUIPE, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Id
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "id_loja")
    @Fetch(FetchMode.JOIN)
    private Loja loja;

    @Column(name = "contato", nullable = false)
    private String contato;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "tipo", nullable = false)
    private Integer tipo;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.MERGE)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name="id_chefe_equipe"),
            @JoinColumn(name="id_loja_chefe_equipe")
    })
    private Funcionario chefeEquipe;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumns({
            @JoinColumn(name = "id_equipe_funcionario"),
            @JoinColumn(name = "id_loja_equipe_funcionario")
    })
    private List<Funcionario> funcionarios;
}
