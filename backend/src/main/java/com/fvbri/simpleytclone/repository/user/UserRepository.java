package com.fvbri.simpleytclone.repository.user;

import com.fvbri.simpleytclone.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u.authSub FROM User u WHERE u.authSub = :sub")
    Optional<String> findByAuthSub(@Param("sub") String sub);
}
