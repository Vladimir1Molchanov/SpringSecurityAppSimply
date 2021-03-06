package vm.projects.SpringSecurityApp.model;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "roleName")
    private String roleName;
    @Column(name = "users")
    @ManyToMany(targetEntity = User.class
            , mappedBy = "roles"
            , fetch = FetchType.LAZY)
    private List<User> users;

    public Role(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return getRoleName();
    }
}
