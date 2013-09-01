//------------------------------------------------------------------------------
//
//  Iptsrae Laser Beam Tutorial
//
//  Copyright (C) 1997 Steve Baker
//
// Author: Steve Baker <ssbaker@primenet.com>
// Maintainer: Steve Baker <ssbaker@primenet.com>
// Created:  14 Nov 1997
// Version: 1.0
// Keywords: iptscrae, tutorial, drawing, laserbeam
//
// $Id: IptLaser.java,v 1.3 1998/02/08 06:59:57 ssbaker Exp $
//
// This program is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation; either version 2, or (at your option)
// any later version.
//
// This program is distributed in the hope that it will be useful, but
// WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; see the file COPYING.  If not, write to the
// Free Software Foundation, Inc., 59 Temple Place - Suite 330,
// Boston, MA 02111-1307, USA.
//  
//------------------------------------------------------------------------------
//
//  This program may be modified to display other tutorials as follows:
//  1. Change the name of the applet class from IptLaser to something else
//  2. Change the name of the output panel from LasPanel to something else
//     (make sure to change the reference to this name in IptLaser.init()
//      and the name of its constructor as well)
//  3. Change the value of LasPanel.m_nMaxPage to the number of pages in
//     the new tutorial
//  4. Ensure the switch statement in LasPanel.paintPage()  calls a separate 
//     function for each page in the tutorial (add or delete cases as needed)
//  5. Write a LasPanel.paintPage#() function to display each page
//
//------------------------------------------------------------------------------
import java.applet.Applet;
import java.awt.*;

//------------------------------------------------------------------------------
//  Applet IptLaser
//
//  Tutorial for IPTSCRAE: Drawing Laser Beams
//------------------------------------------------------------------------------
public class IptLaser extends Applet
{

  //----------------------------------------------------------------------------
  // Private variables
  //----------------------------------------------------------------------------
  private Button btnNext, btnReset;     // User control buttons
  private TextArea txtOutput;           // Output area for explanatory text
  private LasPanel pnlDraw;             // Output area for tutorial graphics
  private Panel pnlControls;            // Panel to hold controls
  
  //----------------------------------------------------------------------------
  // Initialization
  //----------------------------------------------------------------------------
  public void init()
    {
      //------------------------------------------------------------------------
      // Create the controls
      //------------------------------------------------------------------------
      btnNext = new Button("Next");
      btnReset = new Button("Reset");
      pnlControls = new Panel();
      txtOutput = new TextArea("\n", 4, 1);
      pnlDraw = new LasPanel(txtOutput);

      //------------------------------------------------------------------------
      // Build the user interface
      //------------------------------------------------------------------------
      pnlControls.setLayout(new BorderLayout());
      pnlControls.add("North", txtOutput);
      pnlControls.add("West", btnReset);
      pnlControls.add("East", btnNext);
      txtOutput.setEditable(false);
      setLayout(new BorderLayout());
      add("South", pnlControls);
      add("Center", pnlDraw);
    }
  
  //----------------------------------------------------------------------------
  // Act on the users' requests
  //----------------------------------------------------------------------------
  public boolean action(Event event, Object obj)
    {
      //------------------------------------------------------------------------
      // The "Next" button has been pressed, advance to the next page
      //------------------------------------------------------------------------
      if(event.target == btnNext)
        {
	  pnlDraw.nextPage();
	  return true;
        }
    
      //------------------------------------------------------------------------
      // The "Reset" button has been pressed, go to the first page
      //------------------------------------------------------------------------
      if(event.target == btnReset)
        {
	  pnlDraw.resetPage();
	  return true;
        }
    
      //------------------------------------------------------------------------
      // If anything else happens, don't mess with it
      //------------------------------------------------------------------------
      else
        {
	  return false;
        }
    }
}
