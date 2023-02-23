package com.receteausersservice.receteausersservice.repositories;

import com.receteausersservice.receteausersservice.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, Long> {
}
