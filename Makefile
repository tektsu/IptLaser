#
#	Makefile for IptLaser
#
#	$Id: Makefile,v 1.1 1998/02/09 00:00:53 ssbaker Exp $
#

# Program Name
PROG = IptLaser

# Compiler
JVC = javac

# Source Files
SRC = IptLaser.java LasPanel.java
CLS = $(SRC:.java=.class)

%.class: %.java
	$(JVC) $<

all: $(CLS)

# Phony dependencies
.PHONY : clean TAGS bk2 tar
clean : 
	-rm -f $(CLS) *~ 
