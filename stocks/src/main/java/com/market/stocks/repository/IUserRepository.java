package com.market.stocks.repository;

import com.market.stocks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long>  {
}
