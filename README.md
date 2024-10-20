# Kamisado
A program a Kamisado nevű játék egy egyszerűsített formáját valósítja meg.

## Szabályok
A játékot a felhasználó egy robottal játssza.

A cél, hogy a játékos a saját bábui közül egyet átjuttasson a tábla szemközti szélén lévő mezők egyikére.

A felhasználó kezdi a meccset, első körben bármelyik bábujával léphet, melyek a tábla alsó részét sorakoznak. A lépés csakis előre irányba megengedett, egyenesen vagy átlóban. Miután ezt megtette, a "Léptem!" gombra kattintással kell jeleznie.

A következő lépést az ellenfél olyan színű bábujával kell, hogy tegye, amilyen színű mezőre előtte a másik játékos lépett. A felhasználó "Léptem!" kattintása után a robot egyből megteszi a saját lépését, amit követően a fejlécen kiírásra kerül segítségként, hogy a felhasználónak melyik bábuval kell lépnie.

A felhasználónak lehetősége van elmentenie a játék állását az erre megfelelő gombbal. A program indulásakor "A játék folytatása" menüpontot választva a legutoljára elmentett játékállás jelenik meg a táblán, és mindig a felhasználó következik a lépéssel.

A játék során a robot véletlenszerűen választ a megengedett mezők közül, amire lép.


## A repo tartalma
A repository tartalmazza a source file-okat, illetve JUnit teszteket is a programhoz, mely a házi feladat követelménye volt.
