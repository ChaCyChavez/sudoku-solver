#include <stdio.h>
#include <stdlib.h>

int main(void) {
  int num_of_puzzles;
  int subgrid_size, puzzle_size;
  int i, j;
  int **puzzle;

  FILE *file = fopen("./input/in.txt", "r");

  fscanf(file, "%d", &num_of_puzzles);

  while(num_of_puzzles--) {
    fscanf(file, "%d", &subgrid_size);
    puzzle_size = subgrid_size * subgrid_size;

    // Initialize puzzle
    puzzle = (int **)malloc(sizeof(int *) * puzzle_size);

    for(i = 0; i < puzzle_size; i++) {
      puzzle[i] = (int *)malloc(sizeof(int) * puzzle_size);
    }
    
    // Fill puzzle 
    for(i = 0; i < puzzle_size; i++) {
      for(j = 0; j < puzzle_size; j++) {
        fscanf(file, "%d", &puzzle[i][j]);
      }
    }

    // Print puzzle
    for(i = 0; i < puzzle_size; i++) {
      for(j = 0; j < puzzle_size; j++) {
        printf("%d\t", puzzle[i][j]);
      }
      printf("\n");
    }

    // Free puzzle
    for(i = 0; i < subgrid_size; i++) {
      free(puzzle[i]);
    }

    free(puzzle);
  }

  fclose(file);

  return 0;
}