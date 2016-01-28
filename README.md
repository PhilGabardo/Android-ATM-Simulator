# Android ATM Simulator

This is a project for SFWR ENG 4HC3: Human Computer Interfaces. 

For this project, our group designed and implemented a functional UI for a bank machine. 

Bank machines have been discussed a few times in this course already. In general, the UIs for these are fairly simple, as users are expected to be able to “walk up and use”
with very minimal training. But, there are sometimes problems: consider bank machines that accept the card, 
process a withdrawal, then release the card. In these cases, it’s more likely that a user will forget their card 
as completing the transaction (getting the cash) likely “completes” the interaction. In contrast, a system that simply 
swipes the card will be less error-prone (users will likely not forget their card).

Our application supports the following features:
-	Ability to enter an account number, simulating swiping/inserting a card
-	Ability to withdraw and deposit cash, updating a bank account balance accordingly
-	Ability to simply check/display account balances
-	Transfers between accounts
- Viewing transaction history
