Checkmate3000NetworkAIEdition
=============================

Insert chess pun here

####ToDo:
	Fix pawn attack bug. Capturing with the other pieces is completely broken as well.
	Make other menus call gamebuilder like localview does
	Add Pawn promotion.
	Add timer responsibilities.
	Implement Game over View
	Finish up with Network Player class
	and start AI Player Classes
	GameView draw timers.
	Remove RadioButtons from LocalView (I disagree, they're still being used for AI games - we may want to make them inaccessible if there are two or zero human players).
	Menus look nice.
	GameView.resignButton causes you to lose the game.
	Check if piece can move before selecting it. Save list of possible moves when checking so it doesn't need to check again to draw possible tiles?

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
	