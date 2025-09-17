package com.gitcha.gitcha.commit.repository;

import com.gitcha.gitcha.commit.domain.Commit;
import com.gitcha.gitcha.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommitRepository extends JpaRepository<Commit, Long> {
    List<Commit> findAllByUser(User user);
}
