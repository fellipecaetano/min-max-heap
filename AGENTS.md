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
