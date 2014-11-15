# include <stdio.h>
FILE *in, *out;
int main()
{
	int n, i, j, t;
	char board[4][4], ch;
	int String_XOT[4][4], Column_XOT[4][4];
	int diagonal_X[2][2], diagonal_O[2][2];
	in = fopen("INPUT.in", "r");
	out = fopen("OUTPUT.txt", "w");
	fscanf(in, "%d", &n);
	fscanf(in, "%c", &ch);
	for (t=0; t<n; t++)
	{
		int flag=0;
		for (i=0; i<4; i++)
			for (j=0; j<4; j++)
			{
				String_XOT[i][j]=0;
				Column_XOT[i][j]=0;
			}
		for (j=0; j<2; j++)
			for (i=0; i<2; i++)
		{
			diagonal_X[i][j]=0;
			diagonal_O[i][j]=0;
		}
		for (i=0; i<4; i++) 
		{
			for (j=0; j<4; j++)
			{
				fscanf(in, "%c", &board[i][j]);
				if (board[i][j]=='X') String_XOT[0][i]++;
				else if (board[i][j]=='O') String_XOT[1][i]++;
				else if (board[i][j]=='.') String_XOT[2][i]++;
				else if (board[i][j]=='T') String_XOT[3][i]++;
				if (i==j) 
				{
					if (board[i][j]=='X') diagonal_X[0][0]++;
					if (board[i][j]=='O') diagonal_O[0][0]++;
					if (board[i][j]=='T') diagonal_X[0][1]++;
					if (board[i][j]=='T') diagonal_O[0][1]++;
				}
				if (i==(3-j)) 
				{
					if (board[i][j]=='X') diagonal_X[1][0]++;
					if (board[i][j]=='O') diagonal_O[1][0]++;
					if (board[i][j]=='T') diagonal_X[1][1]++;
					if (board[i][j]=='T') diagonal_O[1][1]++;
				}
				if (board[i][j]=='X') Column_XOT[0][j]++;
				else if (board[i][j]=='O') Column_XOT[1][j]++;
				else if (board[i][j]=='.') Column_XOT[2][j]++;
				else if (board[i][j]=='T') Column_XOT[3][j]++;
			}	
			fscanf(in, "%c", &ch);
		}
		fscanf(in, "%c", &ch);
		for (i=0; i<4; i++)
		{
			if (String_XOT[0][i]==4 || (String_XOT[0][i]==3 && String_XOT[3][i]==1))
			{
				fprintf(out, "Case #%d: X won\n", t+1);
				flag=1;
				break;
			}
			else if (String_XOT[1][i]==4 || (String_XOT[1][i]==3 && String_XOT[3][i]==1))
			{
				fprintf(out, "Case #%d: O won\n", t+1);
				flag=1;
				break;
			}
			else if (Column_XOT[0][i]==4 || (Column_XOT[0][i]==3 && Column_XOT[3][i]==1))
			{
				fprintf(out, "Case #%d: X won\n", t+1);
				flag=1;
				break;
			}
			else if (Column_XOT[1][i]==4 || (Column_XOT[1][i]==3 && Column_XOT[3][i]==1))
			{
				fprintf(out, "Case #%d: O won\n", t+1);
				flag=1;
				break;
			}
  		
		}
		if (flag!=1)
		{
		if (diagonal_X[0][0]==4 || (diagonal_X[0][0]==3 && diagonal_X[0][1]==1))
		{
				fprintf(out, "Case #%d: X won\n", t+1);
				flag=1;
		}
		else if (diagonal_O[0][0]==4 || (diagonal_O[0][0]==3 && diagonal_O[0][1]==1))
		{
				fprintf(out, "Case #%d: O won\n", t+1);
				flag=1;
		}
		else if (diagonal_X[1][0]==4 || (diagonal_X[1][0]==3 && diagonal_X[1][1]==1))
		{
				fprintf(out, "Case #%d: X won\n", t+1);
				flag=1;
		}
		else if (diagonal_O[1][0]==4 || (diagonal_O[1][0]==3 && diagonal_O[1][1]==1))
		{
				fprintf(out, "Case #%d: O won\n", t+1);
				flag=1;
		}
		}
		j=0;
		if (flag!=1)
		{
			for (i=0; i<4; i++) j+=String_XOT[2][i];
			if (j!=0) fprintf(out, "Case #%d: Game has not completed\n", t+1);
			else fprintf(out, "Case #%d: Draw\n", t+1);

		}
		
	}	
		
	fclose(in);
	fclose(out);
	return 0;
}