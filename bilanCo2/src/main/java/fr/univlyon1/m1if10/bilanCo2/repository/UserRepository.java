package fr.univlyon1.m1if10.bilanCo2.repository;

import java.util.List;

import fr.univlyon1.m1if10.bilanCo2.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<Users, Long> {

    List<Users> findByNameContaining(String title);
}
