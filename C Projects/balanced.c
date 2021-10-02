#include <stdlib.h>
#include <stdbool.h>
#include <stdio.h>

// Struct to hold the expected close brace character and a pointer to the next element.
struct element {
    char close;
    struct element* next;
};

void push(struct element** top, int new);
int pop(struct element** top);

void push(struct element** top, int new){
  struct element* new_top = (struct element*)malloc(sizeof(struct element));
  new_top->close = new;
  new_top->next = (*top);
  (*top) = new_top;
}

int pop(struct element** top){
  struct element* temp;
  char temp_close;

  temp = *top;
  temp_close = temp->close;
  *top = temp->next;
  free(temp);
  return temp_close;
}

int main(int argc, char* argv[])
{

    FILE* fp = fopen(argv[1], "r");
    if (!fp) {
        perror("fopen failed");
        return EXIT_FAILURE;
    }

    bool balanced = true;

    struct element* stack = NULL;
    char buff;
    while ( fscanf(fp, "%c", &buff)==1 ) {
      if(buff == '{' || buff == '(' || buff == '<' || buff == '['){
        char close_brace;
        if(buff == '{'){
          close_brace = '}';
          push(&stack, close_brace);
        }
        else if(buff == '('){
          close_brace = ')';
          push(&stack, close_brace);
        }
        else if(buff == '<'){
          close_brace = '>';
          push(&stack, close_brace);
        }
        else if(buff == '['){
          close_brace = ']';
          push(&stack, close_brace);
        }
      }
      else if(buff == '}' || buff == ')' || buff == '>' || buff == ']'){
        if(stack == NULL){
          balanced = false;
          break;
        }
        else{
          char c1 = pop(&stack);
          if(c1 == buff){}
          else{
            balanced = false;
            break;
          }
        }
      }
    }
    if(stack == NULL){
      balanced = true;
    }
    else{
      balanced = false;
      while(stack != NULL){
        pop(&stack);
      }
    }
    fclose(fp);
    printf ( balanced ? "yes" : "no" );

    return 0;
}
