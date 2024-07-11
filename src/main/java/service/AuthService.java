package service;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuthService {
  @ConfigProperty(name = "auth.default-password")
  String defaultPassword;
  
  public boolean authenticate(String document, String password) {
    var user = UserEntity.findByCpf(document);
    return user != null && password.equals(defaultPassword);
  }
}
