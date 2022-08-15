package kz.halykacademy.bookstore.service.api;

import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.service.base.BaseService;

public interface UserService extends BaseService<User,Long> {
    User getByUsername(String username);
    Boolean existByUsername(String username);
}
