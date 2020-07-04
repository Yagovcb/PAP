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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
@Table(name = "profissao", schema = "pap")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Profissao extends Pessoa {

    private static final long serialVersionUID = 1L;
    private static final String SEQ_TB_PROFISSAO = "SEQ_TB_PROFISSAO";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_TB_PROFISSAO)
    @SequenceGenerator(name = SEQ_TB_PROFISSAO, sequenceName = SEQ_TB_PROFISSAO, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "codigo", nullable = false)
    private String codigo;

    @Column(name = "codigo_legado", nullable = false)
    private int codigoLegado;

    @Column(name = "funcao", nullable = false)
    private String funcao;

    @OneToMany(fetch= FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name="id_dados_profissionais"),
            @JoinColumn(name="id_loja_dados_profissionais")
    })
    private List<DadosProfissionais> dadosProfissionais;
}
