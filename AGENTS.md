# Code Style

## Method formatting

Preferred:

```java
@Override
public void foo() {
    doSomething();
}
```

For empty methods:

```java
@Override
public void foo() {}
```

Avoid:

```java
@Override public void foo() { doSomething(); }
```

# Version Control
- If the commit message says two things (or more), it means the commit should probably be two commits (or more). In that case, create more than one commit.