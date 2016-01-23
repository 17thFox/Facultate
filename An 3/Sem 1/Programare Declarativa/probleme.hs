afisare::[Int]->[Int]
afisare xs = xs

--Afiseaza numerele pare dintr-o lista.

afisareNumerePare::[Int] -> [Int]
afisareNumerePare [] = []
--afisareNumerePare [x,y,z] = [x,z]
afisareNumerePare (x:xs)
			| x`mod`2 == 0	=	x : afisareNumerePare xs
			| otherwise		=	afisareNumerePare xs

--1. Se da o lista si trebuie sa afisam numerele prime din ea.

isqrt :: Int -> Int
isqrt = floor . sqrt . fromIntegral

isPrime'::Int->Int->Bool
isPrime' x y
				|x < 2 							=	False 
				|x`mod`y == 0 && y <= isqrt x 	=	False
				|y > isqrt x					=	True
				|otherwise 						= 	isPrime' x (y+1)
isPrime::Int->Bool
isPrime x = isPrime' x 2

afisareNumerePrime::[Int]->[Int]
afisareNumerePrime [] = []
afisareNumerePrime (x:xs)
							|isPrime x == True 	=	x : afisareNumerePrime xs
							|otherwise			=	afisareNumerePrime xs

--2. Se da n. Afisati primele n numere prime.

afisarePrimeleN'::Int->Int->[Int]
afisarePrimeleN' numar n
											|n <= 0 	= 	[]
											|isPrime numar == True 	=	numar : afisarePrimeleN' (numar+1) (n-1)
											|otherwise 				=	afisarePrimeleN' (numar+1) n
afisarePrimeleN::Int->[Int]
afisarePrimeleN n = afisarePrimeleN' 2 n

--3. Se da n. Sa se afle cel mai mare numar prim mai mic decat n.

celMaiMareNumarPrim'::Int->Int->Int->Int
celMaiMareNumarPrim' n nrCrt maxPrim
										|nrCrt >= n 							=	maxPrim
										|nrCrt < n && isPrime nrCrt == True 	=	celMaiMareNumarPrim' n (nrCrt+1) nrCrt
										|otherwise								=	celMaiMareNumarPrim' n (nrCrt+1) maxPrim


celMaiMareNumarPrim::Int->Int
celMaiMareNumarPrim n
						|n>=3	 = celMaiMareNumarPrim' n 2 2
						|otherwise	=	error("Numarul n nu poate fi mai mic decat 3!")



--4. Se da n. Aflati toti divizorii primi ai lui n.

stripNumber::Int->Int->Int
stripNumber n nrCrt
						|n`mod`nrCrt == 0	=	stripNumber (n`div`nrCrt) nrCrt
						|otherwise			=	n

divizoriiPrimi'::Int->Int->[Int]
divizoriiPrimi' n nrCrt
						|n <= 1 	=	[]
						|n`mod`nrCrt == 0 	=	nrCrt : divizoriiPrimi' (stripNumber n nrCrt) (nrCrt+1)
						|otherwise 			= 	divizoriiPrimi' n (nrCrt+1)

divizoriiPrimi::Int->[Int]
divizoriiPrimi n = divizoriiPrimi' n 2

--5. Cel mai mare divizor comun a doua numere.
cmmdc::Int->Int->Int
cmmdc a b
			| a < 1 || b < 1 	=	error("Numerele a si b trebuie sa fie mai mari ca 1!")
			| a > b 	=	cmmdc (a-b) b
			| a < b 	=	cmmdc a (b-a)
			| otherwise	=	a

--6. Quicksort.
getLeft::Int->[Int]->[Int]
getLeft x [] = []
getLeft x (nr:numere)
					| x > nr 		=	nr : getLeft x numere
					| otherwise 	=	getLeft x numere	
getRight::Int->[Int]->[Int]
getRight x [] = []
getRight x (nr:numere)
					| x <= nr 		=	nr : getRight x numere
					| otherwise 	=	getRight x numere	

quicksort::[Int]->[Int]
quicksort [] = []
quicksort (x:numere) = (quicksort (getLeft x numere)) ++ (x : (quicksort (getRight x numere)))

--7. Se da o lista de numere. Creati(afisati) un arbore binar de cautare.
data Arbore a = Nil | Leaf a | Branch a (Arbore a) (Arbore a)
insert::Int->(Arbore Int)->(Arbore Int)
insert x (Nil) = Leaf x
insert x (Leaf node)
						| x < node	=	Branch node (Leaf x) Nil
						| x > node	=	Branch node Nil (Leaf x)
insert x tree@(Branch node left right)
									| x < node 	=	Branch (node) (insert x left) (right)
									| x > node 	=	Branch (node) (left) (insert x right)
									| otherwise =	tree
insertAll'::[Int]->(Arbore Int)->(Arbore Int)
insertAll' [] tree@(Branch _ left right) = tree
insertAll' (x:xs) (Nil) = insertAll' xs (insert x (Nil))
insertAll' (x:xs) (Leaf node) = insertAll' xs (insert x (Leaf node))
insertAll' (x:xs) tree@(Branch node left right) = insertAll' xs (insert x tree)

insertAll::[Int]->(Arbore Int)
insertAll (x:xs) = insertAll' (x:xs) (Nil)



--8. Se da o lista de numere. Afisati arborele binar de cautare in preordine.
showArbore'::(Arbore Int)->[Int]
showArbore' (Nil) = []
showArbore' (Leaf node) = [node]
showArbore' (Branch node left right) = (showArbore' left) ++ [node] ++ (showArbore' right)

showArbore::[Int]->[Int]
showArbore (x:xs) = showArbore' (insertAll (x:xs)) 

--9. Se da o lista de numere si un numar x din lista. Afisati subarborele elementului x.
showSubarbore'::Int->(Arbore Int)->(Arbore Int)
showSubarbore' x (Nil) = (Nil)
showSubarbore' x (Leaf node) 
								| x == node 	=	(Leaf node)
								|otherwise		=	(Nil)
showSubarbore' x (Branch node left right)
											| x > node 	=	showSubarbore' x right
											| x < node 	=	showSubarbore' x left
											| otherwise =	(Branch node left right)

showSubarbore::[Int]->Int->[Int]
showSubarbore (x:xs) a = showArbore' (showSubarbore' a (insertAll (x:xs)))


--10. Se da o lista de numere. Calculati adancimea arborelui binar de cautare.
adancimeArbore'::Int->(Arbore Int)->Int
adancimeArbore' x (Nil) = x
adancimeArbore' x (Leaf node) = x+1
adancimeArbore' x (Branch node left right)
											| adancimeArbore' (x+1) left < adancimeArbore' (x+1) right 	=	adancimeArbore' (x+1) right 
											| otherwise 												= 	adancimeArbore' (x+1) left  


adancimeArbore::[Int]->Int
adancimeArbore (x:xs) = adancimeArbore' (insertAll (x:xs))



--11. 