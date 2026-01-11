package org.example.repository;

import org.example.entities.RefeshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RefreshTokenRepository extends CrudRepository<RefeshToken, Integer> {


    Optional<RefeshToken> findByToken(String token);
}
