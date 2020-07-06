package br.com.yagovcb.pap_backend.ponto.model;

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
@Table(name = "dispositivo_identificacao", schema = "ponto")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DispositivoIdentificacao implements Serializable {
    private static final String SEQ_TB_DISPOSIIVO_IDENTIFICACAO = "SEQ_TB_DISPOSIIVO_IDENTIFICACAO";
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_TB_DISPOSIIVO_IDENTIFICACAO)
    @SequenceGenerator(name = SEQ_TB_DISPOSIIVO_IDENTIFICACAO, sequenceName = SEQ_TB_DISPOSIIVO_IDENTIFICACAO, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "fabricante", nullable = false)
    private String fabricante;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "cnpj", nullable = false, length = 14)
    private String cnpj;
}
