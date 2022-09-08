package ch.usi.si.seart.analyzer.count;

import ch.usi.si.seart.treesitter.Node;

import java.util.Collection;
import java.util.stream.Stream;

public abstract class AbstractCounter implements Counter {

    @Override
    public Long count(No