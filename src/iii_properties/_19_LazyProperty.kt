package iii_properties

import util.TODO

class LazyProperty(val initializer: () -> Int) {
    var lazyInited = false;
    val lazy: Int = 0
        get() {
            if (!lazyInited) {
                lazyInited = true
                field = initializer()
            }
            return field
        }
}

fun todoTask19() = TODO(
    """
        Task 19.
        Add a custom getter to make the 'lazy' val really lazy.
        It should be initialized by the invocation of 'initializer()'
        at the moment of the first access.
        You can add as many additional properties as you need.
        Do not use delegates ;).
    """,
    references = { LazyProperty({ 42 }).lazy }
)
