package com.example.password_manager;

public class Password {

   private String email;
   private String password;
   private String Provider;
   private String Img = "";
   private String pass;

   public Password ()
   {

   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getProvider() {
      return Provider;
   }

   public void setProvider(String provider) {
      Provider = provider;
   }

   public String getImg() {
      return Img;
   }

   public void setImg(String img) {
      Img = img;
   }

   @Override
   public String toString() {
      return "Password{" +
              "email='" + email + '\'' +
              ", password='" + password + '\'' +
              ", Provider='" + Provider + '\'' +
              ", Img='" + Img + '\'' +
              ", pass='" + pass + '\'' +
              '}';
   }

   public String getPass() {
      return pass;
   }

   public void setPass(String pass) {
      this.pass = pass;
   }
}
