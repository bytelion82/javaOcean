package com.bytelion.jpa.repository;

import com.bytelion.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 太傅
 * @version V1.0
 * @package com.bytelion.jpa.repository
 * @description
 * @date: Created in 2020/10/8 21:30
 * @copyright Copyright (c) 2020/10/8
 */
@Repository
public interface UserDao extends JpaRepository<User,Long> {

}
