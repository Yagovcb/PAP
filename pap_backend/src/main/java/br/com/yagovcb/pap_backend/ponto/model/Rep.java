package br.com.yagovcb.pap_backend.ponto.model;

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
import javax.persistence.JoinTable;
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
@Table(name = "rep", schema = "ponto")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Rep implements Serializable {
    private static final String SEQ_TB_REP = "SEQ_TB_REP";
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_TB_REP)
    @SequenceGenerator(name = SEQ_TB_REP, sequenceName = SEQ_TB_REP, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Id	@ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "id_loja")
    @Fetch(FetchMode.JOIN)
    private Loja loja;

    @Column(name = "numero_nota_fiscal", nullable = false)
    private long numeroNotaFiscal;

    @Column(name = "numero_serie", nullable = false)
    private long numeroSerie;

    @Column(name = "porta", nullable = false, length = 5)
    private String porta;

    @Column(name = "endereco_ip", nullable = false, length = 15)
    private String enderecoIp;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "capacidade_memoria_mrp", nullable = false, length = 4)
    private String capacidadeMemoriaMrp;

    @Column(name = "nome_fabricante", nullable = false, length = 50)
    private String nomeFabricante;

    @Column(name = "cnpj", nullable = false, length = 14)
    private String cnpj;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "id_dispositivo")
    @Fetch(FetchMode.JOIN)
    private DispositivoIdentificacao dispositivo;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_loja_completa")
    @Fetch(FetchMode.JOIN)
    private LojaCompleta lojaCompleta;
}
