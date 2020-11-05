package com.dev.cinema.dao;

import com.dev.cinema.model.Role;
import java.util.Optional;

public interface RoleDao extends GenericDao<Role> {
    Optional<Role> getRoleByName(String rolename);
}
