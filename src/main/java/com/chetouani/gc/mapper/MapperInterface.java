package com.chetouani.gc.mapper;


@FunctionalInterface
public interface MapperInterface<I, O> {

    O map(I input);
}
