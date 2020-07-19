# Coffee-Machine
A coffee machine which can prepare beverages in parallel

### How to Run
- Clone the repo and navigate to the pom.xml location **($ cd coffee-machine)**
- run **$ mvn clean install**
- Run Main.java
- Incase you want to test for different input files, change in Main.java

### Test files
- input.json : number of outlet is greater than number of beverages than can be prepared in parallel with available ingradients.
- input1.json : number of outlet is less than number of beverages than can be prepared in parallel with available ingradients.
- input2.json : capacity of the machine is very less, so no beverage can be prepared.

### Assumptions
- At any instant, one kind of beverage can be prepared by maximum one thread, i.e two parallel threads cannot prepare black_tea.
- Have unlimited external supply of ingradients, so refill can be done at any time with any quantity of the ingradient.
