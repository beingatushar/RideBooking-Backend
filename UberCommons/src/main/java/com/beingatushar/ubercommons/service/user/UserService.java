package com.beingatushar.ubercommons.service.user;

import com.beingatushar.ubercommons.entity.user.User;
import com.beingatushar.ubercommons.service.base.BaseService;
import com.beingatushar.ubercommons.service.base.GetByRefBaseService;

public interface UserService extends BaseService<User, Long>, GetByRefBaseService<User, Long> {
}
