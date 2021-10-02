#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>

//Function declerations
bool is_prime(int n);

int main(int argc, char* argv[]) {
  int number = atoi(argv[1]);
  if((number > 5)&&((number % 2) == 1)){

    //find all prime numbers from 0 to number
    int h = 0;
    int primes[number-1];
    for(int i = 2; i < number;i++){
      if(is_prime(i)==true){
        primes[h] = i;
        h++;
      }
    }

    //using the primes in array, find combination
    int x;
    int y;
    int z;
    for(int i = 0; i < h; i++ ){
      x = primes[i];
      for(int j = 0; j < h; j++){
        y = primes[j];
        for(int k = 0; k < h; k++){
          z = primes[k];
          if((x + y + z) == number){
            printf("%d = %d + %d + %d", number, x, y, z);
            return EXIT_SUCCESS;
          }
        }
      }
    }
  }
}


//check if number is prime
bool is_prime(int n){
  if(n <= 1){
      return false;
  }
  else{
    for(int i = 2; i<n; i++){
      if(n % i == 0){
        return false;
      }
    }
  }
  return true;
}
