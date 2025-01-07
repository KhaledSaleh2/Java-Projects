# Java-Projects

These are Java projects I've completed through CS 400. These projects include various data structures, implementations of algorithms, and collaborative projects. Continue reading for more detailed descriptions of the projects:

## P1: Hashtable

The **Hashtable** project implements a basic hashtable structure. The hashtable is designed to store "posts" that include the following information:
- **Title**: The title of the post.
- **Body**: The content of the post.
- **URL**: A link related to the post.

Posts can be searched by either their title or body. This project is a simple demonstration of how a hashtable works with key-value pairs, and it includes functionality to handle collisions.

## P2: DicSearch

**DicSearch** is a dictionary application that allows users to:
- Load words from a CSV file in the format `word,type,definition`.
- Manually insert new words into the dictionary.
- Remove words from the dictionary.

This project is designed to provide a basic interface for users to manage a collection of words, their types, and definitions. It's a great way to explore file input/output in Java and implement basic search functionalities for word lookup.

## P3: Map of Madison

The **Map of Madison** project represents a simplified graph where vertices are locations on the UW-Madison campus, and the edges represent the distances (in arbitrary units) between those locations. 

Key features of this project:
- **Graph Representation**: Locations on the map are modeled as vertices, and edges represent weighted distances between locations.
- **Dijkstra's Algorithm**: The project implements **Dijkstra's algorithm** to find the shortest path between two locations on campus. This is useful for users to determine the most efficient route to take.

Although the distances between locations are not perfectly accurate, the goal of this project is to showcase graph theory and the implementation of Dijkstra's algorithm in Java.

## Learning Objectives

- **Data Structures**: Gain experience implementing basic data structures like hash tables and graphs.
- **Algorithms**: Understand and apply algorithms such as Dijkstra's algorithm for pathfinding.
- **File I/O**: Learn how to work with files in Java, such as reading CSV files.
- **Collaborative Development**: Experience working in collaborative settings for larger projects.
