package br.com.alexcosta.repository;

import br.com.alexcosta.entities.Vendas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendasRepository extends JpaRepository<Vendas,Long> {
}
