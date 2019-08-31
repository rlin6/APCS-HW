/* 

Sieve of Eratosthenes
1. To find nth prime:
    1. Create an array of potential primes, setting each entry to the same value
        To find the nth prime, (for sufficiently large primes), you need:
            n*ln(n) * 1.15 buckets //in c, log(n) in <math.h> is ln(n)
    2. Mark 2 as prime, then mark all multiples of 2 as composite
    3. When you've marked the entire list, go back to 2 and find the next entry containing the default value


*/