//------------------------------------------------------------------------------
//
//  Iptsrae Laser Beam Tutorial
//
//  Copyright (C) 1998 Steve Baker
//
// Author: Steve Baker <ssbaker@primenet.com>
// Maintainer: Steve Baker <ssbaker@primenet.com>
// Created:  14 Nov 1997
// Version: 1.0
// Keywords: iptscrae, tutorial, drawing, laserbeam
// Date: 16 Nov 1997
//
// $Id: LasPanel.java,v 1.2 1998/02/09 00:00:52 ssbaker Exp $
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

import java.awt.*;

//------------------------------------------------------------------------------
//  class LasPanel
//
//  This is actually where most of the work is done
//------------------------------------------------------------------------------
class LasPanel extends Panel
{
  
  //----------------------------------------------------------------------------
  // Private variables
  //----------------------------------------------------------------------------
  private int m_nPage, m_nMaxPage;      // Current and Max page number
  private Image m_imgBackground;        // The background image
  private Image m_imgBuffer;            // The image buffer
  private Graphics m_gBuffer;           // Graphics context for the buffer
  private TextArea m_txtOutput;         // The text output area

  // Standard Fonts
  private Font m_fntLabel;	        // The label font
  private int m_nfntLabel;	        // Height of the label font
  private Font m_fntTitle;	        // The title font
  private int m_nfntTitle;	        // Height of the title font

  // Variables to hold temp data for drawing
  private String m_strMsg;              // Temp string for calculating message widths
  private int m_nMsg;			// Width of message

  //----------------------------------------------------------------------------
  // Constructor
  //
  // txtOutput       - TextArea object on which to print instructions
  //----------------------------------------------------------------------------
  public LasPanel(TextArea textarea)
    {
      m_nMaxPage = 7;
      m_txtOutput = textarea;
      m_fntLabel = new Font("Helvetica", 0, 12);
      m_nfntLabel = m_fntLabel.getSize();
      m_fntTitle = new Font("Helvetica", 1, 14);
      m_nfntTitle = m_fntTitle.getSize();
    }
  
  //----------------------------------------------------------------------------
  // Reset the tutorial to the first page
  //----------------------------------------------------------------------------
  public void resetPage()
    {
      m_nPage = 1;
      paintPage(m_nPage);
    }
  
  //----------------------------------------------------------------------------
  // Progress to the next page of the tutorial
  //----------------------------------------------------------------------------
  public void nextPage()
    {
      if(m_nPage < m_nMaxPage)
	paintPage(++m_nPage);
      else 
	resetPage();		// If on last page, next does a reset
    }
  
  //----------------------------------------------------------------------------
  // Update the screen
  //----------------------------------------------------------------------------
  public void update(Graphics g)
    {
      paint(g);
    }
  
  //----------------------------------------------------------------------------
  // Paint the screen
  //----------------------------------------------------------------------------
  public void paint(Graphics g)
    {
      //--------------------------------------------------------------------------
      // If we get this far but have no buffer yet, make one!
      //--------------------------------------------------------------------------
      if(m_imgBuffer == null)
        {
	  Dimension d = size();
	  m_imgBuffer = createImage(d.width, d.height);
	  m_gBuffer = m_imgBuffer.getGraphics();
	  resetPage();
        }

      //--------------------------------------------------------------------------
      // Write the display buffer out to the real display
      //--------------------------------------------------------------------------
      g.drawImage(m_imgBuffer, 0, 0, this);
    }
  
  //----------------------------------------------------------------------------
  // Paint the requested page
  //----------------------------------------------------------------------------
  public void paintPage(int nPage)
    {
      Dimension d = size();
      switch(nPage)
        {
        case 1: /* '\001' */
	  paintPage1(d);
	  return;
	  
        case 2: /* '\002' */
	  paintPage2(d);
	  return;
	  
        case 3: /* '\003' */
	  paintPage3(d);
	  return;
	  
        case 4: /* '\004' */
	  paintPage4(d);
	  return;
	  
        case 5: /* '\005' */
	  paintPage5(d);
	  return;
	  
        case 6: /* '\006' */
	  paintPage6(d);
	  return;
	  
        case 7: /* '\007' */
	  paintPage7(d);
	  return;
	  
        }
    }
  
  //----------------------------------------------------------------------------
  // Draw a filled square
  //----------------------------------------------------------------------------
  public void drawSquare(int x, int y, int s)
    {
      for(int i = 0; i < s; i++)
	m_gBuffer.drawLine(x, y + i, x + (s - 1), y + i);
      
    }
  
  //----------------------------------------------------------------------------
  // Draw a palace-style laser line
  //----------------------------------------------------------------------------
  public void drawLaser(int x1, int y1, int x2, int y2)
    {
      int dx, dy;		// delta-x and delta-y

      // Draw the red outer layer
      m_gBuffer.setColor(Color.red);
      for(dx = 0; dx <= 4; dx++)
        {
	  for(dy = 0; dy <= 4; dy++)
	    m_gBuffer.drawLine(x1 + dx, y1 + dy, x2 + dx, y2 + dy);
	  
        }
      
      // Draw the orange middle layer
      m_gBuffer.setColor(Color.orange);
      for(dx = 1; dx <= 3; dx++)
        {
	  for(dy = 1; dy <= 3; dy++)
	    m_gBuffer.drawLine(x1 + dx, y1 + dy, x2 + dx, y2 + dy);
	  
        }
      
      // Draw the hot yellow interior layer
      m_gBuffer.setColor(Color.yellow);
      m_gBuffer.drawLine(x1 + 2, y1 + 2, x2 + 2, y2 + 2);
    }
  
  //----------------------------------------------------------------------------
  // Reset the screen to a predetermined condition
  //----------------------------------------------------------------------------
  public void clearScreen(Dimension d)
    {
      m_gBuffer.setColor(Color.lightGray);
      m_gBuffer.fillRect(0, 0, d.width, d.height);
    }
  
  //----------------------------------------------------------------------------
  // These methods do all the work; there should be one for each page
  //
  // Remember to write only to the m_gBuffer, never directly to the screen
  //----------------------------------------------------------------------------
  public void paintPage1(Dimension d)
    {
      clearScreen(d);
      m_gBuffer.setFont(m_fntTitle);
      m_strMsg = new String("The Laserbeam Tutorial");
      m_gBuffer.setColor(Color.black);
      m_gBuffer.drawString(m_strMsg, 30, 40);
      m_gBuffer.setColor(Color.red);
      m_gBuffer.drawString(m_strMsg, 29, 39);
      drawLaser(29, 45, d.width - 6, 45);
      m_gBuffer.setFont(m_fntLabel);
      m_gBuffer.setColor(Color.blue);
      int Y0 = 70;
      m_strMsg = new String("A 'laserbeam' is a wide line drawn across the screen that is red on the outside,");
      m_nMsg = getFontMetrics(m_fntLabel).stringWidth(m_strMsg);
      m_gBuffer.drawString(m_strMsg, d.width / 2 - m_nMsg / 2, Y0);
      Y0 += m_nfntLabel + 3;
      m_strMsg = new String("orange further in, and yellow in the center. It is used in Palace for a laser");
      m_nMsg = getFontMetrics(m_fntLabel).stringWidth(m_strMsg);
      m_gBuffer.drawString(m_strMsg, d.width / 2 - m_nMsg / 2, Y0);
      Y0 += m_nfntLabel + 3;
      m_strMsg = new String("effect. It is also used in Iptscrae classes as a graphics programming example");
      m_nMsg = getFontMetrics(m_fntLabel).stringWidth(m_strMsg);
      m_gBuffer.drawString(m_strMsg, d.width / 2 - m_nMsg / 2, Y0);
      Y0 += m_nfntLabel + 3;
      m_strMsg = new String("since to understand how to draw the laserbeam requires understanding of the");
      m_nMsg = getFontMetrics(m_fntLabel).stringWidth(m_strMsg);
      m_gBuffer.drawString(m_strMsg, d.width / 2 - m_nMsg / 2, Y0);
      Y0 += m_nfntLabel + 3;
      m_strMsg = new String("key concepts in line drawing. This tutorial is intended as a supplement to");
      m_nMsg = getFontMetrics(m_fntLabel).stringWidth(m_strMsg);
      m_gBuffer.drawString(m_strMsg, d.width / 2 - m_nMsg / 2, Y0);
      Y0 += m_nfntLabel + 3;
      m_strMsg = new String("a formal Iptscrae class or a self-study program.");
      m_nMsg = getFontMetrics(m_fntLabel).stringWidth(m_strMsg);
      m_gBuffer.drawString(m_strMsg, d.width / 2 - m_nMsg / 2, Y0);
      Y0 += m_nfntLabel + 3;
      m_txtOutput.setText("Welcome to the Iptscrae Laserbeam Tutorial, written by Tandika Star\n");
      m_txtOutput.appendText("\n");
      m_txtOutput.appendText("Press the 'Next' button to proceed, or 'Reset' to start over");
      repaint();
    }
  
  //----------------------------------------------------------------------------
  public void paintPage2(Dimension d)
    {
      byte dx = 100;
      byte dy = 50;
      clearScreen(d);
      m_gBuffer.setColor(Color.blue);
      m_gBuffer.drawLine(dx, 0, dx, d.height);
      m_gBuffer.drawLine(dx, 0, dx - 5, 10);
      m_gBuffer.drawLine(dx, 0, dx + 5, 10);
      m_gBuffer.drawLine(dx, d.height, dx - 5, d.height - 10);
      m_gBuffer.drawLine(dx, d.height, dx + 5, d.height - 10);
      m_gBuffer.drawLine(0, dy, d.width, dy);
      m_gBuffer.drawLine(0, dy, 10, dy + 5);
      m_gBuffer.drawLine(0, dy, 10, dy - 5);
      m_gBuffer.drawLine(d.width, dy, d.width - 10, dy + 5);
      m_gBuffer.drawLine(d.width, dy, d.width - 10, dy - 5);
      m_gBuffer.setFont(m_fntLabel);
      m_strMsg = new String("512 Pixels");
      m_nMsg = getFontMetrics(m_fntLabel).stringWidth(m_strMsg);
      m_gBuffer.drawString(m_strMsg, d.width / 2 - m_nMsg / 2, dy - 5);
      m_strMsg = new String("384 Pixels");
      m_nMsg = getFontMetrics(m_fntLabel).stringWidth(m_strMsg);
      m_gBuffer.drawString(m_strMsg, dx + 5, d.height / 2);
      m_gBuffer.setFont(m_fntTitle);
      m_gBuffer.setColor(Color.black);
      m_strMsg = new String("So how big is my");
      m_nMsg = getFontMetrics(m_fntTitle).stringWidth(m_strMsg);
      m_gBuffer.drawString(m_strMsg, (dx + (d.width - dx) / 2) - m_nMsg / 2, (d.height - dy) / 2 + (dy - 3));
      m_strMsg = new String("screen anyway?");
      m_nMsg = getFontMetrics(m_fntTitle).stringWidth(m_strMsg);
      m_gBuffer.drawString(m_strMsg, (dx + (d.width - dx) / 2) - m_nMsg / 2, (d.height - dy) / 2 + dy + m_nfntTitle + 3);
      m_gBuffer.setFont(m_fntLabel);
      m_gBuffer.setColor(Color.blue);
      m_gBuffer.drawString("(0,0)", 5, m_nfntLabel + 5);
      m_gBuffer.drawString("(0,383)", 5, d.height - 5);
      m_strMsg = new String("(511,0)");
      m_nMsg = getFontMetrics(m_fntLabel).stringWidth(m_strMsg);
      m_gBuffer.drawString(m_strMsg, d.width - (m_nMsg + 5), m_nfntLabel + 5);
      m_strMsg = new String("(511,383)");
      m_nMsg = getFontMetrics(m_fntLabel).stringWidth(m_strMsg);
      m_gBuffer.drawString(m_strMsg, d.width - (m_nMsg + 5), d.height - 5);
      m_txtOutput.setText("Your screen is 512 pixels wide and 384 pixels tall, addressed as\n");
      m_txtOutput.appendText("0 - 511 and 0 - 383, with the origin (0,0) in the upper left.\n");
      m_txtOutput.appendText("It is very important not to use coordinates outside of this range\n");
      m_txtOutput.appendText("Since drawing off the screen will provide you with some odd effects!");
      repaint();
    }
  
  //----------------------------------------------------------------------------
  public void paintPage3(Dimension d)
    {
      clearScreen(d);
      m_txtOutput.setText("So how do we make a laserbeam out of this?\n");
      m_txtOutput.appendText("\n");
      m_txtOutput.appendText("Press the 'Next' button to proceed, or 'Reset' to start over");
      m_gBuffer.setFont(m_fntLabel);
      m_gBuffer.setColor(Color.blue);
      int Y0 = 15;
      int X0 = 10;
      m_strMsg = new String("The key to understanding how to draw a laserbeam is in understanding that lines");
      m_gBuffer.drawString(m_strMsg, X0, Y0);
      Y0 += m_nfntLabel + 3;
      m_strMsg = new String("drawn in Iptscrae are drawn using a square brush of PENSIZE pixels. The starting");
      m_gBuffer.drawString(m_strMsg, X0, Y0);
      Y0 += m_nfntLabel + 3;
      m_strMsg = new String("and ending points that you pass to the LINE command are actually the coordinates");
      m_gBuffer.drawString(m_strMsg, X0, Y0);
      Y0 += m_nfntLabel + 3;
      m_strMsg = new String("of the upper left corner of the square. So if you have a PENSIZE of 5 and you draw");
      m_gBuffer.drawString(m_strMsg, X0, Y0);
      Y0 += m_nfntLabel + 3;
      m_strMsg = new String("a line from (10,10) to (20,20), you are really drawing a square from X = 10 to 14");
      m_gBuffer.drawString(m_strMsg, X0, Y0);
      Y0 += m_nfntLabel + 3;
      m_strMsg = new String("and Y = 10 to 14 to a similar square at X = 20 to 24 and Y = 20 to 24.");
      m_gBuffer.drawString(m_strMsg, X0, Y0);
      Y0 = 120;
      m_gBuffer.setColor(Color.blue);
      m_strMsg = new String("This Iptscrae code:");
      m_gBuffer.drawString(m_strMsg, X0, Y0);
      Y0 += 2 * (m_nfntLabel + 3);
      m_gBuffer.setColor(Color.black);
      m_strMsg = new String("5 PENSIZE");
      m_gBuffer.drawString(m_strMsg, X0, Y0);
      Y0 += m_nfntLabel + 3;
      m_strMsg = new String("10 10 20 20 LINE");
      m_gBuffer.drawString(m_strMsg, X0, Y0);
      Y0 = 120;
      X0 = 200;
      m_gBuffer.setColor(Color.blue);
      m_strMsg = new String("Produces this output:");
      m_gBuffer.drawString(m_strMsg, X0, Y0);
      Y0 = 140;
      X0 = 300;
      m_gBuffer.setColor(Color.black);
      for(int l = 0; l <= 10; l++)
        {
	  int i1 = X0 + l * 5;
	  int j1 = Y0 + l * 5;
	  for(int j = 0; j <= 4; j++)
            {
	      for(int k = 0; k <= 4; k++)
		drawSquare(i1 + j * 5, j1 + k * 5, 4);
	      
            }
	  
        }
      
      m_gBuffer.setColor(Color.blue);
      m_strMsg = new String("(10,10)");
      m_nMsg = getFontMetrics(m_fntLabel).stringWidth(m_strMsg);
      m_gBuffer.drawString(m_strMsg, X0 - 80, Y0 + m_nfntLabel / 2);
      m_strMsg = new String("(20,20)");
      m_gBuffer.drawString(m_strMsg, X0 + 123, Y0 + 50 + m_nfntLabel / 2);
      m_strMsg = new String("(24,24)");
      m_gBuffer.drawString(m_strMsg, X0 + 123, Y0 + 70 + m_nfntLabel / 2);
      m_gBuffer.setColor(Color.green);
      m_gBuffer.drawLine((X0 - 80) + m_nMsg + 3, Y0 + 2, X0 - 3, Y0 + 2);
      m_gBuffer.drawLine(X0 - 7, Y0, X0 - 3, Y0 + 2);
      m_gBuffer.drawLine(X0 - 7, Y0 + 4, X0 - 3, Y0 + 2);
      m_gBuffer.drawLine(X0 + 53, Y0 + 52, X0 + 120, Y0 + 52);
      m_gBuffer.drawLine(X0 + 53, Y0 + 52, X0 + 57, Y0 + 50);
      m_gBuffer.drawLine(X0 + 53, Y0 + 52, X0 + 57, Y0 + 54);
      m_gBuffer.drawLine(X0 + 77, Y0 + 72, X0 + 120, Y0 + 72);
      m_gBuffer.drawLine(X0 + 77, Y0 + 72, X0 + 81, Y0 + 70);
      m_gBuffer.drawLine(X0 + 77, Y0 + 72, X0 + 81, Y0 + 74);
      repaint();
    }
  
  //----------------------------------------------------------------------------
  public void paintPage4(Dimension d)
    {
      clearScreen(d);
      m_txtOutput.setText("The next screen adds the orange intermediate color to the red background\n");
      m_txtOutput.appendText("\n");
      m_txtOutput.appendText("Press the 'Next' button to proceed, or 'Reset' to start over");
      m_gBuffer.setFont(m_fntLabel);
      m_gBuffer.setColor(Color.blue);
      int Y0 = 15;
      int X0 = 10;
      m_strMsg = new String("Now that we know that lines are drawn with square brushes, we have a clue as to");
      m_gBuffer.drawString(m_strMsg, X0, Y0);
      Y0 += m_nfntLabel + 3;
      m_strMsg = new String("how to draw a laserbeam: All we have to do is draw three lines of varying colors");
      m_gBuffer.drawString(m_strMsg, X0, Y0);
      Y0 += m_nfntLabel + 3;
      m_strMsg = new String("using smaller brushes each time, making sure that we center each smaller brush on");
      m_gBuffer.drawString(m_strMsg, X0, Y0);
      Y0 += m_nfntLabel + 3;
      m_strMsg = new String("the preceding larger brush. If we use PENSIZEs of 5, 3 and 1 and add 1 pixel to the");
      m_gBuffer.drawString(m_strMsg, X0, Y0);
      Y0 += m_nfntLabel + 3;
      m_strMsg = new String("X and Y coordinates of the beginning and the end of the line, it will work just right.");
      m_gBuffer.drawString(m_strMsg, X0, Y0);
      Y0 += 2 * (m_nfntLabel + 3);
      m_gBuffer.setColor(Color.red);
      m_strMsg = new String("Confused???");
      m_nMsg = getFontMetrics(m_fntLabel).stringWidth(m_strMsg);
      m_gBuffer.drawString(m_strMsg, X0, Y0);
      m_gBuffer.setColor(Color.blue);
      m_strMsg = new String("Don't be. It's not hard -- just look at this:");
      m_gBuffer.drawString(m_strMsg, X0 + m_nMsg + 4, Y0);
      Y0 += m_nfntLabel + 3;
      Y0 = 140;
      m_gBuffer.setColor(Color.blue);
      m_strMsg = new String("This Iptscrae code:");
      m_gBuffer.drawString(m_strMsg, X0, Y0);
      Y0 += 2 * (m_nfntLabel + 3);
      m_gBuffer.setColor(Color.black);
      m_strMsg = new String("5 PENSIZE");
      m_gBuffer.drawString(m_strMsg, X0, Y0);
      Y0 += m_nfntLabel + 3;
      m_strMsg = new String("255 0 0 PENCOLOR");
      m_gBuffer.drawString(m_strMsg, X0, Y0);
      Y0 += m_nfntLabel + 3;
      m_strMsg = new String("10 10 20 20 LINE");
      m_gBuffer.drawString(m_strMsg, X0, Y0);
      Y0 = 140;
      X0 = 200;
      m_gBuffer.setColor(Color.blue);
      m_strMsg = new String("Produces this output:");
      m_gBuffer.drawString(m_strMsg, X0, Y0);
      Y0 = 160;
      X0 = 300;
      m_gBuffer.setColor(Color.red);
      for(int nLen = 0; nLen <= 10; nLen++)
        {
	  int X1 = X0 + nLen * 5;
	  int Y1 = Y0 + nLen * 5;
	  for(int x = 0; x <= 4; x++)
            {
	      for(int y = 0; y <= 4; y++)
		drawSquare(X1 + x * 5, Y1 + y * 5, 4);
            }
        }
      
      m_gBuffer.setColor(Color.blue);
      m_strMsg = new String("(10,10)");
      m_nMsg = getFontMetrics(m_fntLabel).stringWidth(m_strMsg);
      m_gBuffer.drawString(m_strMsg, X0 - 80, Y0 + m_nfntLabel / 2);
      m_strMsg = new String("(24,24)");
      m_gBuffer.drawString(m_strMsg, X0 + 123, Y0 + 70 + m_nfntLabel / 2);
      m_gBuffer.drawLine((X0 - 80) + m_nMsg + 3, Y0 + 2, X0 - 3, Y0 + 2);
      m_gBuffer.drawLine(X0 - 7, Y0, X0 - 3, Y0 + 2);
      m_gBuffer.drawLine(X0 - 7, Y0 + 4, X0 - 3, Y0 + 2);
      m_gBuffer.drawLine(X0 + 77, Y0 + 72, X0 + 120, Y0 + 72);
      m_gBuffer.drawLine(X0 + 77, Y0 + 72, X0 + 81, Y0 + 70);
      m_gBuffer.drawLine(X0 + 77, Y0 + 72, X0 + 81, Y0 + 74);
      repaint();
    }
  
  //----------------------------------------------------------------------------
  public void paintPage5(Dimension d)
    {
      m_txtOutput.setText("The next screen adds the yellow center color to the red and orange background\n");
      m_txtOutput.appendText("\n");
      m_txtOutput.appendText("Press the 'Next' button to proceed, or 'Reset' to start over");
      int Y0 = 140;
      int X0 = 10;
      m_gBuffer.setColor(Color.blue);
      Y0 += 5 * (m_nfntLabel + 3);
      m_gBuffer.setColor(Color.black);
      m_strMsg = new String("3 PENSIZE");
      m_gBuffer.drawString(m_strMsg, X0, Y0);
      Y0 += m_nfntLabel + 3;
      m_strMsg = new String("255 127 0 PENCOLOR");
      m_gBuffer.drawString(m_strMsg, X0, Y0);
      Y0 += m_nfntLabel + 3;
      m_strMsg = new String("11 11 21 21 LINE");
      m_gBuffer.drawString(m_strMsg, X0, Y0);
      Y0 = 160;
      X0 = 300;
      m_gBuffer.setColor(Color.orange);
      for(int nLen = 0; nLen <= 10; nLen++)
        {
	  int X1 = X0 + nLen * 5;
	  int Y1 = Y0 + nLen * 5;
	  for(int x = 1; x <= 3; x++)
            {
	      for(int y = 1; y <= 3; y++)
		drawSquare(X1 + x * 5, Y1 + y * 5, 4);
            }
        }
      
      repaint();
    }
  
  //----------------------------------------------------------------------------
  public void paintPage6(Dimension d)
    {
      m_txtOutput.setText("That's it! You've got a laserbeam! Pretty cool, huh?\n");
      m_txtOutput.appendText("\n");
      m_txtOutput.appendText("Press the 'Next' button to proceed, or 'Reset' to start over");
      int Y0 = 140;
      int X0 = 10;
      m_gBuffer.setColor(Color.blue);
      Y0 += 8 * (m_nfntLabel + 3);
      m_gBuffer.setColor(Color.black);
      m_strMsg = new String("1 PENSIZE");
      m_gBuffer.drawString(m_strMsg, X0, Y0);
      Y0 += m_nfntLabel + 3;
      m_strMsg = new String("255 255 0 PENCOLOR");
      m_gBuffer.drawString(m_strMsg, X0, Y0);
      Y0 += m_nfntLabel + 3;
      m_strMsg = new String("12 12 22 22 LINE");
      m_gBuffer.drawString(m_strMsg, X0, Y0);
      Y0 = 160;
      X0 = 300;
      m_gBuffer.setColor(Color.yellow);
      for(int nLen = 0; nLen <= 10; nLen++)
        {
	  int X1 = X0 + nLen * 5;
	  int Y1 = Y0 + nLen * 5;
	  drawSquare(X1 + 10, Y1 + 10, 4);
        }
      
      repaint();
    }
  
  //----------------------------------------------------------------------------
  public void paintPage7(Dimension d)
    {
      clearScreen(d);
      m_gBuffer.setFont(m_fntTitle);
      m_strMsg = new String("Now that wasn't so bad, was it?");
      m_gBuffer.setColor(Color.red);
      m_nMsg = getFontMetrics(m_fntTitle).stringWidth(m_strMsg);
      m_gBuffer.drawString(m_strMsg, d.width / 2 - m_nMsg / 2, 80);
      drawLaser(0, 0, d.width / 2, d.height);
      drawLaser(d.width, 0, d.width / 2, d.height);
      drawLaser(d.width / 2, 83 + m_nfntTitle, d.width / 2, d.height);
      m_txtOutput.setText("Have a great day!\n");
      m_txtOutput.appendText("\n");
      m_txtOutput.appendText("Press 'Reset' to start over");
      repaint();
    }
}
