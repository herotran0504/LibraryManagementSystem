package librarysystem.util;

import librarysystem.models.BookCopy;
import librarysystem.models.LibraryMember;
import librarysystem.models.Publication;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Functors {

    public static final TriFunction<List<Publication>, String, String, List<Publication>> PUBLICATION_FILTER = (l, idSubString, titleSubString) -> l
            .stream()
            .filter(p -> p.getPublicationId().toLowerCase().contains(idSubString.toLowerCase()))
            .filter(p -> p.getTitle().toLowerCase().contains(titleSubString.toLowerCase()))
            .collect(Collectors.toList());

    public static final TriFunction<List<LibraryMember>, String, String, List<LibraryMember>> MEMBER_FILTER = (
            l, idSubString, nameSubString) -> l
            .stream()
            .filter(lm -> lm.getMemberId().toLowerCase().contains(idSubString.toLowerCase()))
            .filter(lm -> lm.getFirstname().toLowerCase().contains(nameSubString.toLowerCase()) ||
                    lm.getLastName().toLowerCase().contains(nameSubString.toLowerCase()))
            .collect(Collectors.toList());

    public static final Function<Publication, Integer> AVAILABLE_COPIES_COUNTER = (p) -> Functors.AVAILABLE_COPIES_FINDER.apply(p).size();

    public static final Function<Publication, List<BookCopy>> AVAILABLE_COPIES_FINDER = (p) -> p.getCopies() == null ? new ArrayList<>() : p.getCopies()
            .stream()
            .filter(p1 -> !p1.isAvailable())
            .collect(Collectors.toList());
}
