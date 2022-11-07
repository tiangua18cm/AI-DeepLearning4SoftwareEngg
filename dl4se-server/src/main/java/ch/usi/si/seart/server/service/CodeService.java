package ch.usi.si.seart.server.service;

import ch.usi.si.seart.exception.CodeNotFoundException;
import ch.usi.si.seart.model.code.Code;
import ch.usi.si.seart.model.code.Code_;
import ch.usi.si.seart.repository.CodeRepository;
import ch.usi.si.seart.service.DatasetService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public interface CodeService extends DatasetService<Code> {

    @Service
    @FieldDefaults(level = Acces