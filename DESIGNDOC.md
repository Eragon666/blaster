IR Blaster Design document
==========================

*	[Technical specifications](#tech)
	*	[Database](#db)
	*	[Classes and functions](#classes)
*	[Style Guide](#style)
	*	[Java code](#java)
	*   [SQL (lite)](#sql)
*	[Design](#design)

<a id="tech"></a>Technical specifications
=========================================

<a id="db"></a>Databases
------------------------

There will be databases with local information (for the user specific data), and there will be databases with global information (IR codes, TV guide etc). 
I will only make a table here for the databases which are not 'easy' databases with only a id and a name.

The first database is the database with the different IR remotes, there will be a seperate database for the codes.
<table>
	<tr>
		<th>IR remotes</th>
	</tr>
	
	<tr>
		<th>Column name</th>		<th>Column type</th>		<th>Description</th>
	</tr>

	<tr>	
		<td>id</td>					<td>int</td>				<td>ID of the ir remote</td>
	</tr>

	<tr>	
		<td>Manufacturer</td>		<td>String</td>				<td>Manufacturer of the remote/ TV</td>
	</tr>

	<tr>	
		<td>Type</td>				<td>int</td>				<td>Type of the remote: for audio systems, tv, beamers etc</td>
	</tr>
</table>

In the IR remote codes table are the IR codes, connected to a ID in the IR remotes table

<table>
	<tr>
		<th>IR remote codes</th>
	</tr>
	
	<tr>
		<th>Column name</th>		<th>Column type</th>		<th>Description</th>
	</tr>

	<tr>	
		<td>Code id</td>			<td>int</td>				<td>ID for the remote code</td>
	</tr>
	
	<tr>	
		<td>Remote id</td>			<td>int</td>				<td>ID for the remote control</td>
	</tr>
	
	<tr>	
		<td>Button id</td>			<td>int</td>				<td>Button ID</td>
	</tr>
	
	<tr>	
		<td>Remote code</td>		<td>string</td>				<td>HEX code for the remote</td>
	</tr>
</table>

The app must function offline, and everytime the user connects to the internet I will check for new content.
When the user connects to the internet, the TV Guide will be updated for there provider, and then saved in this table.

This table uses information from a providers database (with the providers listed in each region), and a channel database. 

<table>
	<tr>
		<th>TV Guide</th>
	</tr>
	
	<tr>
		<th>Column name</th>		<th>Column type</th>		<th>Description</th>
	</tr>

	<tr>	
		<td>id</td>					<td>int</td>				<td>id</td>
	</tr>
	
	<tr>	
		<td>provider_id</td>		<td>int</td>				<td>The provider id</td>
	</tr>
	
	<tr>	
		<td>channel_id</td>			<td>int</td>				<td>channel id</td>
	</tr>
	
	<tr>	
		<td>program</td>			<td>string</td>				<td>Program name</td>
	</tr>
	
	<tr>	
		<td>start</td>				<td>int</td>				<td>Start time</td>
	</tr>
	
	<tr>	
		<td>end</td>				<td>int</td>				<td>End time</td>
	</tr>
	
	<tr>	
		<td>description</td>		<td>Text</td>				<td>Program description</td>
	</tr>
</table>

the user settings databases safes all information about the user and about the latest connections
to the internet

<table>
	<tr>
		<th>User settings</th>
	</tr>
	
	<tr>
		<th>Column name</th>		<th>Column type</th>		<th>Description</th>
	</tr>

	<tr>	
		<td>latest update</td>		<td>int</td>				<td>latest update from the webserver</td>
	</tr>
	
	<tr>	
		<td>provider id</td>		<td>int</td>				<td>Used provider id</td>
	</tr>
	
	<tr>	
		<td>country</td>			<td>String</td>				<td>Country of the user</td>
	</tr>
</table>

User remotes will save the remotes for the Tv from the user. This database will make a link with an additional room database. 

<table>
	<tr>
		<th>Column name</th>		<th>Column type</th>		<th>Description</th>
	</tr>

	<tr>	
		<td>id</td>					<td>int</td>				<td>ID</td>
	</tr>
	
	<tr>	
		<td>room_id</td>			<td>int</td>				<td>The ID from the Room database</td>
	</tr>
	
	<tr>	
		<td>remote_id</td>			<td>int</td>				<td>Connection to the IR remotes database</td>
	</tr>
</table>

As said before, there are some more databases with a small number of columns (with only id and a name). 

<a id="classes"></a>Classes and functions
-----------------------------------------

I will use 3 big classes, two of them for the most important functions of the app, and the last one for the connection with the online
database. 

Class: TV Guide
---------------

<b>Method: TVGuide</b><br>
<i>Private method</i>
Check the users settings, and make sure that the most recent data is retrieved from the server. If there are no settings yet,
ask the user for his settings. 

<b>Method: showinfo</b><br>
<i>Private method</i>
Show all the information from the TV guide for the selected room. 


Class: Remote controller
------------------------

<b>Method: RemoteController</b><br>
<i>Private class</i>
Check for the most recent information from the server, and then start the remote controller.

<b>Method: showController<b><br>
<i>Private class</i>
Show the controller for the selected room

<b>Method: sendSignal</b><br>
<i>Public class</i>
Send a signal to the IR blaster. 

<b>Method: configure settings</b><br>
<i>public class</b>
Configure a new room, remote and channels. 

Async task: Database connection
-------------------------------

<b>Method: getTVGuide</b>
<i>Public class</i>
Check for the most recent TV Guide.

<b>Method: getIRCodes</b>
<i>Public class</i>
Get the most recent IR codes and update the database.

<a id="style"></a>Style Guides
==============================
Because I will work alone, the style guide will be important, but I am not going to write down every single possibility with an example
and a description but I will just give a short example for each coding language. I copied the style guide from the Hangman design document.

<a id="java"></a>Java code
--------------------------
<code>
/*
* If there is a multi line comment I will use this comment declaration
* and for single lines I will use a different one.
*/

function camelCase
{

	if (a == b) 
	{
		//Count a and b
		a + b = c;
	}
	else
	{
		a - c = d;
	}
}
</code>

<a id="sql"></a>SQL (lite)
--------------------------
<code>
/*
* The comment system will be the same as in Java (and like in almost all other languages
* I use).
*/

// When the query doesnt fit on a single line I will break in into different pieces.
$stmt = "SELECT
			a, b, c, d, e, f, h
		FROM theFirstTable
		Where a = b";
</code>

<a id="design"></a>Design
=========================

This part is copied from the readme. I didn't make it in Photoshop this time (as i did with the Hangman project), because I like the design choices they made with the
WatchOn app. So I am not going to spend hours redrawing that in Photoshop. I can better spend that time writing code. 

I will start with a very basic design based on the WatchOn app. With some changes to the remote to support more buttons, and on the channels screen to show the channel
logo instead of a picture from the show. 

When I have time left I will try to make it more fancy with shiny backgrounds, nice transitions etc. 

The menu: <br>
![Main menu](/doc/menu.jpg)

The channels: <br>
![Level menu](/doc/channels.jpg)

The remote control: <br>
![Game screen](/doc/remote.jpg)