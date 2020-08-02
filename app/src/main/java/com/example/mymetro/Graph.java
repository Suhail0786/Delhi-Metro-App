package com.example.mymetro;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    // No. of vertices in graph
    private int v;
    public static ArrayList <ArrayList<Integer>> allpathList = new ArrayList <ArrayList<Integer>>();


    // adjacency list
    private ArrayList<Integer>[] adjList;

    // path
    private ArrayList<Integer> path;


    //Constructor
    public Graph(int vertices){

        //initialise vertex count
        this.v = vertices;

        // initialise adjacency list
        initAdjList();
    }

    // utility method to initialise
    // adjacency list
    @SuppressWarnings("unchecked")
    private void initAdjList()
    {
        adjList = new ArrayList[v];

        for(int i = 0; i < v; i++)
        {
            adjList[i] = new ArrayList<>();
        }
    }

    // add edge from u to v
    public void addEdge(int u, int v)
    {
        // Add v to u's list.
        adjList[u].add(v);
    }

    // Prints all paths from
    // 's' to 'd'
    public void printAllPaths(int s, int d)
    {
        boolean[] isVisited = new boolean[v];
        ArrayList<Integer> pathList = new ArrayList<>();

        //add source to path[]
        pathList.add(s);

        //Call recursive utility
        printAllPathsUtil(s, d, isVisited, pathList);
    }

    // A recursive function to print
    // all paths from 'u' to 'd'.
    // isVisited[] keeps track of
    // vertices in current path.
    // localPathList<> stores actual
    // vertices in the current path
    private void printAllPathsUtil(Integer u, Integer d,
                                   boolean[] isVisited,
                                   List<Integer> localPathList) {

        // Mark the current node
        isVisited[u] = true;

        if (u.equals(d))
        {
            this.path = new ArrayList<Integer>(localPathList);
            noteThePath(this.path);
            // if match found then no need to traverse more till depth
            isVisited[u] = false;
            return ;
        }

        // Recur for all the vertices
        // adjacent to current vertex
        for (Integer i : adjList[u])
        {
            if (!isVisited[i])
            {
                // store current node
                // in path[] fs
                localPathList.add(i);
                printAllPathsUtil(i, d, isVisited, localPathList);

                // remove current node
                // in path[]
                localPathList.remove(i);
            }
        }

        // Mark the current node
        isVisited[u] = false;
    }

    public void noteThePath(ArrayList <Integer> path){
        this.allpathList.add(path);

        Log.d("route2", "Route is : "+ allpathList);
    }

    public ArrayList<ArrayList<Integer>> getAllPaths(){
        return this.allpathList;
    }
}
