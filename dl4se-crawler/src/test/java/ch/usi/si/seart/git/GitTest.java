package ch.usi.si.seart.git;

import ch.usi.si.seart.crawler.git.Git;
import ch.usi.si.seart.crawler.git.GitException;
import ch.usi.si.seart.model.Language;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class GitTest {

    @NonFinal
    @TempDir
    Path tmp;
    // https://github.com/dabico/dl4se-test
    String testRepoName = "dabico/dl4se-test";
    // https://github.com/dabico/dl4se-empty
    String emptyRepoName = "dabico/dl4se-empty";
    // https://github.com/dabico/dl4se-history
    String historyRepoName = "dabico/dl4se-history";

    Language java = Language.builder().extensions(Collections.singletonList("java")).build();
    Language python = Language.builder().extensions(Collections.singletonList("py")).build();
    Language cpp = Language.builder()
            .extensions(List.of(
                    "c",
                    "cc",
                    "cpp",
                    "cxx"
            ))
            .build();

    @Test
    void regularCloneTest() throws IOException, GitException {
        try (Git ignored = new Git(testRepoName, tmp)) {
            checkContents(tmp.toFile());
        }
    }

    @Test
