package core.util;

import business.Book;
import business.BookCopy;
import business.LibraryMember;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Functors {

    private Functors() {
    }

    public static final TriFunction<List<Book>, String, String, List<Book>> BOOK_FILTER = (l, idSubString, titleSubString) -> l
            .stream()
            .filter(p -> p.getIsbn().toLowerCase().contains(idSubString.toLowerCase()))
            .filter(p -> p.getTitle().toLowerCase().contains(titleSubString.toLowerCase()))
            .collect(Collectors.toList());

    public static final TriFunction<List<LibraryMember>, String, String, List<LibraryMember>> MEMBER_FILTER = (
            l, idSubString, nameSubString) -> l
            .stream()
            .filter(lm -> lm.getMemberId().toLowerCase().contains(idSubString.toLowerCase()))
            .filter(lm -> lm.getFirstname().toLowerCase().contains(nameSubString.toLowerCase()) ||
                    lm.getLastName().toLowerCase().contains(nameSubString.toLowerCase()))
            .collect(Collectors.toList());

    public static final Function<Book, Integer> AVAILABLE_COPIES_COUNTER = (p) -> Functors.AVAILABLE_COPIES_FINDER.apply(p).size();

    public static final Function<Book, List<BookCopy>> AVAILABLE_COPIES_FINDER = (p) -> p.getCopies() == null ? new ArrayList<>() : Arrays.stream(p.getCopies())
            .filter(BookCopy::isAvailable)
            .collect(Collectors.toList());

}
