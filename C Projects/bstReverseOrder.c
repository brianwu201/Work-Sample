#include <stdlib.h>
#include <stdbool.h>
#include <stdio.h>



typedef struct bstNode bstNode;

struct bstNode {
    int val;
    struct bstNode* l_child;
    struct bstNode* r_child;
};

struct bstNode* insert(struct bstNode* root, int key){

  if(root == NULL){
    root = malloc(sizeof(bstNode));
    root->l_child = NULL;
    root->r_child = NULL;
    root->val = key;
  }

  if(key < root->val){
    root->l_child = insert(root->l_child, key);
  }
  else if(key == root->val){
  }
  else{
    root->r_child = insert(root->r_child, key);
  }
  return root;
}

void delete(struct bstNode* root);
void reverseOrder(struct bstNode* root);

void delete(bstNode* root){
  if(root->r_child != NULL){
    delete(root->r_child);
  }
  if(root->l_child != NULL){
    delete(root->l_child);
  }
  free(root);
}

void reverseOrder(bstNode* root){
  //go to righmost child
  if(root->r_child != NULL){
    reverseOrder(root->r_child);
  }
  //print it
  int key = root->val;
  printf("%d ", key);
  //go to left
  if(root->l_child != NULL){
    reverseOrder(root->l_child);
  }

}

int main(int argc, char* argv[])
{
    //getting tree
    FILE* fp = fopen(argv[1], "r");
    if (!fp) {
        perror("fopen failed");
        return EXIT_FAILURE;
    }

    struct bstNode* root = NULL;


    char buff[256];
    while ( fscanf(fp, "%s", buff)!=EOF ) {
      int k = atoi(buff);
      root = insert(root, k);
    }
    fclose(fp);

    //reverse order traversal
    reverseOrder(root);

    //free BST nodes
    delete(root);
    return 0;
}
