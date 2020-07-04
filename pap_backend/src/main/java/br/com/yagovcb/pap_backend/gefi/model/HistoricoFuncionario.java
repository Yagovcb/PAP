package br.com.yagovcb.pap_backend.gefi.model;

import br.com.yagovcb.pap_backend.pap.model.Loja;
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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
@Table(name = "historico_funcionario", schema = "gefi")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HistoricoFuncionario implements Serializable {
    private static final String SEQ_TB_HISTORICO_FUNCIONARIO = "SEQ_TB_HISTORICO_FUNCIONARIO";
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_TB_HISTORICO_FUNCIONARIO)
    @SequenceGenerator(name = SEQ_TB_HISTORICO_FUNCIONARIO, sequenceName = SEQ_TB_HISTORICO_FUNCIONARIO, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Id
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "id_loja")
    @Fetch(FetchMode.JOIN)
    private Loja loja;

    @Column(name = "data_demissao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date data;

    @Column(name = "refeicao", nullable = false)
    @Lob
    private String descricao;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "id_tipo_loja", referencedColumnName = "id")
    private TipoHistoricoFuncionario tipoHistoricoFuncionario;
}
