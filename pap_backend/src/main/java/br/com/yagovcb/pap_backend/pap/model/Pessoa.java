package br.com.yagovcb.pap_backend.pap.model;

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
import java.util.Date;
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
@Table(name = "pessoa", schema = "pap")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String SEQ_TB_PESSOA = "SEQ_TB_PESSOA";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_TB_PESSOA)
    @SequenceGenerator(name = SEQ_TB_PESSOA, sequenceName = SEQ_TB_PESSOA, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Id
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "id_loja")
    @Fetch(FetchMode.JOIN)
    private Loja loja;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "sexo", nullable = false, length = 1)
    private char sexo;

    @Column(name = "data_nascimento", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dataNascimento;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumns({
            @JoinColumn(name = "id_documentos"),
            @JoinColumn(name = "id_loja_documentos")
    })
    private List<Documento> documentos;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumns({
            @JoinColumn(name = "id_enderecos"),
            @JoinColumn(name = "id_loja_enderecos")
    })
    private List<EnderecoPessoa> enderecosPessoa;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumns({
            @JoinColumn(name = "id_telefones"),
            @JoinColumn(name = "id_loja_telefones")
    })
    private List<Telefone> telefones;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumns({
            @JoinColumn(name = "id_emails"),
            @JoinColumn(name = "id_loja_emails")
    })
    private List<Email> emails;

    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_endereco_atual")
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name = "id_endereco_atual"),
            @JoinColumn(name = "id_loja_endereco_atual")
    })
    private EnderecoPessoa enderecoAtual;

    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.MERGE)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name = "id_telefone_aual"),
            @JoinColumn(name = "id_loja_telefone_aual")
    })
    private Telefone telefoneAtual;

    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.MERGE)
    @Fetch(FetchMode.JOIN)
    @JoinColumns({
            @JoinColumn(name = "id_email_atual"),
            @JoinColumn(name = "id_loja_email_atual")
    })
    private Email emailAtual;

    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_naturalidade")
    @Fetch(FetchMode.JOIN)
    private Localidade naturalidade;


    public void setEnderecoAtual(EnderecoPessoa enderecoAtual) {
        for(EnderecoPessoa enderecoPessoa : enderecosPessoa){
            if (enderecoPessoa.isAtual()){
                enderecoAtual = enderecoPessoa;
            }
        }
        this.enderecoAtual = enderecoAtual;
    }

    public void setTelefoneAtual(Telefone telefoneAtual) {
        for(Telefone telefone : telefones){
            if (telefone.isAtual()){
                telefoneAtual = telefone;
            }
        }
        this.telefoneAtual = telefoneAtual;
    }

    public void setEmailAtual(Email emailAtual) {
        for(Email email : emails){
            if (email.isAtual()){
                emailAtual = email;
            }
        }
        this.emailAtual = emailAtual;
    }
}
