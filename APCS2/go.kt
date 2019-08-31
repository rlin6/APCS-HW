Ricky Lin
APCS2 pd02
HW#12 -- __+3R|\/|1|\|4|_  1|_|_|\|3$$__
2018-03-02 F

Q0: What do you make of the multiple class definitions in a single .java file?

Both classes are able to be run. However, it seems like one of the class cannot have the signature of public or private placed on it. As long as the class name corresponds to the name of the file, it will seem to work as long as there is only one public or private. However, I don't really see the point of doing so if we can put them as separate files.

Q1: How are the command line arguments processed?

The command line arguments creates an int array with one element controlling the grid size and another one controlling the time of the delay. The size and time can be changed by using scanner to go through the array and changing their values to what we want. 

Q2: What happens if no command line arguments are given?

In this case, the default settings will be used where the grid size will by 8 by 8 and the time of delay will be 500 milliseconds.

Q3: What is the nature/purpose of the recursion? Is it necessary for the animation?

The recursion is used to continuously call up the grid animation again but with a slight change in the grid everytime. This allows the grid to continously refresh and appear as an animation until the base case is reached, which then stops it.

Q4: What do you expect to see when you run it?

I was expecting to see a 8x8 board surrounded by -1s that mark its borders, continously updating itself with increasing numbers appearing on parts of the board previously filled by 0s.

Q5: How long do you expect it to run?

I expect it to run for (grid length* grid length - grid length) * (delay time) in milliseconds. 

Q6: What does ANSI stand for? (You are permitted some intertubes searching here). What is it good for?

ANSI is an acronoym for American Nation Standards Institute. Its function is to make "standards" "nation"ally. It sets the guidelines and basic rules for things that the entire nation agrees on. This is good for the CS community as many conventions will be set to prevent confusion among programmers.

Q7: How did your expectations compare to your observation?

I expected the animation to work more fluently than it did. When I ran it, not all of the 0s will be covered with numbers by the time it stopped. I wasn't really sure why it was happening and how to change it.

Q8: How might you adapt this framework to animate a probing for a Knight's Tour?

The idea of continously freshing the screen when a knight moves allows us to clearly see where the knight is moving everytime and when they have to undo the move they just did. The border of -1s allows us to prevent errors if the knight goes out of bounds and allow us to easily scrap those knights by putting a condition on the -1s. 

Updated Algorithm was very similar to the old one as we couldn't come up with a better one

1) Place a knight in a random patch of the checkboard
2) If the knight has been on every patch, meaning that each one has their number changed to a positive number that indicates that a knight's been there, you found a solution.
3) Find and indicate all the possible movement, shown by changing the number of the available patch to a negative number. This would not account for the ones the knight's already been on.
4) Move the knight to an available patch.
   The knight cannot move onto a location previously occupied the number of the patch would make it impossible. 
   If the knight moves out of bounds onto a -1, undo the step. 
5) If you cannot move your knight anywhere, remove your knight and undo the stamp it has left on its location.
6) Continue making moves with your knight, undoing everytime something goes wrong until:
6) You have found your solution with all the spaces occupied at least once or you realize it doesn't work. 