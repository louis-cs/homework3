package edu.postech.csed332.homework3;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

/**
 * An implementation of Graph with an adjacency list representation.
 * NOTE: you should NOT add more member variables to this class.
 *
 * @param <N> type of vertices, which must be immutable and comparable
 */
public class AdjacencyListGraph<N extends Comparable<N>> implements MutableGraph<N> {

    /**
     * A map from vertices to the sets of their adjacent vertices. For example, a graph
     * with four vertices {v1, v2, v3, v4} and four edges {(v1,v1), (v1,v2), (v3,v1), (v3,v2)}
     * is represented by the map {v1 |-> {v1,v2}, v2 |-> {}, v3 -> {v1,v2}, v4 -> {}}.
     */
    private final @NotNull SortedMap<N, SortedSet<N>> adjMap;

    /**
     * Creates an empty graph
     */
    public AdjacencyListGraph() {
        adjMap = new TreeMap<>();
    }

    @Override
    public boolean containsVertex(@NotNull N vertex) {
        for (Map.Entry<N,SortedSet<N>> entry : adjMap.entrySet()){
            if (entry.getKey().equals(vertex)) return true;
        }
        return false;
    }

    @Override
    public boolean addVertex(@NotNull N vertex) {
        if (this.containsVertex(vertex)) return false;
        else {
            adjMap.put(vertex, new TreeSet<N>());
            return true;
        }
    }

    @Override
    public boolean removeVertex(@NotNull N vertex) {
        if (!this.containsVertex(vertex)) return false;
        else {
            adjMap.remove(vertex);
            for (Map.Entry<N,SortedSet<N>> entry : adjMap.entrySet()){
                if (entry.getValue().contains(vertex)) entry.getValue().remove(vertex);
            }
            return true;
        }
    }

    @Override
    public boolean containsEdge(@NotNull N source, @NotNull N target) {
        if ((!this.containsVertex(source) || (!this.containsVertex(target)))) return false;
        else {
            for (Map.Entry<N, SortedSet<N>> entry : adjMap.entrySet()) {
                if (entry.getKey().equals(source)){
                    if (entry.getValue().contains(target)) return true;
                }
            }
            return false;
        }
    }

    @Override
    public boolean addEdge(@NotNull N source, @NotNull N target) {
        if (this.containsEdge(source, target)) return false;
        else{
            adjMap.get(source).add(target);
            return true;
        }
    }

    @Override
    public boolean removeEdge(@NotNull N source, @NotNull N target) {
        if (!this.containsEdge(source, target)) return false;
        else{
            adjMap.get(source).remove(target);
            return true;
        }
    }

    @Override
    public @NotNull Set<N> getSources(N target) {
        TreeSet<N> sources = new TreeSet<>();
        for (Map.Entry<N, SortedSet<N>> entry : adjMap.entrySet()){
            if (entry.getValue().contains(target)) sources.add(entry.getKey());
        }
        return sources;
    }

    @Override
    public @NotNull Set<N> getTargets(N source) {
        return adjMap.get(source);
    }

    @Override
    public @NotNull Set<N> getVertices() {
        return Collections.unmodifiableSet(adjMap.keySet());
    }

    @Override
    public @NotNull Set<Edge<N>> getEdges() {
        return adjMap.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream().map(n -> new Edge<>(entry.getKey(), n)))
                .collect(Collectors.toUnmodifiableSet());
    }

    /**
     * Checks if all class invariants hold for this object.
     *
     * @return true if the representation of this graph is valid
     */
    boolean checkInv() {
        for (Map.Entry<N, SortedSet<N>> entry : adjMap.entrySet()){
            Iterator<N> iterator = entry.getValue().iterator();
            while (iterator.hasNext()){
                N target = iterator.next();
                if (!adjMap.containsKey(target)) return false;
            }
        }
        return true;
    }

    /**
     * Provides a human-readable string representation for the abstract value of the graph
     *
     * @return a string representation
     */
    @Override
    public String toString() {
        return String.format("[vertex: {%s}, edge: {%s}]",
                getVertices().stream().map(N::toString).collect(Collectors.joining(", ")),
                getEdges().stream().map(Edge::toString).collect(Collectors.joining(", ")));
    }
}
