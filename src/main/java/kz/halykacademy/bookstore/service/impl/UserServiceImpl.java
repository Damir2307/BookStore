package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.exception.NotFoundException;
import kz.halykacademy.bookstore.repository.UserRepository;
import kz.halykacademy.bookstore.service.api.UserService;
import kz.halykacademy.bookstore.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User,Long, UserRepository> implements UserService {
    @Override
    public User getByUsername(String username) {
        return getRepository().getByUsername(username).orElseThrow(()-> new NotFoundException("Username not found"));
    }

    @Override
    public Boolean existByUsername(String username) {
        return getRepository().existsByUsername(username);
    }
}
