package suggestions;

public class Suggestions2Test {

    // There's a couple of problems here...

    // First problem: the tests don't have clear and descriptive names,
    // which means that when a test fails, it's harder to see which part
    // of your implementation didn't pass the test
    // Here are some suggestions: https://medium.com/@stefanovskyi/unit-test-naming-conventions-dd9208eadbea

    // Second problem, there's a couple of tests in there that exercise
    // the same logic, just with different combinations of input and
    // expected output. This isn't clear when you read the test code
    // or when you inspect the test results
    // Here's a way to deal with this: https://www.baeldung.com/parameterized-tests-junit-5
}
