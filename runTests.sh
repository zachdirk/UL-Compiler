echo testing accepts
echo testing main
java Compiler tests/accept/main.ul
echo testing multFn
java Compiler tests/accept/multFn.ul
echo testing parameter
java Compiler tests/accept/parameter.ul
echo testing parameters and returns
java Compiler tests/accept/parameters.ul
echo testing varDecl
java Compiler tests/accept/varDecl.ul
echo testing assignment
java Compiler tests/accept/assignment.ul
echo testing addition and subtraction
java Compiler tests/accept/addsub.ul
echo testing comparison and if else blocks
java Compiler tests/accept/compare.ul
echo testing while
java Compiler tests/accept/while.ul 
echo testing print
java Compiler tests/accept/print.ul
echo testing expressions
java Compiler tests/accept/expressions.ul

echo testing Jason
echo testing 1-6
java Compiler tests/jason/valid1.ul
java Compiler tests/jason/valid2.ul
java Compiler tests/jason/valid3.ul
java Compiler tests/jason/valid4.ul
java Compiler tests/jason/valid5.ul
java Compiler tests/jason/valid6.ul
echo testing factorial
java Compiler tests/jason/factorial.ul
echo testing rejects
echo testing jason
echo testing 1-4
java Compiler tests/jason/invalid1.ul
java Compiler tests/jason/invalid2.ul
java Compiler tests/jason/invalid3.ul
java Compiler tests/jason/invalid4.ul
echo testing mess
java Compiler tests/reject/mess.ul
echo testing invalid ID
java Compiler tests/reject/badID.ul
echo testing too many braces
java Compiler tests/reject/toomanybraces.ul
echo testing too many parens
java Compiler tests/reject/toomanyparens.ul
