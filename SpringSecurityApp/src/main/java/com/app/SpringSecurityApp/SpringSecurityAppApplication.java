package com.app.SpringSecurityApp;

import com.app.SpringSecurityApp.persistence.entity.PermissionEntity;
import com.app.SpringSecurityApp.persistence.entity.RoleEntity;
import com.app.SpringSecurityApp.persistence.entity.RoleEnum;
import com.app.SpringSecurityApp.persistence.entity.UserEntity;
import com.app.SpringSecurityApp.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringSecurityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAppApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository){
		return args -> {
			PermissionEntity createPermission = PermissionEntity.builder().name("CREATE").build();
			PermissionEntity readPermission = PermissionEntity.builder().name("READ").build();
			PermissionEntity updatePermission = PermissionEntity.builder().name("UPDATE").build();
			PermissionEntity deletePermission = PermissionEntity.builder().name("DELETE").build();
			PermissionEntity refactorPermission = PermissionEntity.builder().name("REFACTOR").build();

			RoleEntity roleAdmin = RoleEntity.builder().roleEnum(RoleEnum.ADMIN).permissionList(Set.of(createPermission,readPermission,updatePermission,deletePermission)).build();
			RoleEntity roleUser = RoleEntity.builder().roleEnum(RoleEnum.USER).permissionList(Set.of(createPermission,readPermission)).build();
			RoleEntity roleInvited = RoleEntity.builder().roleEnum(RoleEnum.INVITED).permissionList(Set.of(readPermission)).build();
			RoleEntity roleDeveloper = RoleEntity.builder().roleEnum(RoleEnum.DEVELOPER).permissionList(Set.of(createPermission,readPermission,updatePermission,deletePermission,refactorPermission)).build();

			UserEntity userRodrigo = UserEntity.builder().username("Rodrigo").password("$2a$10$ZsucdQ/Gy7rqzaeHcaCCCuAzhqWNXYKB5risstLrdJ7pwlrKnA0Nu").isEnabled(true).accountNoExpired(true).accountNoLocked(true).credentialNoExpired(true).roles(Set.of(roleAdmin)).build();
			UserEntity userAlejandrito = UserEntity.builder().username("Alejandrito").password("$2a$10$ZsucdQ/Gy7rqzaeHcaCCCuAzhqWNXYKB5risstLrdJ7pwlrKnA0Nu").isEnabled(true).accountNoExpired(true).accountNoLocked(true).credentialNoExpired(true).roles(Set.of(roleUser)).build();
			UserEntity userNicole = UserEntity.builder().username("Nicole").password("$2a$10$ZsucdQ/Gy7rqzaeHcaCCCuAzhqWNXYKB5risstLrdJ7pwlrKnA0Nu").isEnabled(true).accountNoExpired(true).accountNoLocked(true).credentialNoExpired(true).roles(Set.of(roleInvited)).build();
			UserEntity userMargarita = UserEntity.builder().username("Margarita").password("$2a$10$ZsucdQ/Gy7rqzaeHcaCCCuAzhqWNXYKB5risstLrdJ7pwlrKnA0Nu").isEnabled(true).accountNoExpired(true).accountNoLocked(true).credentialNoExpired(true).roles(Set.of(roleDeveloper)).build();

			userRepository.saveAll(List.of(userRodrigo,userAlejandrito,userNicole,userMargarita));

		};
	}

}
