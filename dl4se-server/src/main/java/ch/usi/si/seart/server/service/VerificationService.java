package ch.usi.si.seart.server.service;

import ch.usi.si.seart.model.user.User;
import ch.usi.si.seart.model.user.token.VerificationToken;
import ch.usi.si.seart.repository.VerificationTokenRepository;
import ch.usi.si.seart.server.exception.TokenExpiredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface VerificationService extends TokenService<VerificationToken> {

    @Servic