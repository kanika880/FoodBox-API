package com.foodbox.model;


	import javax.persistence.CascadeType;
	import javax.persistence.Entity;
	import javax.persistence.FetchType;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;
	@Component
	@Entity
	public class UserRole {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long userRoleId;
		
		@ManyToOne(fetch = FetchType.EAGER)
		private User users;
		
		@ManyToOne
		private Role role;

		public long getUserRoleId() {
			return userRoleId;
		}

		public void setUserRoleId(long userRoleId) {
			this.userRoleId = userRoleId;
		}

		public User getUsers() {
			return users;
		}

		public void setUsers(User users) {
			this.users = users;
		}

		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}
		
		

	}



