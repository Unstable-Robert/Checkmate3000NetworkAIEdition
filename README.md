Checkmate3000NetworkAIEdition
=============================

Insert chess pun here

####ToDo:
	add win, lose, draw enum for netgames
	Pressing canel when saving a log returns user to the game over option pane.
	game log save AI moves
	game log castling
	game log en passant
	game log pawn promotion
	game log display who won

####Changelog:
	Added three fold repetition.
	Game log working more or less.
	.cm3 log files are now formatted like this: "UID,X,Y,;nextUID,nextX,nextY,;"
	added a draw game option pane
	LogView added
	removed GameOverView and DrawView
	Ended timer ends game
	Networked code finished.
	Added Pawn promotion.
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
	
