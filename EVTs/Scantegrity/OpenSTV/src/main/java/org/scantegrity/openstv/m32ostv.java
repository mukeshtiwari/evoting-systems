/*
 * @(#)BallotStore.java.java
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
package org.scantegrity.openstv;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.scantegrity.common.Ballot;
import org.scantegrity.common.Contest;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import org.scantegrity.common.Contestant;

/**
 * Converts MeetingThreeOut.xml (results) files to the OSTV format. Requires
 * ContestInformation.xml in order to get names. 
 * 
 * @author Richard Carback
 *
 */
public class m32ostv 
{
	private static Options c_opts = null;	
	
	public static void main(String args[])
	{
		TreeMap<Integer, Vector<Ballot>> l_results = null;
		
		setOptions();
		
		String l_args[] = null;
		CommandLine l_cmdLine = null;
		try {
			CommandLineParser l_parser = new PosixParser();
		    l_cmdLine = l_parser.parse(c_opts, args);
		    l_args = l_cmdLine.getArgs();
		}
		catch( ParseException l_e ) {
			l_e.printStackTrace();
		    return;
		}
		
	    if (l_cmdLine == null || l_cmdLine.hasOption("help") || l_args == null 
	    		|| l_args.length < 1 || l_args.length > 2)
	    {
	    	printUsage();
	    	return;
	    }
	    
	    //Looks like we have valid arguments, try to read M3
		try
		{
			l_results = ParseMeetingThree(l_args[0]);
		}
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
			return;
		}
		catch (SAXException e)
		{
			e.printStackTrace();
			return;
		}
		catch (IOException e)
		{
			System.out.println("Could not read '" + l_args[0] + "'");
			return;
		}
		
		//Get contest information, if possible.
	    Vector<Contest> l_c = null;
	    if (l_cmdLine.hasOption("info"))
	    {
	    	System.out.println("Contest Information");
	    	try 
	    	{
		    	l_c = loadContest(l_cmdLine.getOptionValue("info"));
	    		//validateContest(l_results, l_c);
	    	} catch (Exception l_e)
	    	{
	    		l_e.printStackTrace();
	    		l_c = null;
	    	}
	    }
	    
	    if (l_c == null)
	    {
	    	//Load defaults
	    	System.out.print("Contest information is missing! ");
	    	System.out.print("Generating default contest information...");
	    	l_c = getContestDefaults(l_results);
	    	System.out.println("done");
	    }
		
	    //Convert results to BLT format.
	    Integer l_key = l_results.firstKey();
	    while (l_key != null)
	    {
	    	/*
	    	for (Ballot l_b : l_results.get(l_key))
	    	{
	    		System.out.println(l_b.getId());
	    		System.out.println(java.util.Arrays.deepToString(l_b.getBallotData().get(0)));
	    		
	    	}*/
	    	//Find the corresponding contest
	    	Contest l_contest = null;
	    	for (Contest l_con : l_c) if (l_con.getId().equals(l_key)) 
    		{
	    		l_contest = l_con;
	    		break;
    		}
	    	if (l_contest == null)
    		{
	    		System.out.println("Contest " + l_key + " could not be found...");
	    		continue;
    		}
	    	//Get the ballots and Print
	    	try
			{
				createBLT(l_contest, l_results.get(l_key));
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    	l_key = l_results.higherKey(l_key);
	    }
		
	}
	
	/**
	 * Creates a BLT File.
	 * @param p_contest the contest to use
	 * @param p_ballots the ballot store
	 * @throws IOException
	 */
	public static void createBLT(Contest p_contest, Vector<Ballot> p_ballots) throws IOException
	{
		System.out.println("Saving Contest" + p_contest.getId() + ".xml");
		
		Vector<Ballot> l_ballot = new Vector<Ballot>();
		
		//Pull out all the ballots which contain this contest
		for (Ballot l_b : p_ballots)
		{
			if (l_b.hasContest(p_contest.getId()))
				l_ballot.add(l_b);
		}

		//TODO: Should "clean" the ballots here.
		
		FileWriter l_file = new FileWriter("Contest" + p_contest.getId() + ".blt");
		BufferedWriter l_out = new BufferedWriter(l_file);
		BufferedWriter l_log = new BufferedWriter(new FileWriter("log.txt"));
		l_out.write(p_contest.getContestants().size() + " 1");
		l_out.newLine();
		
		for (Ballot l_b : l_ballot)
		{
			Integer[][] l_cData = l_b.getContestData(p_contest.getId());
			//System.out.println(java.util.Arrays.deepToString(l_cData));
			
			Vector<Integer> l_rankOrder = new Vector<Integer>();
			int l_skip = 0;
			for (int l_rank = 0; l_rank < l_cData[0].length; l_rank++)
			{
				int l_printCan = 0;
				for (int l_can = 0; l_can < l_cData.length; l_can++)
				{
					if (l_cData[l_can][l_rank] == 1)
					{
						if (l_printCan != 0)
						{
							l_log.write("Ballot " + l_b.getId() + ": " 
									+ "Overvote on Rank " + (l_rank+1));
							l_log.newLine();
							System.out.println("Ballot " + l_b.getId() + ": " 
									+ "Overvote on Rank " + (l_rank+1));
							l_printCan = -1;
						}
						else
						{
							l_printCan = l_can+1;
						}
					}
				}
				if (l_printCan != 0)
				{
					l_skip = 0;
					if (l_printCan != -1)
					{
						if (l_rankOrder.contains(l_printCan))
						{
							l_log.write("Ballot " + l_b.getId() + ": " 
									+ "Multipl candidates for same Rank");
							l_log.newLine();
							System.out.println("Ballot " + l_b.getId() + ": " 
									+ "Multiple candidates for same Rank");
							
						}
						else
						{
							l_rankOrder.add(l_printCan);
						}
					}
				}
				else
				{
					if (l_skip == 0) l_skip++;					
					else break; //NOTE: This condition is typically NOT reported.
				}
			}
			//System.out.println(l_rankOrder.toString());
			//Print this ballot
			l_out.write("1 ");
			for (Integer l_j : l_rankOrder) l_out.write(l_j + " ");
			l_out.write("0");
			l_out.newLine();
		}
		
		l_out.write("0");
		l_out.newLine();

		for (Contestant l_cname : p_contest.getContestants())
		{
			l_out.write("\"" + l_cname.getName() + "\"");
			l_out.newLine();
		}
		
		l_out.write("\"" + p_contest.getContestName() + "\"");
		l_out.newLine();
		
		l_out.close();
		l_log.close();
		//l_file.close();
	}
	
	/**
	 * Returns an Map of ballot data back to the calling function. This 
	 * function doesn't need contest data to work, it just reads the m3 format
	 * and produces Ballot objects and contest data from with in it. This lack
	 * of extra information adds a little bit of complexity, but that's OK.
	 * 
	 * @param p_file
	 * @return
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	private static TreeMap<Integer, Vector<Ballot>> ParseMeetingThree(String p_file) throws ParserConfigurationException, SAXException, IOException
	{
		TreeMap<Integer, Vector<Ballot>> l_data = new TreeMap<Integer, Vector<Ballot>>();
		BufferedInputStream l_in = 
			new BufferedInputStream(new FileInputStream(p_file));
		
		//Create documentbuilder and parse m3 file
		DocumentBuilderFactory l_factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder l_builder = l_factory.newDocumentBuilder();
		Document l_doc = l_builder.parse(l_in);
		
		NodeList l_partitions = l_doc.getElementsByTagName("partition");		
		//System.err.println("Partitions: " + l_partitions.getLength());
		
		//Look at each partition/contest block
		for( int x = 0; x < l_partitions.getLength(); x++ )
		{	
			Node l_contest = l_partitions.item(x);
			int l_contestId = Integer.parseInt(
				   l_contest.getAttributes().getNamedItem("id").getNodeValue());
			
			//Look at the results block inside the partition block, that's
			//where the plaintext results are stored.
			Node l_results = null;
			for( int y = 0; y < l_contest.getChildNodes().getLength(); y++ )
			{
				Node l_child = l_contest.getChildNodes().item(y);
				if( l_child.getNodeName() == "results" )
					l_results = l_child;
			}
			
			NodeList l_rows = l_results.getChildNodes();
			
			//System.err.println("Rows: " + l_rows.getLength());

			//int l_length = 0;
			
			Vector<Ballot> l_ballots = new Vector<Ballot>();
			//For each ballot..
			for( int y = 0; y < l_rows.getLength(); y++ )
			{
				if( l_rows.item(y).getNodeName() == "row" )
				{
					int l_bid = Integer.parseInt(l_rows.item(y)
												.getAttributes()
												.getNamedItem("id")
												.getNodeValue());
					
					//"r" is the results data attribute
					String[] l_sData = l_rows.item(y).getAttributes()
											.getNamedItem("r")
											.getNodeValue().split(" ");
					int l_numRanks = l_sData.length;
					int l_maxCan = 0;

					//Read rank data into an array.
					int[] l_bData = new int[l_numRanks];
					for (int j = 0; j < l_numRanks; j++)
					{
						l_bData[j] = Integer.parseInt(l_sData[j]);
						l_maxCan = Math.max(l_maxCan, l_bData[j]);
					}
					
					//Init the raw (boolean logic) data matrix
					Integer[][] l_rawData = new Integer[l_maxCan+1][];
					for (int j = 0; j < l_maxCan+1; j++)
					{
						l_rawData[j] = new Integer[l_numRanks];
						java.util.Arrays.fill(l_rawData[j], 0);						
					}
					
					//Fill in the spots from the "r" attribute
					for (int j = 0; j < l_numRanks; j++)
					{
						if (l_bData[j] >= 0)
						{
							l_rawData[l_bData[j]][j] = 1;							
						}
					}
					
					/*
					System.out.println("Adding ballot with Contest ID: " + l_contestId);
					System.out.println(l_bid);
					System.out.println(java.util.Arrays.toString(l_bData));
					System.out.println(java.util.Arrays.deepToString(l_rawData));
				 	*/
					Ballot l_b = new Ballot();
					TreeMap<Integer, Integer[][]> l_cData = new TreeMap<Integer, Integer[][]>();
					l_cData.put(l_contestId, l_rawData);
					l_b.setId(l_bid);
					l_b.setBallotData(l_cData);
					l_ballots.add(l_b);
				}
			}
			l_data.put(l_contestId, l_ballots);
		}		
		
		return l_data;
	}
	
	
	/**
	 * Fills in default strings if contest information isn't provided
	 * @param p_results
	 * @return
	 */
	public static Vector<Contest> getContestDefaults(
									TreeMap<Integer, Vector<Ballot>> p_results)
	{
		Vector<Contest> l_res = new Vector<Contest>();
		Integer l_key = p_results.firstKey();
		while (l_key != null)
		{
			Ballot l_b = p_results.get(l_key).get(0);
			Integer[][] l_d = l_b.getContestData(l_key);
			Vector<Contestant> l_cons = new Vector<Contestant>();
			for (int i = 0; i < l_d.length; i++)
			{
				l_cons.add(new Contestant(i, "Contestant " + i));
			}

			Contest l_c = new Contest();
			l_c.setId(l_key);
			l_c.setContestName("Contest " + l_key);
			l_c.setContestants(l_cons);
			l_res.add(l_c);
			l_key = p_results.higherKey(l_key);
		}
		
		return l_res;
	}
	
	/**
	 * Load a contest from file. 
	 * @param l_fname
	 * @throws FileNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public static Vector<Contest> loadContest(String p_fname) throws FileNotFoundException
	{
		Vector<Contest> l_res;
		XMLDecoder e;
		e = new XMLDecoder(new BufferedInputStream(new FileInputStream(p_fname)));
		l_res = (Vector<Contest>)e.readObject();
		e.close();		
		return l_res;
	}

	/**
	 * Create options for this application. Currently there is only 1, and 
	 * that is if the user wants to include a contest information file.
	 */
	@SuppressWarnings("static-access")
	public static void setOptions()
	{
		c_opts = new Options();
		
		Option l_contestInfo = OptionBuilder.withArgName( "contestinfo" )
						.hasArg()
						.withDescription("Use a file that contains contest information.")
						.create("info");
		
		Option l_help = new Option("help", "Print help message.");
		Option l_verb = new Option("v", "Unimplemented verbosity setting, prints more info.");
		
		c_opts.addOption(l_contestInfo);	
		c_opts.addOption(l_help);	
		c_opts.addOption(l_verb);	
		
	}
	
	/**
	 * Prints the usage information for the application. 
	 */
	public static void printUsage()
	{
		try {			
			HelpFormatter l_form  = new HelpFormatter();
			l_form.printHelp(80, "mt2ostv [OPTIONS] INFILE [OUTFILE]", 
					"mt2ostv converts results files in Scantegrity to the BLT format " +
					"used by the OpenSTV counting program. It uses the Scantegrity" +
					" results INFILE, usually named MeetingThreeOut.xml to produce" +
					" a BLT compatible file to stdout or OUTFILE.\n\nOPTIONS:", c_opts, "", false);
		} 
		catch (Exception l_e)
		{
			l_e.printStackTrace();
			System.exit(-1);
		}
	}
	
}