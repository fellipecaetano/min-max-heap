# min-max-heap

![JUnit](https://img.shields.io/badge/tested%20with-JUnit%205-25A162?logo=junit5&logoColor=white)

A generic min-max heap implementation in Java, written as a way to
actually learn the data structure instead of just reading about it.

## Why this exists

I was recently brushing up my coding problem chops to prepare for
hiring processes and came upon the [task scheduler problem](https://leetcode.com/problems/task-scheduler/) in Leetcode. While studying
it, I found out that besides the optimal math-based solution, you can
also solve it using a heap. In fact, the heap is the best solution if
you need to return the actual schedule instead of just calculating the
minimum number of intervals. That piqued my interest: I saw myself
studying heaps and realized I didn't really remember them from school.
Thus, this project is my attempt at solving two things: (a) brush up
my Java skills, which are rusty to say the least and (b) learn the
algorithms behind a min-max-heap (coding a data structure is the only
way for me to learn how it works). It ended up also being an exercise
in adding agentic engineering concepts to my workflow (turns out an
old dog can learn new tricks after all), that's why there is an
`AGENTS.md` file in there. The initial version was an int-based
implementation and I wrote every character in the source, no AI
involved (I swear!). I then asked Claude to help me make my
`MinMaxHeap` generic and to set up a pretty Java project around it.

## What's a min-max heap, anyway

A regular binary heap gives you fast access to one end of an ordering:
a min-heap gives you the minimum, a max-heap gives you the maximum,
but never both at once. A min-max heap is a single structure that
gives you constant-time access to *both* the minimum and the maximum.

It does this by alternating the invariant level by level: nodes on
even levels (0, 2, 4, ...) are "min levels" and must be smaller than
all of their descendants, while nodes on odd levels are "max levels"
and must be larger than all of their descendants. The root, being on
level 0, ends up holding the minimum; one of its two children holds
the maximum. Insertion and removal trickle elements up or down
through these alternating levels, which is the same shape of work as
a regular heap, just with twice the bookkeeping.

## Building and testing

```
./gradlew test
```
