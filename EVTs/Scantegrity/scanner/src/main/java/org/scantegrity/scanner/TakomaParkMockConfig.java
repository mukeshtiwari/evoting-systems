/*
 * @(#)CreateScannerConfig.java.java
 *  
 * Copyright (C) 2008-2009 Scantegrity Project
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.scantegrity.scanner;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.TreeMap;
import java.util.Vector;

import org.scantegrity.common.BallotStyle;
import org.scantegrity.common.Contest;
import org.scantegrity.common.Contestant;
import org.scantegrity.common.methods.InstantRunoffTally;
import org.scantegrity.common.methods.PluralityTally;
import org.scantegrity.scanner.CircleAlignmentMarkReader;
import org.scantegrity.scanner.QRCodeReader;
import org.scantegrity.scanner.ScannerConfig;
import org.scantegrity.scanner.ScantegrityBallotReader;

/**
 * Scanner config creator. Translated from Stefan's BallotGeom.xml file.
 * 
 * Unfortunately, don't have a translator module for that just yet.
 * 
 * @author Richard Carback
 *
 */
public class TakomaParkMockConfig
{
	private static String c_loc = "";
	private static String c_name = "ScannerConfig.xml";

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		ScannerConfig l_config = new ScannerConfig();
		ScantegrityBallotReader l_reader = new ScantegrityBallotReader();
		
		//Dimension l_d = new Dimension(2550, 4200); Difference: 840
		Dimension l_d = new Dimension(2550, 3360);
		Point[] l_marks = new Point[2];
							// 483, 1889: Differences- +77, -239
		l_marks[0] = new Point(575, 1650);
		l_marks[1] = new Point(575, 3490);
		QRCodeReader l_code = new QRCodeReader();
		l_code.setSerialBoundingBox(new Rectangle(1190, 3265, 500, 500));
		l_reader.setSerial(l_code);
		l_reader.setAlignment(l_marks);
		l_reader.setDimension(l_d);
		l_reader.setAlignmentMark(new CircleAlignmentMarkReader(80/2, .05));
		l_reader.setTolerance(.4);
		
		Vector<Integer> l_contests = new Vector<Integer>();
		l_contests.add(0);
		l_contests.add(1);

		TreeMap<Integer, Vector<Vector<Rectangle>>> l_rects = new TreeMap<Integer, Vector<Vector<Rectangle>>>();
		TreeMap<Integer, TreeMap<Integer, Rectangle>> l_writeInRects = new TreeMap<Integer, TreeMap<Integer,Rectangle>>();
		TreeMap<Integer, Rectangle> l_tempMap;
		
		//There are 6 Styles
		BallotStyle l_styles[] = new BallotStyle[6];

		//There are 7 Contests. 1 Mayors Race, which is guaranteed to be on every ballot in the same place.
		// and 6 Ward races, which share positions but are slightly different.
		
		//Contest 0 Locations - Mayor's Race
		l_rects.put(0, new Vector<Vector<Rectangle>>());
		l_rects.get(0).add(new Vector<Rectangle>());
		l_rects.get(0).elementAt(0).add(new Rectangle(1389, 2007, 160, 60));
		l_rects.get(0).elementAt(0).add(new Rectangle(1587, 2007, 160, 60));
		l_rects.get(0).elementAt(0).add(new Rectangle(1786, 2007, 160, 60));
		l_rects.get(0).add(new Vector<Rectangle>());
		l_rects.get(0).elementAt(1).add(new Rectangle(1389, 2157, 160, 60));
		l_rects.get(0).elementAt(1).add(new Rectangle(1587, 2157, 160, 60));
		l_rects.get(0).elementAt(1).add(new Rectangle(1786, 2157, 160, 60));
		l_rects.get(0).add(new Vector<Rectangle>());
		l_rects.get(0).elementAt(2).add(new Rectangle(1389, 2307, 160, 60));
		l_rects.get(0).elementAt(2).add(new Rectangle(1587, 2307, 160, 60));
		l_rects.get(0).elementAt(2).add(new Rectangle(1786, 2307, 160, 60));
		l_tempMap = new TreeMap<Integer, Rectangle>(); 
		l_tempMap.put(2, (new Rectangle(390, 2288, 920, 110)));
		l_writeInRects.put(0, l_tempMap);
				
		//Contest 1 Locations - Ward 1
		l_rects.put(1, new Vector<Vector<Rectangle>>());
		l_rects.get(1).add(new Vector<Rectangle>());
		l_rects.get(1).elementAt(0).add(new Rectangle(1389, 2804, 160, 60));
		l_rects.get(1).elementAt(0).add(new Rectangle(1587, 2804, 160, 60));
		l_rects.get(1).add(new Vector<Rectangle>());
		l_rects.get(1).elementAt(1).add(new Rectangle(1389, 2954, 160, 60));
		l_rects.get(1).elementAt(1).add(new Rectangle(1587, 2954, 160, 60));
		l_tempMap = new TreeMap<Integer, Rectangle>(); 
		l_tempMap.put(1, (new Rectangle(390, 2935, 920, 110)));
		l_writeInRects.put(1, l_tempMap);
		
		//Contest 2 Locations - Ward 2
		l_rects.put(2, new Vector<Vector<Rectangle>>());
		l_rects.get(2).add(new Vector<Rectangle>());
		l_rects.get(2).elementAt(0).add(new Rectangle(1389, 2804, 160, 60));
		l_rects.get(2).elementAt(0).add(new Rectangle(1587, 2804, 160, 60));
		l_rects.get(2).add(new Vector<Rectangle>());
		l_rects.get(2).elementAt(1).add(new Rectangle(1389, 2954, 160, 60));
		l_rects.get(2).elementAt(1).add(new Rectangle(1587, 2954, 160, 60));
		l_tempMap = new TreeMap<Integer, Rectangle>(); 
		l_tempMap.put(1, (new Rectangle(390, 2935, 920, 110)));
		l_writeInRects.put(2, l_tempMap);
		
		//Contest 3 Locations - Ward 3
		l_rects.put(3, new Vector<Vector<Rectangle>>());
		l_rects.get(3).add(new Vector<Rectangle>());
		l_rects.get(3).elementAt(0).add(new Rectangle(1389, 2804, 160, 60));
		l_rects.get(3).elementAt(0).add(new Rectangle(1587, 2804, 160, 60));
		l_rects.get(3).add(new Vector<Rectangle>());
		l_rects.get(3).elementAt(1).add(new Rectangle(1389, 2954, 160, 60));
		l_rects.get(3).elementAt(1).add(new Rectangle(1587, 2954, 160, 60));
		l_tempMap = new TreeMap<Integer, Rectangle>(); 
		l_tempMap.put(1, (new Rectangle(390, 2935, 920, 110)));
		l_writeInRects.put(3, l_tempMap);
		
		//Contest 4 Locations - Ward 4
		l_rects.put(4, new Vector<Vector<Rectangle>>());
		l_rects.get(4).add(new Vector<Rectangle>());
		l_rects.get(4).elementAt(0).add(new Rectangle(1389, 2804, 160, 60));
		l_rects.get(4).elementAt(0).add(new Rectangle(1587, 2804, 160, 60));
		l_rects.get(4).add(new Vector<Rectangle>());
		l_rects.get(4).elementAt(1).add(new Rectangle(1389, 2954, 160, 60));
		l_rects.get(4).elementAt(1).add(new Rectangle(1587, 2954, 160, 60));
		l_tempMap = new TreeMap<Integer, Rectangle>(); 
		l_tempMap.put(1, (new Rectangle(390, 2935, 920, 110)));
		l_writeInRects.put(4, l_tempMap);
		
		//Contest 5 Locations - Ward 5
		l_rects.put(5, new Vector<Vector<Rectangle>>());
		l_rects.get(5).add(new Vector<Rectangle>());
		l_rects.get(5).elementAt(0).add(new Rectangle(1389, 2804, 160, 60));
		l_rects.get(5).elementAt(0).add(new Rectangle(1587, 2804, 160, 60));
		l_rects.get(5).add(new Vector<Rectangle>());
		l_rects.get(5).elementAt(1).add(new Rectangle(1389, 2954, 160, 60));
		l_rects.get(5).elementAt(1).add(new Rectangle(1587, 2954, 160, 60));
		l_tempMap = new TreeMap<Integer, Rectangle>(); 
		l_tempMap.put(1, (new Rectangle(390, 2935, 920, 110)));
		l_writeInRects.put(5, l_tempMap);
		
		//Contest 6 Locations - Ward 6
		l_rects.put(6, new Vector<Vector<Rectangle>>());
		l_rects.get(6).add(new Vector<Rectangle>());
		l_rects.get(6).elementAt(0).add(new Rectangle(1389, 2804, 160, 60));
		l_rects.get(6).elementAt(0).add(new Rectangle(1587, 2804, 160, 60));
		l_rects.get(6).elementAt(0).add(new Rectangle(1786, 2804, 160, 60));
		l_rects.get(6).add(new Vector<Rectangle>());
		l_rects.get(6).elementAt(1).add(new Rectangle(1389, 2954, 160, 60));
		l_rects.get(6).elementAt(1).add(new Rectangle(1587, 2954, 160, 60));
		l_rects.get(6).elementAt(1).add(new Rectangle(1786, 2954, 160, 60));
		l_rects.get(6).add(new Vector<Rectangle>());
		l_rects.get(6).elementAt(2).add(new Rectangle(1389, 3104, 160, 60));
		l_rects.get(6).elementAt(2).add(new Rectangle(1587, 3104, 160, 60));
		l_rects.get(6).elementAt(2).add(new Rectangle(1786, 3104, 160, 60));
		l_tempMap = new TreeMap<Integer, Rectangle>(); 
		l_tempMap.put(2, (new Rectangle(390, 2935, 920, 110)));
		l_writeInRects.put(6, l_tempMap);
		
		
		Vector<Vector<Integer>> l_contestantIds = new Vector<Vector<Integer>>();
		
		//Contest 0 Locations - Mayor's Race
		l_contestantIds.add(new Vector<Integer>());
		l_contestantIds.get(0).add(0);
		l_contestantIds.get(0).add(1);
		l_contestantIds.get(0).add(2);
		
		//Contest 1 Locations - Ward 1
		l_contestantIds.add(new Vector<Integer>());
		l_contestantIds.get(1).add(0);
		l_contestantIds.get(1).add(1);
		
		//Contest 2 Locations - Ward 2
		l_contestantIds.add(new Vector<Integer>());
		l_contestantIds.get(2).add(0);
		l_contestantIds.get(2).add(1);
		
		//Contest 3 Locations - Ward 3
		l_contestantIds.add(new Vector<Integer>());
		l_contestantIds.get(3).add(0);
		l_contestantIds.get(3).add(1);
		
		//Contest 4 Locations - Ward 4
		l_contestantIds.add(new Vector<Integer>());
		l_contestantIds.get(4).add(0);
		l_contestantIds.get(4).add(1);
		
		//Contest 5 Locations - Ward 5
		l_contestantIds.add(new Vector<Integer>());
		l_contestantIds.get(5).add(0);
		l_contestantIds.get(5).add(1);
		
		//Contest 6 Locations - Ward 6
		l_contestantIds.add(new Vector<Integer>());
		l_contestantIds.get(6).add(0);
		l_contestantIds.get(6).add(1);
		l_contestantIds.get(6).add(2);
		
		Vector<Contest> l_c = new Vector<Contest>();
		
		//Contest 0 Locations - Mayor's Race
		Contest l_x = new Contest();
		l_x.setId(0);
		l_x.setContestName("CAT");
		l_x.setShortName("CAT");
		Vector<Contestant> l_can = new Vector<Contestant>();
		l_can.add(new Contestant(0, "Cheetah"));
		l_can.add(new Contestant(1, "Tiger"));
		l_can.add(new Contestant(2, "Write-In"));
		l_x.setContestants(l_can);
		l_x.setMethod(new InstantRunoffTally());
		l_c.add(l_x);
		
		//Contest 1 Locations - Ward 1
		l_x = new Contest();
		l_x.setId(1);
		l_x.setContestName("APE");
		l_x.setShortName("APE");
		l_can = new Vector<Contestant>();
		l_can.add(new Contestant(0, "Chimpanzee"));
		l_can.add(new Contestant(1, "Write-In"));
		l_x.setContestants(l_can);
		l_x.setMethod(new InstantRunoffTally());
		l_c.add(l_x);		
		
		//Contest 2 Locations - Ward 2
		l_x = new Contest();
		l_x.setId(2);
		l_x.setContestName("APE");
		l_x.setShortName("APE");
		l_can = new Vector<Contestant>();
		l_can.add(new Contestant(0, "Chimpanzee"));
		l_can.add(new Contestant(1, "Write-In"));
		l_x.setContestants(l_can);
		l_x.setMethod(new InstantRunoffTally());
		l_c.add(l_x);		
		
		//Contest 3 Locations - Ward 3
		l_x = new Contest();
		l_x.setId(3);
		l_x.setContestName("APE");
		l_x.setShortName("APE");
		l_can = new Vector<Contestant>();
		l_can.add(new Contestant(0, "Chimpanzee"));
		l_can.add(new Contestant(1, "Write-In"));
		l_x.setContestants(l_can);
		l_x.setMethod(new InstantRunoffTally());
		l_c.add(l_x);		
		
		//Contest 4 Locations - Ward 4
		l_x = new Contest();
		l_x.setId(4);
		l_x.setContestName("APE");
		l_x.setShortName("APE");
		l_can = new Vector<Contestant>();
		l_can.add(new Contestant(0, "Chimpanzee"));
		l_can.add(new Contestant(1, "Write-In"));
		l_x.setContestants(l_can);
		l_x.setMethod(new InstantRunoffTally());
		l_c.add(l_x);		
		
		//Contest 5 Locations - Ward 5
		l_x = new Contest();
		l_x.setId(5);
		l_x.setContestName("APE");
		l_x.setShortName("APE");
		l_can = new Vector<Contestant>();
		l_can.add(new Contestant(0, "Chimpanzee"));
		l_can.add(new Contestant(1, "Write-In"));
		l_x.setContestants(l_can);
		l_x.setMethod(new InstantRunoffTally());
		l_c.add(l_x);		
		
		//Contest 6 Locations - Ward 6
		l_x = new Contest();
		l_x.setId(6);
		l_x.setContestName("APE");
		l_x.setShortName("APE");
		l_can = new Vector<Contestant>();
		l_can.add(new Contestant(0, "Chimpanzee"));
		l_can.add(new Contestant(1, "Orangutan"));
		l_can.add(new Contestant(2, "Write-In"));
		l_x.setContestants(l_can);
		l_x.setMethod(new InstantRunoffTally());
		l_c.add(l_x);		
		
		/*** WARD 1 ***/
		Vector<Integer> l_conIds = new Vector<Integer>();
		l_conIds.add(0);
		l_conIds.add(1);
		Vector<Vector<Vector<Rectangle>>> l_conRects = new Vector<Vector<Vector<Rectangle>>>();
		l_conRects.add(l_rects.get(0));
		l_conRects.add(l_rects.get(1));
		TreeMap<Integer, TreeMap<Integer, Rectangle>> l_wRects = new TreeMap<Integer, TreeMap<Integer,Rectangle>>();
		l_wRects.put(0, l_writeInRects.get(0));
		l_wRects.put(1, l_writeInRects.get(1));
		l_styles[0] = new BallotStyle(1, l_conIds, l_conRects, true);
		l_styles[0].setWriteInRects(l_wRects);
		/*** WARD 2 ***/
		l_conIds = new Vector<Integer>();
		l_conIds.add(0);
		l_conIds.add(2);
		l_conRects = new Vector<Vector<Vector<Rectangle>>>();
		l_conRects.add(l_rects.get(0));
		l_conRects.add(l_rects.get(2));
		l_wRects = new TreeMap<Integer, TreeMap<Integer,Rectangle>>();
		l_wRects.put(0, l_writeInRects.get(0));
		l_wRects.put(2, l_writeInRects.get(2));
		l_styles[1] = new BallotStyle(2, l_conIds, l_conRects, true);
		l_styles[1].setWriteInRects(l_wRects);
		/*** WARD 3 ***/
		l_conIds = new Vector<Integer>();
		l_conIds.add(0);
		l_conIds.add(3);
		l_conRects = new Vector<Vector<Vector<Rectangle>>>();
		l_conRects.add(l_rects.get(0));
		l_conRects.add(l_rects.get(3));
		l_wRects = new TreeMap<Integer, TreeMap<Integer,Rectangle>>();
		l_wRects.put(0, l_writeInRects.get(0));
		l_wRects.put(3, l_writeInRects.get(3));
		l_styles[2] = new BallotStyle(3, l_conIds, l_conRects, true);
		l_styles[2].setWriteInRects(l_wRects);
		/*** WARD 4 ***/
		l_conIds = new Vector<Integer>();
		l_conIds.add(0);
		l_conIds.add(4);
		l_conRects = new Vector<Vector<Vector<Rectangle>>>();
		l_conRects.add(l_rects.get(0));
		l_conRects.add(l_rects.get(4));
		l_wRects = new TreeMap<Integer, TreeMap<Integer,Rectangle>>();
		l_wRects.put(0, l_writeInRects.get(0));
		l_wRects.put(4, l_writeInRects.get(4));
		l_styles[3] = new BallotStyle(4, l_conIds, l_conRects, true);
		l_styles[3].setWriteInRects(l_wRects);
		/*** WARD 5 ***/
		l_conIds = new Vector<Integer>();
		l_conIds.add(0);
		l_conIds.add(5);
		l_conRects = new Vector<Vector<Vector<Rectangle>>>();
		l_conRects.add(l_rects.get(0));
		l_conRects.add(l_rects.get(5));
		l_wRects = new TreeMap<Integer, TreeMap<Integer,Rectangle>>();
		l_wRects.put(0, l_writeInRects.get(0));
		l_wRects.put(5, l_writeInRects.get(5));
		l_styles[4] = new BallotStyle(5, l_conIds, l_conRects, true);
		l_styles[4].setWriteInRects(l_wRects);
		/*** WARD 6 ***/
		l_conIds = new Vector<Integer>();
		l_conIds.add(0);
		l_conIds.add(6);
		l_conRects = new Vector<Vector<Vector<Rectangle>>>();
		l_conRects.add(l_rects.get(0));
		l_conRects.add(l_rects.get(6));
		l_wRects = new TreeMap<Integer, TreeMap<Integer,Rectangle>>();
		l_wRects.put(0, l_writeInRects.get(0));
		l_wRects.put(6, l_writeInRects.get(6));
		l_styles[5] = new BallotStyle(6, l_conIds, l_conRects, true);
		l_styles[5].setWriteInRects(l_wRects);

		
		Vector<String> l_outDirNames = new Vector<String>();
		l_outDirNames.add("/media/");
		
		
		Vector<BallotStyle> l_s = new Vector<BallotStyle>();
		for (BallotStyle l_sty : l_styles)
			l_s.add(l_sty);
		
		Vector<String> l_j = new Vector<String>();
		l_j.add("Anne Sergeant");
		l_config.setChiefJudges(l_j);
		l_config.setLocation("Timbuktoo Community Center Azalea Room");
		l_config.setName("Timbuktoo Mock Election at Takoma Park");
		l_config.setOutputDirNames(l_outDirNames);
		l_config.setPollID(10);
		l_config.setReader(l_reader);
		l_config.setContests(l_c);
		
		l_config.setDate("October 11th, 2009");
		l_config.setTime("10:00 am");
		
		l_config.setScannerID(37337);
		l_config.setStyles(l_s);
		
		/*Vector<String> l_jh = new Vector<String>();
		l_jh.add("[B@de6ced");
		l_config.setJudgePassHash(l_jh);*/
		
		XMLEncoder e;
		try
		{
			FileOutputStream l_out = new FileOutputStream(c_loc + "ContestInformation.xml");
			e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(c_loc + c_name)));
			e.writeObject(l_config);
			e.close();		
			e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(c_loc + "ContestInformation.xml")));
			e.writeObject(l_c);
			e.close();
		}
		catch (FileNotFoundException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}