package tac_extra.util;

@FunctionalInterface
public interface Process<T> {
	T process(T t);
}