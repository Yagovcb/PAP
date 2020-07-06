package br.com.yagovcb.pap_backend.pap.model;

import br.com.yagovcb.pap_backend.gefi.model.Loja;
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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "endereco_pessoa", schema = "pap")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EnderecoPessoa implements Serializable {

    private static final String SEQ_TB_ENDERECO_PESSOA = "SEQ_TB_ENDERECO_PESSOA";
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_TB_ENDERECO_PESSOA)
    @SequenceGenerator(name = SEQ_TB_ENDERECO_PESSOA, sequenceName = SEQ_TB_ENDERECO_PESSOA, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Id
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "id_loja")
    @Fetch(FetchMode.JOIN)
    private Loja loja;

    @Lob
    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Lob
    @Column(name = "complemento")
    private String complemento;

    @Lob
    @Column(name = "perimetro", nullable = false)
    private String perimetro;

    @Lob
    @Column(name = "numero_dependentes", nullable = false)
    private String pontoReferencia;

    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_tipo_endereco")
    @Fetch(FetchMode.JOIN)
    private TipoEndereco tipoEndereco;

    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_bairro")
    @Fetch(FetchMode.JOIN)
    private Bairro bairro;

    @Column(name = "cep", nullable = false)
    private String cep;

    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_localidade")
    @Fetch(FetchMode.JOIN)
    private Localidade localidade;

    @Column(name = "atual", nullable = false)
    private boolean atual;
}
