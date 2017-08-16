package com.example.exercisetracker.service;

import com.example.exercisetracker.model.Role;
import com.example.exercisetracker.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
	private RoleRepository roleRepository;

	@Autowired
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public Role getRole(String name) {
		return this.roleRepository.findRoleByName(name).orElse(new Role());
	}
}
