package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByNameContaining(String title);
}
