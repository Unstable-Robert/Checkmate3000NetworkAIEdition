Checkmate3000NetworkAIEdition
=============================

Insert chess pun here

####ToDo:
	Fix pawn attack bug.
	Make other menus call gamebuilder like localview does
	Add Pawn promotion.
	Add timer responsibilities.
	Implement Game over View
	Finish up with Network Player class
	and start AI Player Classes
<<<<<<< HEAD
=======
	Menus look nice.
	Add timers, resign button to GameView.
	Remove RadioButtons from LocalView (I disagree, they're still being used for AI games - we may want to make them inaccessible if there are two or zero human players).
>>>>>>> e45e33529642f9d030accd2346c93dcda8cab1bb
	Clicking anywhere but a possible move space will deselect a piece.
	Remove RadioButtons from LocalView.
	Add timers, resign button to GameView.
	Menus look nice.
	

####Changelog:
	Implemented piece lazy tiles methods.
	Added code for Marshaler and Networked Player
	Implemented GameModel
	Implemented GameBuilder
	Removed DrawView, GameOverView, and PawnPromotionView. Use JOptionPane.showMessageDialog() instead.
	Made SettingViews extend JPanel instead of JFrame.
	Created Checkmate.java as the main entry point and to have a main JFrame.
	Changed MenuView to MainMenuView
	Checkmate class is saved in SettingsView so the View can be changed within each SettingsView.
	Removed Color enumeration.
