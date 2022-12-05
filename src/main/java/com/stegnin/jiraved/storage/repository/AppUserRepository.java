package com.stegnin.jiraved.storage.repository;

import com.stegnin.jiraved.storage.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alexandr Stegnin
 */
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    @Override
    boolean existsById(Long chatUserId);
}
