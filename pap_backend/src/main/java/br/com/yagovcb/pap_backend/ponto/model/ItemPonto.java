package br.com.yagovcb.pap_backend.ponto.model;

import br.com.yagovcb.pap_backend.gefi.model.Loja;
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
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

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
@Table(name = "item_ponto", schema = "ponto")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ItemPonto implements Serializable {
    private static final String SEQ_TB_ITEM_PONTO = "SEQ_TB_ITEM_PONTO";
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_TB_ITEM_PONTO)
    @SequenceGenerator(name = SEQ_TB_ITEM_PONTO, sequenceName = SEQ_TB_ITEM_PONTO, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Id	@ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "id_loja")
    @Fetch(FetchMode.JOIN)
    private Loja loja;

    @Column(name = "sequencial", nullable = false, length = 1)
    private int sequencial;

    @Column(name = "hora", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Date hora;

    @Lob
    @Column(name = "sequencial")
    private String justificativa;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns({@JoinColumn(name="id_rep"), @JoinColumn(name="id_loja_rep")})
    @Fetch(FetchMode.JOIN)
    private Rep rep;

    @ManyToOne(targetEntity=Ponto.class,fetch = FetchType.LAZY)
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(column=@JoinColumn(name="id_ponto")),
            @JoinColumnOrFormula(formula=@JoinFormula("id_loja"))})
    private Ponto owner;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_tipo_marcacao")
    @Fetch(FetchMode.JOIN)
    private TipoMarcacao tipoMarcacao;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_tipo_registro_ponto")
    @Fetch(FetchMode.JOIN)
    private TipoRegistroPonto tipoRegistroPonto;

//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name="id_usuario")
//    @Fetch(FetchMode.JOIN)
//    private Usuario usuario;

}
