# Problem 1


Let $`\mathcal{N}`$ be the set of all elements of type $`\textsf{N}`$, and $`\mathsf{null} \notin \mathcal{N}`$ be an distinguished element to represent `null`. Write formal abstract specifications of the interfaces below with respect to following abstract values:

- A graph is a pair $`G = (V, E)`$, where $`V \subseteq \mathcal{N}`$ and $`E \subseteq V \times V`$.
- A tree is a triple $`T = (V, E, \hat{v})`$, where $`(V, E)`$ is a graph and $`\hat{v} \in \mathcal{N}`$ denotes the root.

Other data types, such as `boolean`, `int`, `Set<N>`, etc. have conventional abstract values, e.g., Boolean values, integers, and subsets of $`\mathcal{N}`$, etc.

## `Graph<N>`

Let $`G_{this} = (V_{this}, E_{this})`$ be an abstract value of the current graph object. 

##### Class invariant 

```math
\forall (v, w) \in E_{this}.\ v, w \in V_{this}
```

##### containsVertex

```java 
boolean containsVertex(N vertex);
```

- requires: vertex is in $`\mathcal{N}`$ and not $`\mathsf{null}`$
- ensures:  
    + returns true if vertex is in $`V_{this}`$; and
    - returns false, otherwise.

##### containsEdge

```java
boolean containsEdge(N source, N target);
```

- requires:
    * source is in $`\mathcal{N}`$ and not $`\mathsf{null}`$
    * target is in $`\mathcal{N}`$ and not $`\mathsf{null}`$
- ensures:
    + returns true if the edge (source, target) is in $`E_{this}`$; and
    - returns false, otherwise.

##### getSources

```java
Set<N> getSources(N target);
```

- requires: target is in $`\mathcal{N}`$
- ensures:  
    + returns the set of vertices that have an edge to target if the target is in the graph, immutable
    - returns the empty set if target is not in the graph
##### getTargets

```java
Set<N> getTargets(N source);
```

- requires: source is in $`\mathcal{N}`$
- ensures:  
    + returns the set of vertices that have an edge from source if source is in the graph, immutable
    - returns the empty set if the target is not in the graph

## `Tree<N>`

Let $`T_{this} = (V_{this}, E_{this}, \hat{v}_{this})`$ be an abstract value of the current graph object. 

##### Class invariant 

```math
\forall (v, w) \in E_{this}.\ v, w \in V_{this};  
\forall v \in E_{this}, \exists ! \ set \ of \ unique \ edges \ linking \ \hat{v}_{this} \ to \ v
```

##### getDepth

```java
int getDepth(N vertex);
```

- requires: 
  + vertex is in $`\mathcal{N}`$ and not $`\mathsf{null}`$; and
  + getRoot.isPresent()
- ensures:  
  + returns 0 if vertex is getRoot.get(); and
  + returns getDepth(getParent(vertex)) + 1, otherwise.

##### getHeight

```java
int getHeight();
```

- requires: getRoot.isPresent()
- ensures:  
    + returns the maximum depth of this tree

##### getRoot

```java
Optional<N> getRoot();
```

- requires:
- ensures: 
    + returns the root of this tree if it is not empty
    + returns Optional.empty(), otherwise

##### getParent

```java
Optional<N> getParent(N vertex);
```

- requires: vertex is in $`\mathcal{N}`$
- ensures:
    + returns getSources(vertex).stream().findAny() if vertex is not the root; and
    + returns Optional.empty(), otherwise


## `MutableGraph<N>`

Let $`G_{this} = (V_{this}, E_{this})`$ be an abstract value of the current graph object,
and $`G_{next} = (V_{next}, E_{next})`$ be an abstract value of the graph object _modified by_ the method call. 

##### Class invariant 

```math
\forall (v, w) \in E_{this}.\ v, w \in V_{this}
```

##### addVertex

```java
boolean addVertex(N vertex);
```

- requires: vertex is in $`\mathcal{N}`$ and not $`\mathsf{null}`$
- ensures:  
    + $`V_{next} = V_{this} \cup \{\texttt{vertex}\}`$; 
    + $`E_{next} = E_{this}`$ (the edges are not modified)
    + If $`G_{this}`$ satisfies the class invariant, $`G_{next}`$ also satisfies the class invariant; and
    + returns true if and only if $`\texttt{vertex} \notin V_{this}`$.

##### removeVertex

```java
boolean removeVertex(N vertex);
```

- requires: vertex is in $`\mathcal{N}`$ and not $`\mathsf{null}`$
- ensures:
    + $`V_{next} = V_{this} \setminus \{\texttt{vertex}\}`$;
    + $`E_{next} = E_{this} \setminus \{\texttt{e} \in E_{this}, \texttt{target} \in V_{this} \mid \texttt{e = (vertex, target) or e = (target, vertex)}`$
    + If $`G_{this}`$ satisfies the class invariant, $`G_{next}`$ also satisfies the class invariant; and
    + returns true if $`\texttt{vertex} \in V_{this}`$.
    + returns false otherwise

##### addEdge

```java
boolean addEdge(N source, N target);
```

- requires:
    + source is in $`\mathcal{N}`$ and not $`\mathsf{null}`$
    + target is in $`\mathcal{N}`$ and not $`\mathsf{null}`$
- ensures:
    + $`E_{next} = E_{this} \cup \texttt{\{(source, target)\}}`$
    + $`V_{next} = V_{this}`$ (the vertices are not modified)
    + If $`G_{this}`$ satisfies the class invariant, $`G_{next}`$ also satisfies the class invariant; and
    + returns true if $`\texttt{(source, target)} \notin E_{this}`$.
    + returns false otherwise.


##### removeEdge

```java
boolean removeEdge(N source, N target);
```

- requires: 
    + source is in $`\mathcal{N}`$ and not $`\mathsf{null}`$
    + target is in $`\mathcal{N}`$ and not $`\mathsf{null}`$
- ensures: 
    + $`E_{next} = E_{this} \setminus \texttt{\{(source, target)\}}`$
    + $`V_{next} = V_{this}`$ (the vertices are not modified)
    + If $`G_{this}`$ satisfies the class invariant, $`G_{next}`$ also satisfies the class invariant; and
    + returns true if $`\texttt{(source, target)} \in E_{this}`$.
    + returns false otherwise.


## `MutableTree<N>`

Let $`T_{this} = (V_{this}, E_{this}, \hat{v}_{this})`$ be an abstract value of the current tree object,
and $`T_{next} = (V_{next}, E_{next}, \hat{v}_{next})`$ be an abstract value of the tree object _modified by_ the method call. 

##### Class invariant 

```math 
\forall v \in E_{this}, \exists ! \ set \ of \ unique \ edges \ linking \ \hat{v}_{this} \ to \ v
```

##### addVertex

```java
boolean addVertex(N vertex);
```

- requires:
    + E_{this} is empty
    + vertex is in $`\mathcal{N}`$ is not $`\mathsf{null}`$
- ensures:
    + T_{next} = (\{vertex}, $`\emptyset`$ , vertex)
    + returns true if the Tree was empty
    + returns false otherwise

##### removeVertex

```java
boolean removeVertex(N vertex);
```

- requires: vertex is in $`\mathcal{N}`$ and not $`\mathsf{null}`$
- ensures:
    + $`V_{next} = V_{this} \setminus  (\{\texttt{vertex}\} \cup \texttt{all vertices following vertex})`$;
    + returns true if $`\texttt{vertex} \in V_{this}`$.
    + returns false otherwise

##### addEdge

```java
boolean addEdge(N source, N target);
```

- requires:
    + source is in $`\mathcal{N}`$ and not $`\mathsf{null}`$
    + target is in $`\mathcal{N}`$ and not $`\mathsf{null}`$
- ensures:
    + $`E_{next} = E_{this} \cup \texttt{\{(source, target)\}}`$
    + $`V_{next} = V_{this} \cup \{\texttt{target}\}`$
    + returns true if $`\texttt{source} \in E_{this}`$ and $`\texttt{target} \notin E_{this}`$
    + returns false otherwise.

##### removeEdge

```java
boolean removeEdge(N source, N target);
```

- requires:
    + source is in $`\mathcal{N}`$ and not $`\mathsf{null}`$
    + target is in $`\mathcal{N}`$ and not $`\mathsf{null}`$
- ensures:
    + $`V_{next} = V_{this} \setminus (\texttt{(source, target)}`$
    + $`V_{next} = V_{this} \setminus  (\{\texttt{target}\} \cup \texttt{target.getChildren()})`$;
    + returns true if $`\texttt{vertex} \in V_{this}`$.
    + returns false otherwise


# Problem 2

Identify whether the abstract interfaces satisfy the Liskov substitution principle.
For each question, explain your reasoning _using the abstract specifications that you have defined in Problem 1_. 


##### `Tree<N>` and `Graph<N>`

* Is `Tree<N>` a subtype of `Graph<N>`?  
Yes, `Tree<N>` is a subtype of `Graph<N>`. `Tree<N>` has stronger specfications in its class invariants
 (having the class invariants of Graph<N> and an additional one). Its preconditions and 
 postconditions are the same, as the Tree is immutable.

##### `MutableGraph<N>` and `Graph<N>`

* Is `MutableGraph<N>` a subtype of `Graph<N>`  
Yes, `MutableGraph<N>` is a subtype of `Graph<N>` : the class invariants are the same, there is not 
  method override, and since every added method ensures the class invariants stay true, then the
   preconditions and postconditions are also the same.

##### `MutableTree<N>` and `Tree<N>`

* Is `MutableTree<N>` a subtype of `Tree<N>`  
No, `MutableTree<N>` is not a subtype of `Tree<N>`. `MutableTree<N>` can have edges  pointing to deleted
 vertices after using the removeVertex() method. its class invariants are therefore weaker.

##### `MutableTree<N>` and `MutableGraph<N>`   

* Is `MutableTree<N>` a subtype of `MutableGraph<N>`  
No, `MutableTree<N>` is not a subtype of `MutableGraph<N>`, for the same reason that it is not
a subtype of `Tree<N>` : its class invariants are weaker, since it does not always satisfy the
requirement of having all edges being between existing vertices.