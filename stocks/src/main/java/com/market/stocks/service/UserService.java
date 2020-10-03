package com.market.stocks.service;

import com.market.stocks.model.User;
import com.market.stocks.repository.IStockRepository;
import com.market.stocks.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {

    private IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser() {
        return userRepository.findById(1L).get();
    }
}
