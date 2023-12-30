package br.com.desafiogrupoallcross.adapter.out.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class AbstractAuditingEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @CreatedDate
    @Column(name = "data_cadastro", nullable = false, insertable = true, updatable = false)
    private Instant dataCadastro;

    @LastModifiedDate
    @Column(name = "data_atualizacao", nullable = true, insertable = true, updatable = true)
    private Instant dataAtualizacao;

    @CreatedBy
    @Column(name = "cadastrado_por")
    private String cadastradoPor;

    @LastModifiedBy
    @Column(name = "atualizado_por")
    private String atualizadoPor;
}

