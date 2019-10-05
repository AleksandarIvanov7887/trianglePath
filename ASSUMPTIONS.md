# Assumptions
 
The first assumption, I made, is that, we would wan to check the longest
minimum path, because searching for the shortest, would be trivial.
 
First, I tried to think of a collection that can store this 
triangular array. I thought of a recursive Tree, or a Graph, but
the first option would generate take up a lot of space 
(I thought of a binary tree) and the second one, seemed to make the 
solution more complex. I also, thought of stroing the collection,
somehow, upside dow, from the leaves up, which actually helped me
with the solution later.

Then, I saw that a simple array of arrays would fit. I found out that
a zip on the children would make a perfect sense to the elements above.
Also, here I plugged the idea of going from the back, thus reduce right.

In order to store the path, I first tried a State or Writer Monad, but 
couldn't get them to fit the case, and so I created a new case class
Result, that would store the minimum paths so far.

Because, I introduced a new return type, I would have to switch from
reduce to fold. And because fold needs an initial element, I create an
array of empty Result types, with the length of the last element of the 
triangle array + 1. Artificially created a new row at the back in a way.

The second assumption, I made, is that we would want the application
to work with different types. So, I tries the idea of having Monoids,
because I would want a combine method for the type, and also the empty 
element to perform the Fold.

I also use implicitly an Order type, so that I can perform the minimum
operation. And a Codec type, to encode or decode a String to a type.

Sadly thought, I didn't have time to implement a fail-fast behaviour
in the parsing and to have proper fail messages. Also, couldn't get
to pass the output of `cat << EOF` to the app.

I did all my testing in MainTest.scala