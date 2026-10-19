
public class RegisterUserRequest {
    private final String name;
    private final String password;
    private final String email;
    private final int age;
    private final boolean autoActivate;
    private final String role;

    public RegisterUserRequest(String name, String password, String email, int age, boolean autoActivate, String role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.age = age;
        this.autoActivate = autoActivate;
        this.role = role;
    }

    public String getName() { return name; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public boolean isAutoActivate() { return autoActivate; }
    public String getRole() { return role; }
}
public class RegistrationResult {
    private final int userId;
    private final String token;

    public RegistrationResult(int userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public int getUserId() { return userId; }
    public String getToken() { return token; }
}

public class UserRegistration {
    public RegistrationResult registerUser(RegisterUserRequest request) {
        if (!isValidPassword(request.getPassword()) || request.getName() == null) {
            throw new IllegalArgumentException("Invalid user registration data");
        }

        User user = buildUserFromRequest(request);
        int userId = Database.saveUser(user);
        String tokenValue = generateTokenValue(userId);

        return new RegistrationResult(userId, tokenValue);
    }

    private boolean isValidPassword(String password) {
        return password != null && password.length() >= 8;
    }

    private User buildUserFromRequest(RegisterUserRequest request) {
        User u = new User(request.getName(), request.getPassword(), request.getEmail(), request.getAge());
        u.setRole(request.getRole());
        if (request.isAutoActivate()) {
            u.activateDirectly();
        }
        return u;
    }

    private String generateTokenValue(int userId) {
        return "TOKEN_" + userId + "_" + System.currentTimeMillis();
    }
}