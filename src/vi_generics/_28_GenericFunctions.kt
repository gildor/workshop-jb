package vi_generics.generics

import java.util.ArrayList
import java.util.HashSet
import util.TODO
import javax.sql.rowset.Predicate

fun task28() = TODO(
    """
        Task28.
        Add a 'partitionTo' function that splits a collection into two collections according to a predicate.
        Uncomment the commented invocations of 'partitionTo' below and make them compile.

        There is a 'partition()' function in the standard library that always returns two newly created lists.
        You should write a function that splits the collection into two collections given as arguments.
        The signature of the 'toCollection()' function from the standard library may help you.
    """,
        references = { l: List<Int> ->
            l.partition { it > 0 }
            l.toCollection(HashSet<Int>())
        }
)

fun List<String>.partitionWordsAndLines(): Pair<List<String>, List<String>> {
    return partitionTo(ArrayList<String>(), ArrayList()) { s -> !s.contains(" ") }
}

fun Set<Char>.partitionLettersAndOtherSymbols(): Pair<Set<Char>, Set<Char>> {
    return partitionTo(HashSet<Char>(), HashSet()) { c -> c in 'a'..'z' || c in 'A'..'Z'}
}

fun <T> Iterable<T>.partitionTo(list1: MutableList<T>, list2: MutableList<T>, predicate: (T) -> Boolean): Pair<List<T>, List<T>> {
    forEach { if (predicate(it)) list1.add(it) else list2.add(it) }
    return Pair(list1, list2)
}

fun <T> Set<T>.partitionTo(list1: MutableSet<T>, list2: MutableSet<T>, predicate: (T) -> Boolean): Pair<Set<T>, Set<T>> {
    forEach { if (predicate(it)) list1.add(it) else list2.add(it) }
    return Pair(list1, list2)
}
