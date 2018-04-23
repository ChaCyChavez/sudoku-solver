#include <stdio.h>
#include <stdlib.h>


void printPuzzle(int ** puzzle, int puzzle_size){
  // Print puzzle
  int i, j;
  for(i = 0; i < puzzle_size; i++) {
    for(j = 0; j < puzzle_size; j++) {
      printf("%d\t", puzzle[i][j]);
    }
    printf("\n");
  }
  printf("\n");
}

int solved(int ** puzzle, int puzzle_size) {
  int i, j;
  for(i = 0; i < puzzle_size; i++) {
    for(j = 0; j < puzzle_size; j++) {
      if(puzzle[i][j] == 0){
        return 0;
      }
    }
  }

  return 1;
}

void findNext(int ** puzzle, int puzzle_size, int *row, int *col){
  int i, j;
  for(i = 0; i < puzzle_size; i++) {
    for(j = 0; j < puzzle_size; j++) {  	
      if(puzzle[i][j] == 0){
        (*row) = i;
        (*col) = j;
        return;
      }
    }
  }  
}

int hasConflict(int ** puzzle, int subgrid_size, int puzzle_size, int currRow, int currCol){
  int i, j, rowMin, rowMax, colMin, colMax;

  //check row
  for(i=0; i<puzzle_size; i++){
    if(currCol != i && puzzle[currRow][i] == puzzle[currRow][currCol]){
      return 1;
    }
  }

  //check column
  for(i=0; i<puzzle_size; i++){
    if(currRow != i && puzzle[i][currCol] == puzzle[currRow][currCol]){
      return 1;
    }
  }

  //check box or quadrant
  if((currRow + 1) == 1){
  	rowMin = 0;
  	rowMax = subgrid_size;
  }else if((currRow + 1)%subgrid_size == 0){
  	rowMin = (currRow + 1) - subgrid_size;
  	rowMax = currRow + 1;
  }else{
  	rowMax = currRow + 1;
  	while(1){
  		rowMax++;
  		if(rowMax%subgrid_size == 0) break;
  	}
  	rowMin = rowMax - subgrid_size;
  }

  if((currCol + 1) == 1){
  	colMin = 0;
  	colMax = subgrid_size;
  }else if((currCol + 1)%subgrid_size == 0){
  	colMin = (currCol + 1) - subgrid_size;
  	colMax = currCol + 1;
  }else{
  	colMax = currCol + 1;
  	while(1){
  		colMax++;
  		if(colMax%subgrid_size == 0) break;
  	}
  	colMin = colMax - subgrid_size;
  }  


	for(i=rowMin; i<rowMax; i++){
		for(j=colMin; j<colMax; j++){
	    if((currRow != i && currCol != j) && 
	    	puzzle[i][j] == puzzle[currRow][currCol]){
	      return 1;
	    }
		}
	}  
  return 0;
}

int solvePuzzle(int ** puzzle, int subgrid_size, int puzzle_size, int *row, int *col, int *num_of_sols){
  int i, j, x;
  int currRow = *row;
  int currCol = *col;
  if(solved(puzzle, puzzle_size) == 1){
  	(*num_of_sols)++;
  	printf("Solution #%d:\n", (*num_of_sols));
  	printPuzzle(puzzle, puzzle_size);
    return 1;
  }

  for(i=1; i<puzzle_size+1; i++){
    puzzle[currRow][currCol] = i;
    if(hasConflict(puzzle, subgrid_size, puzzle_size, currRow, currCol) == 0){
      findNext(puzzle, puzzle_size, row, col);
      // if(solvePuzzle(puzzle, subgrid_size, puzzle_size, row, col) == 1){
      //   return 1;
      // }
      solvePuzzle(puzzle, subgrid_size, puzzle_size, row, col, num_of_sols);
    }
  }
  puzzle[currRow][currCol] = 0; 
  return 0;
}

int main(void) {
  int num_of_puzzles;
  int subgrid_size, puzzle_size;
  int i, j;
  int **puzzle;
  int row, col;
  int num_of_sols=0;

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

    printPuzzle(puzzle, puzzle_size);
    findNext(puzzle, puzzle_size, &row, &col);
    solvePuzzle(puzzle, subgrid_size, puzzle_size, &row, &col, &num_of_sols);
    printf("Number of solutions: %d\n\n", num_of_sols);
    // printPuzzle(puzzle, puzzle_size);
    // Free puzzle
    for(i = 0; i < subgrid_size; i++) {
      free(puzzle[i]);
    }

    free(puzzle);
  }

  fclose(file);

  return 0;
}