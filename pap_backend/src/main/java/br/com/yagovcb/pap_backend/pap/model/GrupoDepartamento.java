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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "grupo_departamento", schema = "pap")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GrupoDepartamento implements Serializable {
    private static final String SEQ_TB_GRUPO_DEPARTAMENTO = "SEQ_TB_GRUPO_DEPARTAMENTO";
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_TB_GRUPO_DEPARTAMENTO)
    @SequenceGenerator(name = SEQ_TB_GRUPO_DEPARTAMENTO, sequenceName = SEQ_TB_GRUPO_DEPARTAMENTO, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "codigo", nullable = false)
    private String codigo;
}
