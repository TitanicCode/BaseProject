package api.pojo;

import javax.persistence.*;

//标明它是实体类，用于pojo扫描
@Entity
//标明对应数据库表格
@Table(name="t_user")
public class User {
    //表明主键
    @Id
    //设定自增长策略
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //设置对应数据库表的列,列名最好加前缀，防止遇到列名为关键字，影响sql语句执行
    @Column(name="u_id")
    private Integer id;
    @Column(name = "u_username")
    private String username;
    @Column(name = "u_password")
    private String password;
    @Column(name = "u_email")
    private String email;
    @Column(name = "u_flag")
    private Integer flag;
    @Column(name = "u_role")
    private Integer role;
    @Column(name = "u_phone")
    private String phone;
    @Column(name = "u_code")
    private String code;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", flag=" + flag +
                ", role=" + role +
                ", phone='" + phone + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
