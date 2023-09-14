package librarysystem.util;

@FunctionalInterface
public interface TriFunction<L, S, T, R> {
    R apply(L l, S s, T t);
}
