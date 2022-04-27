# TreeApp
Recent Java Program
Java based Binary Search Tree Application

This application supports opperations:
search 
insert 
remove 
traversal 
display tree 

Input format:

X1 INT1

X2 INT2

-1

X can be s (search), i (insert), r (remove), or t (traverse) integer,1 integer2 … are either the integer keys of the nodes (for s, i, and r) or the traversal type (1: preorder, 2: inorder, 3: postorder).

Sample Input:

i 53

i 45

i 65

s 35

s 53

r 45

r 49

-1

Output:
Inserting 53: complete.
Inorder traversal:  53  
Inserting 45: complete.
Inorder traversal:  45  53  
Inserting 65: complete.
Inorder traversal:  45  53  65  
Searching for 35: not found.
Searching for 53: found.
Removing 45: complete.
Inorder traversal:  53  65  
Removing 49: not found.
Inorder traversal:  53  65  
......................................................
                                53                                                              
                --                              65                              
......................................................
