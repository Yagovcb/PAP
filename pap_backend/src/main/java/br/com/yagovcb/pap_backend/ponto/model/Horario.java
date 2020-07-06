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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
@Table(name = "horario", schema = "ponto")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Horario implements Serializable {

    private static final String SEQ_TB_HORARIO = "SEQ_TB_HORARIO";
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_TB_HORARIO)
    @SequenceGenerator(name = SEQ_TB_HORARIO, sequenceName = SEQ_TB_HORARIO, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Id
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "id_loja")
    @Fetch(FetchMode.JOIN)
    private Loja loja;

    @Column(name = "telemarketing", nullable = false)
    private boolean telemarketing;

    @Column(name = "telemarketing", nullable = false)
    private int codigo;

    @Column(name = "noturno", nullable = false)
    private boolean noturno;

    @Column(name = "jornada_dia", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Date jornadaDia;

    @Column(name = "entrada", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Date entrada;

    @Column(name = "inicio_intervalo", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Date inicioIntervalo;

    @Column(name = "fim_intervalo", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Date fimIntervalo;

    @Column(name = "saida", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Date saida;

    @Column(name = "inicio_terceiro_turno", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Date inicioTerceiroTurno;

    @Column(name = "fim_terceiro_turno", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Date fimTerceiroTurno;

    public String getStringTodosOsHorarios() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", new Locale("pt","BR"));
        StringBuffer hora = new StringBuffer();
        hora.append(formatter.format(entrada)).append(" - ").
                append(formatter.format(inicioIntervalo)).append(" / ").
                append(formatter.format(fimIntervalo)).append(" - ").
                append(formatter.format(saida)).append(" / ").
                append(formatter.format(inicioTerceiroTurno)).append(" - ").
                append(formatter.format(fimTerceiroTurno)).append(" - ").
                append(String.valueOf(codigo));

        return  hora.toString();
    }
}
