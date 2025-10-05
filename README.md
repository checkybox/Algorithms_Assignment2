# Design and Analysis of Algorithms Assignment 2

![Build](https://github.com/checkybox/Algorithms_Assignment2/actions/workflows/maven.yml/badge.svg)

## Learning Goals
- Implement fundamental sorting and array algorithms with proper asymptotic analysis
- Apply rigorous complexity analysis using Big-O, Big-Theta, and Big-Omega notations for best/worst/average cases
- Conduct professional peer code review focusing on algorithmic efficiency and optimization opportunities 
- Validate theoretical analysis through empirical measurements and identify performance bottlenecks
- Communicate findings via comprehensive analysis reports and maintain clean Git workflow

## Featured algorithms
- Insertion Sort

## Command-line interface

<details>
<summary><strong>Show CLI usage and examples</strong></summary>

The command line interface allows you to benchmark Insertion Sort on randomly generated integer arrays and export performance metrics to a CSV file.

### Building the JAR

To build the executable JAR, run:

```bash
mvn clean package
```

This will produce `target/insertion_sort-1.0.jar`.

Alternatively, you can download the latest release from the [Releases](https://github.com/checkybox/Algorithms_Assignment2/releases) page.

### Usage

```bash
java -jar target/insertion_sort-1.0.jar \
  --size <n> \
  --csv <output.csv> \
  [--seed <seed>]
```

- `--size` (required): Number of elements in the random array
- `--csv` (required): Path to the CSV file to append results to
- `--seed` (optional): RNG seed for reproducible inputs (default: 3258)
- `--help`: Show usage instructions

### Examples

```bash
# Benchmark Insertion Sort on 10,000 integers, results to results.csv
java -jar target/insertion_sort-1.0.jar --size 10000 --csv results.csv

# Benchmark with a custom seed for reproducibility
java -jar target/insertion_sort-1.0.jar --size 10000 --csv results.csv --seed 12345
```

### CSV Output Columns

- `algorithm`: Name of the algorithm (InsertionSort)
- `input_size`: Number of elements sorted
- `comparisons`: Number of key comparisons
- `moves`: Number of element moves (assignments)
- `array_accesses`: Total array reads/writes
- `time_ns`: Elapsed time in nanoseconds

Tip: Use `--seed` to make runs reproducible for fair comparisons.

</details>

## JMH Benchmarking

<details>
<summary><strong>Show JMH usage and memory profiling</strong></summary>

The project includes a JMH harness for accurate benchmarking of Insertion Sort across various input sizes and distributions (random, sorted, reverse-sorted, nearly-sorted).

### Memory and GC Profiling

JMH can report memory allocation and garbage collection impact. This project enables GC profiling by default in the JmhRunner. Example output columns:

- `gc.alloc.rate` (MB/sec): Allocation rate
- `gc.alloc.rate.norm` (B/op): Bytes allocated per operation
- `gc.count`: Number of GC events
- `gc.time` (ms): Time spent in GC

### Benchmarking Results

| Input Type      | Size    | Avg Time (ms/op) |
|-----------------|---------|------------------|
| Nearly Sorted   |   100   |     ≈ 10⁻³       |
| Nearly Sorted   |  1000   |     0.008        |
| Nearly Sorted   | 10000   |     0.509        |
| Nearly Sorted   |100000   |    44.561        |
| Random          |   100   |     0.002        |
| Random          |  1000   |     0.178        |
| Random          | 10000   |    14.840        |
| Random          |100000   |  1789.367        |
| Reverse         |   100   |     0.004        |
| Reverse         |  1000   |     0.355        |
| Reverse         | 10000   |    36.643        |
| Reverse         |100000   | 10983.204        |
| Sorted          |   100   |     ≈ 10⁻⁴       |
| Sorted          |  1000   |     0.001        |
| Sorted          | 10000   |     0.009        |
| Sorted          |100000   |     0.080        |


</details>

## Asymptotic Complexity Analysis

### Time Complexity

- Best Case (Ω(n)):
The array is already sorted.
Each element is compared once with its predecessor, resulting in a single pass through the array.
- Worst Case (O(n²)):
The array is sorted in reverse order. Each new element must be compared with all previously sorted elements and shifted to the front.
- Average Case (Θ(n²)):
Randomly ordered array. On average, each element is compared with half of the sorted portion before being inserted.

### Space Complexity

- Auxiliary Space & In-Place Optimization:
O(1) auxiliary space since Insertion Sort is an in-place algorithm.
All insertions and shifts are performed within the original array, with no need for extra arrays or buffers.

### Recurrence Relations

- For each element from index 1 to n-1, it may need to be compared with all previous elements (in the worst case).
- For the i-th element (i from 1 to n-1), in the worst case, it is compared with all i previous elements.

This gives the recurrence relation of `T(n) = T(n-1) + (n-1)` which simplifies to `T(n) = O(n²)`

### Summary

| Case   | Time Complexity | Space Complexity | Notes                  |
|--------|----------------|------------------|------------------------|
| Best   | Ω(n)           | O(1)             | Already sorted array   |
| Average| Θ(n²)          | O(1)             | Random order           |
| Worst  | O(n²)          | O(1)             | Reverse sorted array   |
