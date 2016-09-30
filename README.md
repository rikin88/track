The horsetrack application written using Java 8. Here are the instructions to run the program:

1. Import as Maven project using Eclipse (or another IDE of your choice)
2. Ensure Java Compiler and Installed JRE is set to run JAVA 8.
3. run mvn install
4. run mvn test 
5. Right click on TrackMain.java  and run as Java Application
6. Enter user commands.


Sample Output:

-----Main menu----- 
	R or r to restock inventory
	Q or q to quit the application
	W or w [1-7] to set winning horse number
	[1-7] <amount> to specify horse and bet amount
	DONE to print output
---------------
1 55
2 25
W 4
4 10.25
10 55
DONE

Inventory ----
$1, 10
$5, 10
$10, 10
$20, 10
$100, 10

Horses ---
1, That Darn Gray Cat, 5, Won
2, Fort Utopia, 10, Lost
3, Count Sheep, 9, Lost
4, Ms Traitour, 4, Lost
5, Real Princess, 3, Lost
6, Pa Kettle, 5, Lost
7, Gin Stinger, 6, Lost


PAYOUT: That Darn Gray Cat, $275

Inventory ----
$1, 10
$5, 9
$10, 9
$20, 7
$100, 8

Horses ---
1, That Darn Gray Cat, 5, Won
2, Fort Utopia, 10, Lost
3, Count Sheep, 9, Lost
4, Ms Traitour, 4, Lost
5, Real Princess, 3, Lost
6, Pa Kettle, 5, Lost
7, Gin Stinger, 6, Lost


NO PAYOUT: Fort Utopia

Inventory ----
$1, 10
$5, 9
$10, 9
$20, 7
$100, 8

Horses ---
1, That Darn Gray Cat, 5, Won
2, Fort Utopia, 10, Lost
3, Count Sheep, 9, Lost
4, Ms Traitour, 4, Lost
5, Real Princess, 3, Lost
6, Pa Kettle, 5, Lost
7, Gin Stinger, 6, Lost


SETTING WINNING HORSE NUMBER: 4

Inventory ----
$1, 10
$5, 9
$10, 9
$20, 7
$100, 8

Horses ---
1, That Darn Gray Cat, 5, Lost
2, Fort Utopia, 10, Lost
3, Count Sheep, 9, Lost
4, Ms Traitour, 4, Won
5, Real Princess, 3, Lost
6, Pa Kettle, 5, Lost
7, Gin Stinger, 6, Lost


INVALID BET: 10.25

Inventory ----
$1, 10
$5, 9
$10, 9
$20, 7
$100, 8

Horses ---
1, That Darn Gray Cat, 5, Lost
2, Fort Utopia, 10, Lost
3, Count Sheep, 9, Lost
4, Ms Traitour, 4, Won
5, Real Princess, 3, Lost
6, Pa Kettle, 5, Lost
7, Gin Stinger, 6, Lost


INVALID HORSE NUMBER: 10

Inventory ----
$1, 10
$5, 9
$10, 9
$20, 7
$100, 8

Horses ---
1, That Darn Gray Cat, 5, Lost
2, Fort Utopia, 10, Lost
3, Count Sheep, 9, Lost
4, Ms Traitour, 4, Won
5, Real Princess, 3, Lost
6, Pa Kettle, 5, Lost
7, Gin Stinger, 6, Lost


