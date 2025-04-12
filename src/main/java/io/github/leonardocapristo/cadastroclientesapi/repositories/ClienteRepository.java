package io.github.leonardocapristo.cadastroclientesapi.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.github.leonardocapristo.cadastroclientesapi.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente> {
	
    @Query("SELECT c FROM Cliente c WHERE " +
            "(:nome IS NULL OR LOWER(c.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:email IS NULL OR LOWER(c.email) LIKE LOWER(CONCAT('%', :email, '%'))) AND " +
            "(:cpf IS NULL OR c.cpf = :cpf) AND " +
            "(:telefone IS NULL OR c.telefone = :telefone) AND " +
            "(:dataInicio IS NULL OR c.dataNascimento >= :dataInicio) AND " +
            "(:dataFim IS NULL OR c.dataNascimento <= :dataFim)")
     Page<Cliente> filtrarClientes(
         @Param("nome") String nome,
         @Param("email") String email,
         @Param("cpf") String cpf,
         @Param("telefone") Long telefone,
         @Param("dataInicio") LocalDate dataInicio,
         @Param("dataFim") LocalDate dataFim,
         Pageable pageable  // Novo parâmetro
     );

}
