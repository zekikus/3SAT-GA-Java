# Genetic Algorithm for 3-SAT Problem

## The sections related to the definition of the 3-SAT problem are taken from [this paper](https://ieeexplore.ieee.org/abstract/document/7603220).
### Boolean Satisfiability Problem

The Boolean satisfiability problem (SAT) is the problem of determining if there exists an interpretation that satisfies a given Boolean formula. In other words, it asks whether the variables of a given Boolean formula can be consistently replaced by the values TRUE or FALSE in such a way that the formula evaluates to TRUE. If this is the case, the formula is called satisfiable. On the other hand, if no such assignment exists, the function expressed by the formula is FALSE for all possible variable assignments and the formula is unsatisfiable.

#### 3-Satisfiability Problem

The 3-SAT problem means a satisfiability problem, it is problem to find whether a given Boolean formula is satisfiable or not. Here, some related notions are introduced firstly and then we will introduce the 3-SAT in detail:

#### Boolean Variables
The value either True (also can be represented by 1) or False (also can be represented by 0). Let X<sub>n</sub>= x<sub>1</sub>,… ,x<sub>n</sub>   be a collection of Boolean variables.

#### Literal
Literal represented by a Boolean variable (x<sub>i</sub>) or it’s negation formal(¬x<sub>i</sub>), i ∈[1,n].

#### Clause

A Clause means a disjunction of the some literals. Here, we suppose the number of literals is k, so, it can be expressed by the form of l<sub>1</sub> ⋁ l<sub>2</sub> ⋁ ... ⋁ l<sub>k</sub> and l<sub>1</sub>,l<sub>2</sub>,…,l<sub>k</sub> literals corresponding to different variables.

#### 3-SAT Problem Representation

A SAT formula F (in conjunction normal form) in the form of C<sub>1</sub> ∧ … ∧ C<sub>m</sub>, which every C<sub>j</sub> (j∈[1,m]) is a clause. And if each C<sub>j</sub> is a k-clause, the function is a k-SAT formula. When k≤3, then the F is a 3-SAT formula. The problem to find there if have a solution to make such 3-SAT formula is true is called the 3-SAT problem.

For clarity, we use an example to illustrate the problem. The 3-SAT formula can be presented as:

F=(x<sub>1</sub> ∨ ¬x<sub>2</sub> ∨ x<sub>4</sub>) ∧ (¬x<sub>2</sub> ∨ x<sub>3</sub> ∨ x<sub>4</sub> ) ∧ (¬x<sub>3</sub> ∨ ¬x<sub>4</sub> ∨ x<sub>5</sub>)
where the number of Boolean variables n=5, and the number of clause m=3. A solution x_1=false,x_2=true,x_3=true,x_4=true,x_5=true can satisfy all the clauses and make F=true.

#### References:
1. Li, B., & Zhang, Y. A. (2016, August). A hybrid genetic algorithm to solve 3-SAT problem. In 2016 12th International Conference on Natural Computation, Fuzzy Systems and Knowledge Discovery (ICNC-FSKD) (pp. 476-480). IEEE.
2. https://github.com/JaredLGillespie/3SAT-GA-WOC


