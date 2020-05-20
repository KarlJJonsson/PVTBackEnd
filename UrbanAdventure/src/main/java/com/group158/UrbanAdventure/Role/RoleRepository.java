package com.group158.UrbanAdventure.Role;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findByName(ERole name);
}