package ch.usi.si.seart.transformer;

import ch.usi.si.seart.treesitter.Language;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public interface Transformer extends UnaryOperator<String> {

    String apply(String