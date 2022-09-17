package ch.usi.si.seart.analyzer.enumerate;

import ch.usi.si.seart.analyzer.JavaBaseTest;
import ch.usi.si.seart.model.code.Boilerplate;
import ch.usi.si.seart.treesitter.Language;
import ch.usi.si.seart.treesitter.Node;
import ch.usi.si.seart.treesitter.Parser;
import ch.usi.si.seart.treesitter.Tree;
import lombok.Cleanup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.List;
import java.util.stream.Stream;

class PythonBoilerplateEnumeratorTest extends JavaBaseTest {

    private static class PythonCodeProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of(List.of("def function():"), null),
                    Arguments.of(List.of("def __new__():"), Boilerplate.CONSTRUCTOR),
                    Arguments.of(List.of("def __init__():"), Boilerplate.INITIALIZER),
                    Arguments.of(List.of("def __str__():"), Boilerplate.STRING_CONVERSION),
                    Arguments.of(List.of("def __repr__():"), Boilerplate.STRING_REPRESENTATION),
                    Arguments.of(List.of("def __hash__():"), Boilerplate.HASHER),
                    Arguments.of(
                            List.of(
                                    "def __get__():",
                                    "def __getattr__():"
                            ),
                            Boilerplate.GETTER
                    ),
                    Arguments.of(
                            List.of(
                                    "def __set__():",
                                    "def __setattr__():"
                            ),
                            Boilerplate.SETTER
                    ),
                    Arguments.of(
                            List.of(
                                    "def __del__():",
                                    "def __delattr__():"
                            ),
                            Boilerplate.FINALIZER
                    ),
                    Arguments.of(
                            List.o