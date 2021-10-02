#include <stdlib.h>
#include <stdbool.h>
#include <stdio.h>

int main(int argc, char* argv[])
{
    //getting matrix a
    FILE* matrix_a_fp = fopen(argv[1], "r");
    if (!matrix_a_fp) {
        perror("fopen failed");
        return EXIT_FAILURE;
    }

    char buff[256];

    fscanf(matrix_a_fp, "%s", buff);
    char length_l = atoi(buff);
    int** matrix_a = malloc( length_l * sizeof(int*) );

    fscanf(matrix_a_fp, "%s", buff);
    char length_m = atoi(buff);
    for ( unsigned char i=0; i<length_l; i++ ) {
        matrix_a[i] = malloc( length_m * sizeof(int) );
    }

    for(int i = 0; i < length_l; i++){
      for(int j = 0; j < length_m; j++){
         fscanf(matrix_a_fp, "%s", buff);
         *(matrix_a[i] + j) = atoi(buff);
      }
    }
    fclose(matrix_a_fp);

    //getting matrix b
    FILE* matrix_b_fp = fopen(argv[2], "r");
    if (!matrix_b_fp) {
        perror("fopen failed");
        return EXIT_FAILURE;
    }

    char buff2[256];

    fscanf(matrix_b_fp, "%s", buff2);
    char length_l2 = atoi(buff2);
    int** matrix_b = malloc( length_l2 * sizeof(int*) );

    fscanf(matrix_b_fp, "%s", buff2);
    char length_m2 = atoi(buff2);
    for ( unsigned char i=0; i<length_l2; i++ ) {
        matrix_b[i] = malloc( length_m2 * sizeof(int) );
    }

    for(int i = 0; i < length_l2; i++){
      for(int j = 0; j < length_m2; j++){
         fscanf(matrix_b_fp, "%s", buff2);
         *(matrix_b[i] + j) = atoi(buff2);
      }
    }
    fclose(matrix_b_fp);

    //multiplying matrices
    int final_matrix[length_l * length_m2];
    int z = 0;
    for(int i = 0; i<length_l; i++){
      for(int j = 0; j<length_m2; j++){
        int num = 0;
        for(int k = 0; k < length_m; k++){
          num = num + ((*(matrix_a[i] + k)) * (*(matrix_b[k] + j)));
        }
        final_matrix[z] = num;
        z++;
      }
    }

    for ( unsigned char i=0; i<length_l; i++ ) {
        free( matrix_a[i] );
    }
    free( matrix_a );
    for ( unsigned char i=0; i<length_l2; i++ ) {
        free( matrix_b[i] );
    }
    free( matrix_b );

    for(int i = 0; i < length_l*length_m2; i++){
      printf("%d ", final_matrix[i]);
    }
    return 0;

}
