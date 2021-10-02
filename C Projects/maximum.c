#include <stdlib.h>
#include <stdio.h>

int main(int argc, char* argv[]) {

  FILE* fp = fopen(argv[1], "r");
  if (!fp) {
    perror("fopen failed");
    return EXIT_FAILURE;
  }

  //declare variables
  int amount_of_numbers;
  int number_of_largest;
  int int_min = -2147483647 - 1;

  //scan first two lines to get how many numbers there are and how many maxes we are looking for
  fscanf(fp, "%d", &amount_of_numbers);
  fscanf(fp, "%d", &number_of_largest);


  //declare arrays to store numbers in
  int number_array[amount_of_numbers];
  int maximums[number_of_largest];

  //set all elements to smallest integer
  for(int i = 0; i< number_of_largest; i++){
    maximums[i] = -2147483647 - 1;
  }

  //put list of numbers into array
  for(int i = 0; i < amount_of_numbers; i++){
    fscanf(fp, "%d", &number_array[i]);
  }

  //finding maximums
  int max_location = 0;
  for(int i = 0; i < number_of_largest; i++){
    for(int j = 0; j < amount_of_numbers; j++){
      if(number_array[j] > maximums[i]){
        maximums[i] = number_array[j];
        max_location = j;
      }
    }
    number_array[max_location] = int_min;
  }

  //printing maximums
  for(int i = 0; i < number_of_largest; i++){
    printf("%d ", maximums[i]);
  }

  fclose(fp);

}
