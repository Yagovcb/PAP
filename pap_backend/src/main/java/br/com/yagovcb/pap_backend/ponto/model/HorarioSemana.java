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
import javax.persistence.JoinColumns;
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
@Table(name = "horario_semana", schema = "pap")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HorarioSemana implements Serializable {
    private static final String SEQ_TB_HORARIO_SEMANA = "SEQ_TB_HORARIO_SEMANA";
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_TB_HORARIO_SEMANA)
    @SequenceGenerator(name = SEQ_TB_HORARIO_SEMANA, sequenceName = SEQ_TB_HORARIO_SEMANA, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Id
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "id_loja")
    @Fetch(FetchMode.JOIN)
    private Loja loja;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name="id_horario_segunda"),
            @JoinColumn(name="id_loja_horario_segunda")
    })
    private Horario horarioSegunda;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name="id_horario_terca"),
            @JoinColumn(name="id_loja_horario_terca")
    })
    private Horario horarioTerca;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name="id_horario_quarta"),
            @JoinColumn(name="id_loja_horario_quarta")
    })
    private Horario horarioQuarta;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name="id_horario_quinta"),
            @JoinColumn(name="id_loja_horario_quinta")
    })
    private Horario horarioQuinta;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name="id_horario_sexta"),
            @JoinColumn(name="id_loja_horario_sexta")
    })
    private Horario horarioSexta;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name="id_horario_sabado"),
            @JoinColumn(name="id_loja_horario_sabado")
    })
    private Horario horarioSabado;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name="id_horario_domingo"),
            @JoinColumn(name="id_loja_horario_domingo")
    })
    private Horario horarioDomingo;
}
