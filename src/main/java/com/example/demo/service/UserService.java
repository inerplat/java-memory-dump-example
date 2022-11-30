package com.example.demo.service;

import com.example.demo.model.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.RandomStringUtil;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public String update(String name) {
        UserEntity user = userRepository.findByName(name);
        if (user == null) {
            user = this.create(name);
        }
        if(user.getCnt() == 1000){
            userRepository.delete(user);
        }
        else {
            user.setCnt(user.getCnt() + 1);
            user.setRandomString(RandomStringUtil.generate(1024 * 10));
            userRepository.save(user);
            logger.info("update: {}", user.getCnt());
        }
        return user.toString();
    }

    UserEntity create(String name) {
        return UserEntity.builder()
                .name(name)
                .randomString(RandomStringUtil.generate(1024 * 1024))
                .cnt(0)
                .build();
    }
}
