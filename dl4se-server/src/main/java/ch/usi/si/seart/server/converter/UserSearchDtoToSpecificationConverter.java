package ch.usi.si.seart.server.converter;

import ch.usi.si.seart.model.user.User;
import ch.usi.si.seart.model.user.User_;
import ch.usi.si.seart.server.dto.user.UserSearchDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.metamodel.SingularAttribute;

public class UserSearchDtoToSpecificationConverter implements Converter<UserSearchDto, Specification<User>> {
    
    @Override
    @NonNull
 