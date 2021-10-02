#include "../graphutils.h"

// A program to find a cycle in a directed graph

// You may use DFS or BFS as needed
struct element {
    graphNode_t node;
    struct element* next;
};

//Function Declaration
void push(struct element** top, graphNode_t new);
graphNode_t pop(struct element** top);
bool isCycleDFS(size_t graphNodeCount, AdjacencyListNode* adjacencyList, int* visited, graphNode_t* parents, graphNode_t parent, graphNode_t current);

//Push into stack functions
void push(struct element** top, graphNode_t new){
  struct element* new_top = (struct element*)malloc(sizeof(struct element));
  new_top->node = new;
  new_top->next = (*top);
  (*top) = new_top;
}

//Pop from stack function
graphNode_t pop(struct element** top){
  struct element* temp;
  graphNode_t temp_close;

  temp = *top;
  temp_close = temp->node;
  *top = temp->next;
  free(temp);
  return temp_close;
}

//search through graph for cycle using DFS function
bool isCycleDFS(size_t graphNodeCount, AdjacencyListNode* adjacencyList, int* visited, graphNode_t* parents, graphNode_t parent, graphNode_t current) {
  // there is a cycle back to current
  if(visited[current] == 0){
    graphNode_t temp = parent;
    struct element* stack = NULL;

    //backtrack through cycle and push nodes into stack
    while(temp != current){
      push(&stack, temp);
      temp = parents[temp];
    }
    push(&stack, current);

    //print the cycle in the right order
    while(stack != NULL){
      graphNode_t top = pop(&stack);
      printf("%ld ", top);
    }
    return true;
  }
  //no cycle detected yet
  else {
    visited[current] = 0;
  }
  parents[current] = parent;

  //iterate through
  AdjacencyListNode* neighbor = adjacencyList[current].next;
  bool check = false;
  while(neighbor){
    check = isCycleDFS(graphNodeCount,adjacencyList,visited,parents, current, neighbor->graphNode);
    if(check){
      break;
    }
    neighbor = neighbor->next;
  }
  visited[current] = 1;
  return check;
}
/* ... */

int main ( int argc, char* argv[] ) {

    // READ INPUT FILE TO CREATE GRAPH ADJACENCY LIST
    AdjacencyListNode* adjacencyList;
    size_t graphNodeCount = adjMatrixToList(argv[1], &adjacencyList);
    /* ... */

    bool isCyclic = false;

    int* visited = calloc(graphNodeCount, sizeof(int));
    graphNode_t* parents = calloc(graphNodeCount, sizeof(graphNode_t));

    for (unsigned source=0; source<graphNodeCount; source++) {
      //reset visited array for new source
      for(int i = 0; i< graphNodeCount; i++){
        visited[i] = -1;
      }
      //if cycle is found break the loop since we only need to find one cycle.
      if(isCycleDFS(graphNodeCount,adjacencyList,visited,parents,adjacencyList[source].graphNode, adjacencyList[source].graphNode)){
        isCyclic = true;
        break;
      }
      /* ... */
    }

    if (!isCyclic) { printf("DAG\n"); }

    freeAdjList ( graphNodeCount, adjacencyList );
    free (visited);
    free(parents);
    return EXIT_SUCCESS;
}
