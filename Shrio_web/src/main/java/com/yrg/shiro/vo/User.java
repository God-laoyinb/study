package com.yrg.shiro.vo;

public class User {

		private String username;
		private String password;
		private boolean remermberMe;
		
		
		/**
		 * @return the remermberMe
		 */
		public boolean isRemermberMe() {
			return remermberMe;
		}
		/**
		 * @param remermberMe the remermberMe to set
		 */
		public void setRemermberMe(boolean remermberMe) {
			this.remermberMe = remermberMe;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
}
