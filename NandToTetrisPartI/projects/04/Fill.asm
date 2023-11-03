// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

// Put your code here.

(START)
@KBD		//listen input if any key is pressed
D=M
@START
D;JEQ

@SCREEN		//set screen array
D=A
@screenarray
M=D

@8192
D=A
@n
M=D

@i
M=0


(LOOP)
@i       //if (i==n) then go to WAIT
D=M
@n
D=D-M
@WAIT
D;JEQ

@screenarray	//fill the screen black
D=M
@i
A=D+M
M=-1

@i				//i++
M=M+1

@LOOP
0;JMP

(WAIT)		//wait until key is unpressed
@KBD
D=M
@WAIT
D;JNE

	//reset the screen
@SCREEN		//set screen array
D=A
@screenarray
M=D

@8192
D=A
@n
M=D

@i
M=0


(LOOP1)
@i       //if (i==n, screen is clear) then go to START
D=M
@n
D=D-M
@START
D;JEQ

@screenarray	//fill the screen black
D=M
@i
A=D+M
M=0

@i				//i++
M=M+1

@LOOP1
0;JMP





