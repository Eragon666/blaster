IR Blaster
==========

*	[Overview](#overview)
	*	[Note 3](#note)
	*	[Ziggo](#ziggo)
* 	[Technical](#technical)
	*	[Libraries](#libraries)
	*	[External data](#data)
	*	[Compatibility](#compatibility)
*	[Design](#design)

<a id="overview"></a>Overview
=============================

Project by: Matthijs Klijn
Studentnumber: 10447822
Mail: matthijsthoolen@hotmail.com

A remote control for your television, digital TV box and sound system. 

Everytime I watch television I have to use 3 different remotes. One for the TV, one for the audio system 
and one for the Ziggo digital box and ofcourse I have my phone with me aswell. Why not combine those? 

The newest Samsung phones have a build in IR blaster. It comes with a WatchOn app, WatchOn is a wonderfull 
applications, it has thousands of codes for all kinds of infra-red equipment. But the TV GUIDE isn't compatible 
for the Dutch channels, and they only offer the basic controls (volume, channel, channel-numbers, power). So I wanted
to develop a new app for the dutch market, with a better TV guide and more buttons. 

<a id="note"></a>Note 3
-----------------------

Not all android phones have a built-in IR blaster, only some of the newest high-end Android phones have a infra-red sensor. 
But there isn't a API, framework, documentation or anything else for the IR blaster, it is a closed API. But there are some guys on
the internet who found a way to control the IR blaster, and that works great! But because there isn't any official API or documentation
I am not sure if it will work on other phones than the Note 3. So the IR function will only be compatible for the Note 3 (and a big chance
that it will work on the other Samsung phones). 

I will also use some movement detection functions to switch channels, change sound volume etc. But those functions do have a public API,
so that should work on all phones. 

<a id="ziggo"></a>Ziggo
-----------------------

Because we have Ziggo at home for our television, I will focus on compatibility with Ziggo. I couldn't find a official API for the television guide,
but I did found some unofficial XML api's. (https://code.google.com/p/tvgrabnlpy/ and for more countries the http://wiki.xmltv.org/index.php/XMLTVProject). 

<a id="technical"></a>Technical
===============================

<a id="libraries"></a>Libraries
-------------------------------

I will use some built-in libraries for motion detection, and a really small external library for the IR blaster. And maybe I will use a library for a connection
to a external server for the TV guides.

<a id="data"></a>Data
---------------------

We need a lot of data for the app, and I couldn't save it all in the app. So I have to build a external server where all the information is gathered and send to 
the app. We need all codes for the remote control (partly saved in the app itself, but I want to be able to add new remotes without updating the app). And we
need the TV guide, there are some PHP/XML libraries for the TV guide so I will make a small PHP based (JSON-)API for all this information.

<a id="compatibility"></a>Compatibility
---------------------------------------
As said before in the overview, the IR blaster isn't officialy supported (not in Android 4.3). So this app will only fully function on the Note 3 and maybe the
other Samsung phones with the IR blaster. Because I really want to use this app at my home, I will design it for the Note 3 screensize (5,5 inch). I will try to
also make it more or less compatible with smaller phones, but thats not my main priority. 

<a id="design"></a>Design
=========================

I will start with a very basic design based on the WatchOn app. With some changes to the remote to support more buttons, and on the channels screen to show the channel
logo instead of a picture from the show. 

When I have time left I will try to make it more fancy with shiny backgrounds, nice transitions etc. 

The menu: <br>
![Main menu](/doc/menu.jpg)

The channels: <br>
![Level menu](/doc/channels.jpg)

The remote control: <br>
![Game screen](/doc/remote.jpg)