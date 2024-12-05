package net.enjoy.springboot.BrokerMS.repository;

import net.enjoy.springboot.BrokerMS.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}