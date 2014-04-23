Checkmate3000NetworkAIEdition
=============================

Insert chess pun here

####ToDo:
	Add AI possibilities to localview
	Make other menus call gamebuilder like localview does
	Fix castling bug
	Add timer responsibilities
	Implement Game over View
	Finish up with Network Player class
	and start AI Player Classes
	En passant should only work the turn after that pawn moved.
	Menus look nice.
	Add timers, resign button to GameView.
	Remove RadioButtons from LocalView.
	Clicking anywhere but a possible move space will deselect a piece.

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
