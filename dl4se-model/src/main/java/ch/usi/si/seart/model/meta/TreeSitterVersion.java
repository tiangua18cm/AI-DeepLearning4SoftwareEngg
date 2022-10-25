package ch.usi.si.seart.model.meta;

import ch.usi.si.seart.model.code.File;
import ch.usi.si.seart.validation.constraints.SHAHash;
import ch.usi.si.seart.validation.constraints.SemanticVersion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Immutable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Immutable
@Entity
@Table(
        name = "tree_sitter_version",
        uniqueConstraints = @UniqueConstraint(columnNames = {"sha", "tag"})
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TreeSitterVersion {

    @Id
    @GeneratedValue
    @JsonIgnore
    Long id;

    @NotNull
    @SHAH