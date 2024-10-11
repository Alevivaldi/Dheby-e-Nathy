package br.com.alexcosta.repository;

import br.com.alexcosta.entities.Kit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KitRepository extends JpaRepository<Kit,Long> {
}
